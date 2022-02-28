SofaInterface {
    // define where to find the sofa source repository
    classvar <rootDir;
    classvar <srcDir;
    classvar <conventionsDir;
    classvar <attributeNames;

    *initClass{
        rootDir = "/home/josiest/sofa/API_MO/";
        srcDir = rootDir ++ "API_MO/";
        conventionsDir = srcDir ++ "conventions/";
        attributeNames = [
            \Common_Metadata ->
                ["GLOBAL:Conventions", "GLOBAL:Version",
                 "GLOBAL:SOFAConventions", "GLOBAL:SOFAConventionsVersion",
                 "GLOBAL:APIName", "GLOBAL:APIVersion",
                 "GLOBAL:DataType", "GLOBAL:RoomType",
                 "GLOBAL:DateCreated", "GLOBAL:DateModified",
                 "GLOBAL:AuthorContact", "GLOBAL:Organization",
                 "GLOBAL:License", "GLOBAL:Title"],

            \SimpleFreeFieldHRIR_Metadata ->
                ["GLOBAL:DatabaseName", "GLOBAL:ListenerShortName"],

            \SimpleFreeFieldHRIR_SpatialArrays ->
                ["ListenerPosition",
                 "ReceiverPosition",
                 "SourcePosition",
                 "EmitterPosition",
                 "ListenerView"]
        ].asDict;
    }

    *metadataNames{ | convention |
        ^(SofaInterface.attributeNames[\Common_Metadata] ++
          SofaInterface.attributeNames[(convention++\_Metadata).asSymbol]);
    }

    *spatialArrayNames{ | convention |
    }

    *loadMetadata{ | hrtfPath, convention |

        var output, global, sofaObj;

        // get the values of all the attributes given
        ^SofaInterface.prRunSofaroutine(

            hrtfPath,

            // create octave source code strings that print all metadata attributes,
            // both common and specific to the subclass
            SofaInterface.metadataNames(convention)
                .collect{ | attr | SofaInterface.prPrintMetadata(attr) }// ++

            // SofaInterface.spatialArrayNames(convention)
            //     .collect{ | attr | SofaInterface.prPrintSpatialArray(attr) }
        )
        // there might be trailing newlines, so strip before splitting
        .stripWhiteSpace.split($\n)
        // convert each line into an association: attribute name to its value
        .collect{ | line |
            var components, attrName, attrValue;
            components = line
                .split(SofaInterface.prOctaveAttributeDelimeter)
                .collect{ | x | x.stripWhiteSpace };

            attrName = SofaInterface.globalAttributeAsSymbol(components[0]);

            // there may be spaces in the output value
            // so we'll need to undo the string split
            attrValue = components
                .copyToEnd(1)   // take the rest of the array past the first elem
                .join($ );      // restore the original string

            attrName -> attrValue

        }.asDict
    }

    *attributeAsSymbol{ | name |
        ^name.replace(":").replace(".").asSymbol;
    }

    *globalAttributeAsSymbol{ | name |
        ^name.replace(":", "_").asSymbol;
    }

    *globalAttributeAsField{ | name |
        var attr = name.split($:)[1]; // get rid of "GLOBAL:"

        // decapitalize acronyms that appear at the start of the attribute name
        attr = attr.replace("API", "api").replace("SOFA", "sofa");

        // decapitalize the first letter and convert to ymbol
        ^(attr[0].toLower ++ attr.copyToEnd(1)).asSymbol;
    }

    // Get the source vector of a given index from a SOFA object.
    //
    // \param hrtfPath the path name to the sofa file to load
    // \param index the index of the hrtf
    // \param precision the decimal precision of the output
    //
    // \return a vector of measurements (azimuth, elevation, radius)
    *sourceVectorFromIndex { | hrtfPath, index, precision = 10 |

        // compile the octave source code that computes the source vector
        ^SofaInterface.prRunSofaroutine(hrtfPath, [
            // get a matrix of source vectors then grab the specified index
            "apv = SOFAcalculateAPV(hrtf);",
            "v = apv(%, :);".format(index),

            // print each element (azi, ele, r) separated by commas
            "printf('\\%.%f,\\%.%f,\\%.%f\\n', v(1), v(2), v(3));".format(
                precision, precision, precision),
        ])
        // split the output into list of values, collect as floats
        .split($,).collect({ | val | val.asFloat })
    }

    // find the closest source position to a given vector
    *closestSourceFromVector { | hrtfPath, vec, precision = 10 |

        ^SofaInterface.prRunSofaroutine(hrtfPath, [

            // find the closest data points to the input vector
            // and unpack them into octave variables for easy formatting
            "[idx, azi, ele, r] = SOFAfind(hrtf, %, %, %);"
            .format(vec[0], vec[1], vec[2]),

            // print the variables on a comma-seprated line
            // with specified precision for floating-points
            "printf('\\%d,\\%.%f,\\%.%f,\\%.%f\\n', idx, azi, ele, r);"
            .format(precision, precision, precision),
        ])
        // split the output into list of values, collect as floats
        .split($,).collect({|val, i|
            if(i == 0, { val.asInteger }, { val.asFloat })
        })
    }

    // get the Impulse Response at a given index
    *irFromIndex { | hrtfPath, index, precision = 10 |
    
        // the octave source code that gets the impulse response data
        ^SofaInterface.prRunSofaroutine(hrtfPath, [
            "data = reshape(hrtf.Data.IR(%, :, :), [200, 2]);".format(index),
            "printf('%s', mat2str(data));"
        ])
        // strip the encapsulating [] brackets
        .replace("[").replace("]")
        // split the data into two columns
        .split($;).collect{ | line | line.split($ ).collect{ | num | num.asFloat } }
    }

    // get th econventions of a sofa file
    *conventionsOfSofaFile { | hrtfPath |

        var conventionPath, header, rawData, conventions;

        // define the path to the convention file and load the data
        conventionPath = SofaInterface.conventionsDir ++ "/FreeFieldHRIR_1.0.csv";
        // the file extension *says* csv, but the actual files use tab delimeters
        rawData = TabFileReader.read(conventionPath);

        // separate the header line from the rest
        header = rawData[0];
        rawData = rawData.copyToEnd(1);

        // populate the conventions dictionary
        conventions = List();
        rawData.do{ | line |
            conventions.add(line.collect{ | val, i |
                header[i] -> val.stripWhiteSpace
            }.asDict);
        };

        ^conventions
    }

    // create an octave source code line for printing an attribute
    *prPrintMetadata{ | name |
        var delim, octaveAttr;
        delim = SofaInterface.prOctaveAttributeDelimeter;
        octaveAttr = SofaInterface.prMetadataAsOctaveAttribute(name);

          // start by printing the attribute's name 
        ^("printf('%".format(name) ++

          // separate the name from the actual octave data
          // by a pre-specified delimeter
          delim ++

          "%s" ++   // format the attribute value in octave
          "\\n'" ++ // end octave printf string on a newline

          // pass the format arg to the octave printf
          // which will be an attribute of a sofa object
          ", hrtf.%".format(octaveAttr) ++ ");")
    }

    // abstract the octave attribute name-to-value printing
    // delimeter for ease of changing it
    *prOctaveAttributeDelimeter{
        // currently a single space
        // because sofa convention attributes don't have spaces
        //
        // however, sofa attribute values may have spaces - so we'll need to
        // only split at the first space
        ^$ ;
    }

    // convert a metadata name to a octave member-field name
    *prMetadataAsOctaveAttribute{ | name |
        ^name.replace(":", "_");
    }
    // parse a spatial array name into its respective octave member-field names
    *prSpatialArrayOctaveAttributes{ | name |
        ^[] // TODO: figure out how octave gets spatial data information
        .asDict.know_(true);
    }

    // Run octave code on a SOFA object using the SOFA API.
    //
    // \param hrtfPath the path name to the sofa file to load
    // \param source the octave code to run
    //
    // The source code should refer to the SOFA object file as `hrtf`. Any output
    // by the source code will be captured and returned as a string.
    *prRunSofaroutine { | hrtfPath, source |
        var sourceFile, octaveCmd;
        var allSourceCode, pipe, output, lastLine, nextLine;

        // the name of the octave command and the temporary source file
        octaveCmd = "octave";
        sourceFile = "temp.m";

        // define the source code to run:
        //   first we'll need to bootstrap some things
        allSourceCode = [
            "warning('off', 'all');",
            // add the path to the sofa M/O repo and initialize
            "addpath('%');".format(SofaInterface.srcDir),
            "SOFAstart;",

            // load the specified sofa file and mark the beginning of output
            "hrtf = SOFAload('%');".format(hrtfPath),
            "printf('SuperCollider Data Interface\\n');",

        // now we can append the specified source code
        ] ++ source;

        // write the source code to the temporary file
        File.use(sourceFile, "w", { | fp |
            allSourceCode.do{ | line | fp.write(line ++ "\n"); };
        });

        // run the octave command, and pipe input into it
        pipe = Pipe.argv([octaveCmd, sourceFile], "r");

        // scan the output until we find the special tag
        lastLine = pipe.getLine; nextLine = pipe.getLine;
        while ({ lastLine != "SuperCollider Data Interface" }, {
            lastLine = nextLine; nextLine = pipe.getLine;
        });
        // write the rest of the output data into an output string
        while ({ nextLine.notNil }, {
            output = output ++ nextLine ++ "\n";
            nextLine = pipe.getLine;
        });

        // clean up and return the output
        pipe.close;
        File.delete(sourceFile);

        ^output
    }
}
