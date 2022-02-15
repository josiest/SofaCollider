SofaInterface {
    // define where to find the sofa source repository
    classvar <rootDir;
    classvar <srcDir;
    classvar <conventionsDir;
    classvar <attributes;

    *initClass{
        rootDir = "/home/josiest/sofa/API_MO/";
        srcDir = rootDir ++ "API_MO/";
        conventionsDir = srcDir ++ "conventions/";
        attributes = [
            \global ->
                ["GLOBAL_Conventions", "GLOBAL_SOFAConventions",
                 "GLOBAL_SOFAConventionsVersion", "GLOBAL_APIName",
                 "GLOBAL_APIVersion", "GLOBAL_DataType", "GLOBAL_RoomType",
                 "GLOBAL_DateCreated", "GLOBAL_DateModified",
                 "GLOBAL_AuthorContact", "GLOBAL_Organization",
                 "GLOBAL_License", "GLOBAL_Title"],
        
            \SimpleFreeFieldHRIR ->
                ["GLOBAL_DatabaseName", "GLOBAL_ListenerShortName",
                 "ListenerPosition:Type", "ListenerPosition:Units",
                 "ReceiverPosition:Type", "ReceiverPosition:Units",
                 "SourcePosition:Type", "SourcePosition:Units",
                 "EmitterPosition:Type", "EmitterPosition:Units",
                 "ListenerView:Type", "ListenerView:Units",
                 "Data.SamplingRate:Units"
             ]
        ].asDict;
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
        ^SofaInterface.prRunSOFAroutine(hrtfPath, [
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
    
        // the octave source code that computes what we want
        "path in function: ".post;
        hrtfPath.postln;
        ^SofaInterface.prRunSOFAroutine(hrtfPath, [

            "[idx, azi, ele, r] = SOFAfind(hrtf, %, %, %);"
            .format(vec[0], vec[1], vec[2]),

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
        ^SofaInterface.prRunSOFAroutine(hrtfPath, [
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

    *prPrintOctaveAttribute{ | attr |
        ^"printf('%:\\%s\\n', hrtf.%);"
            .format(attr.replace(":"), attr.replace(":", "_"))
    }

    *prLoadSofaMetaData{ | hrtfPath, convention=\SimpleFreeFieldHRIR |

        var output, global, sofaObj;

        // get the values of all the attributes given
        ^SofaInterface.prRunSOFAroutine(

            hrtfPath,
            // get all the global conventions, as well as the specific convention
            attributes[\global]
                .collect({ | attr | SofaInterface.prPrintOctaveAttribute(attr) }) ++

            attributes[convention]
                .collect({ | attr | SofaInterface.prPrintOctaveAttribute(attr) })
        )
        // there might be trailing newlines, so strip before splitting
        .stripWhiteSpace.split($\n)
        // convert each line into an association: attribute name to its value
        .collect{ | line |
            var components;
            components = line.split($:).collect{ | x | x.stripWhiteSpace };
            components[0].replace(":").replace(".").asSymbol -> components[1]
        }.asDict
    }
    
    // Run octave code on a SOFA object using the SOFA API.
    //
    // \param hrtfPath the path name to the sofa file to load
    // \param source the octave code to run
    //
    // The source code should refer to the SOFA object file as `hrtf`. Any output
    // by the source code will be captured and returned as a string.
    *prRunSOFAroutine { | hrtfPath, source |
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
