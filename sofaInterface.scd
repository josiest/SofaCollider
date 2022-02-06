(
var runSOFAroutine;
var attributes;
var printOctaveAttribute;

// define where to find the sofa source repository
~sofaRoot = PathName("/home/josiest/sofa/API_MO");
~sofaSrcDir = ~sofaRoot +/+ "API_MO";
~sofaConventionsDir = ~sofaSrcDir +/+ "conventions";

/** Run octave code on a SOFA object using the SOFA API.
 *
 * \param hrtfPath the path name to the sofa file to load
 * \param source the octave code to run
 *
 * The source code should refer to the SOFA object file as `hrtf`. Any output
 * by the source code will be captured and returned as a string.
 */
runSOFAroutine = { | hrtfPath, source |
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
        "addpath('%');".format(~sofaSrcDir.fullPath),
        "SOFAstart;",

        // load the specified sofa file and mark the beginning of output
        "hrtf = SOFAload('%');".format(hrtfPath.fullPath),
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

    output
};

/** Get the source vector of a given index from a SOFA object.
 *
 * \param hrtfPath the path name to the sofa file to load
 * \param index the index of the hrtf
 * \param precision the decimal precision of the output
 *
 * \return a vector of measurements (azimuth, elevation, radius)
 */
~sourceVectorFromIndex = { | hrtfPath, index, precision = 10 |

    // compile the octave source code that computes the source vector
    runSOFAroutine.(hrtfPath, [
        // get a matrix of source vectors then grab the specified index
        "apv = SOFAcalculateAPV(hrtf);",
        "v = apv(%, :);".format(index),

        // print each element (azi, ele, r) separated by commas
        "printf('\\%.%f,\\%.%f,\\%.%f\\n', v(1), v(2), v(3));".format(
            precision, precision, precision),
    ])
    // split the output into list of values, collect as floats
    .split($,).collect({ | val | val.asFloat })
};

// get the index and measurements of the source position
// closest to the given input vector
~closestSourceFromVector = { | hrtfPath, vec, precision = 10 |

    runSOFAroutine.(hrtfPath, [
        // get the index and measurements closest to the input vec
        "[idx, azi, ele, r] = SOFAfind(hrtf, %, %, %);"
            .format(vec[0], vec[1], vec[2]), // [azi, ele, r]

        // print the index and closest measurements that were found
        "printf('\\%d,\\%.%f,\\%.%f,\\%.%f\\n', idx, azi, ele, r);"
            .format(precision, precision, precision),
    ])
    // split the output into list of values, collect as floats
    .split($,).collect({|val, i|
        if(i == 0, { val.asInteger }, { val.asFloat })
    })
};

// get the impulse responses at the given index
~irFromIndex = { | hrtfPath, index, precision = 10 |

    runSOFAroutine.(hrtfPath, [
        // get the IR matrix and splice out the desired index
        "data = squeeze(hrtf.Data.IR(%, :, :));".format(index),
        "printf('%s', mat2str(data));"
    ])
    // strip the encapsulating [] brackets
    .replace("[").replace("]")
    // split the data into m columns
    .split($;).collect{ | line | line.split($ ).collect{ | num | num.asFloat } }
};

~conventionsFromFile = { | hrtfPath |

    var conventionPath, header, rawData, conventions;

    // define the path to the convention file and load the data
    conventionPath = ~sofaConventionsDir +/+ "FreeFieldHRIR_1.0.csv";
    // the file extension *says* csv, but the actual files use tab delimeters
    rawData = TabFileReader.read(conventionPath.fullPath);

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

    conventions
};

attributes = [
    \global ->
        ["GLOBAL_Conventions", "GLOBAL_SOFAConventions",
         "GLOBAL_SOFAConventionsVersion", "GLOBAL_APIName", "GLOBAL_APIVersion",
         "GLOBAL_DataType", "GLOBAL_RoomType", "GLOBAL_DateCreated",
         "GLOBAL_DateModified", "GLOBAL_AuthorContact", "GLOBAL_Organization",
         "GLOBAL_License", "GLOBAL_Title"],

    \SimpleFreeFieldHRIR ->
        ["GLOBAL_DatabaseName", "ListenerPosition:Type",
         "ListenerPosition:Units", "ReceiverPosition:Type",
         "ReceiverPosition:Units", "SourcePosition:Type",
         "SourcePosition:Units", "EmitterPosition:Type",
         "EmitterPosition:Units", "ListenerView:Type", "ListenerView:Units"]
].asDict;
printOctaveAttribute = { | attr |
    "printf('%:\\%s\\n', hrtf.%);"
        .format(attr.replace(":"), attr.replace(":", "_"))
};

// Create a new SOFA object from a SimpleFreeFieldHRIR file
~newSimpleFreeFieldHRIRFromFile = { | hrtfPath |

    var output, global, sofaObj;
    runSOFAroutine.(
        hrtfPath,
        attributes[\global].collect(printOctaveAttribute) ++
        attributes[\SimpleFreeFieldHRIR].collect(printOctaveAttribute))
    .stripWhiteSpace.split($\n)
    .collect{ | line |
        var components;
        components = line.split($:).collect{ | x | x.stripWhiteSpace };
        components[0] -> components[1]
    }.asDict
};
)
