(
var runSOFAroutine;

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
~sourceVectorAt = { | hrtfPath, index, precision = 10 |

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

~closestSource = { | hrtfPath, azi, ele, r, precision |

    // the octave source code that computes what we want
    runSOFAroutine.(hrtfPath, [
        "[idx, azi, ele, r] = SOFAfind(hrtf, %, %, %);".format(azi, ele, r),
        "printf('\\%d,\\%.%f,\\%.%f,\\%.%f\\n', idx, azi, ele, r);".format(
            precision, precision, precision),
    ])
    // split the output into list of values, collect as floats
    .split($,).collect({|val, i|
        if(i == 0, { val.asInteger }, { val.asFloat })
    })
};

~irAt = { | hrtfPath, index, precision |

    // the octave source code that gets the impulse response data
    runSOFAroutine.(hrtfPath, [
        "data = reshape(hrtf.Data.IR(%, :, :), [200, 2]);".format(index),
        "printf('%s', mat2str(data));"
    ])
    // strip the encapsulating [] brackets
    .replace("[").replace("]")
    // split the data into two columns
    .split($;).collect{ | line | line.split($ ).collect{ | num | num.asFloat } }
};

~conventionsOf = { | hrtfPath |

    var conventionPath, rawData, conventions;

    // define the path to the convention file and load the data
    conventionPath = ~sofaConventionsDir +/+ "FreeFieldHRIR_1.0.csv";
    // the file extension *says* csv, but the actual files use tab delimeters
    rawData = TabFileReader.read(conventionPath.fullPath).postcs;

    // separate the header line from the rest
    header = rawData[0];
    rawData = rawData[1:];

    // populate the conventions dictionary
    conventions = Dictionary();
    rawData.do{ | line | line.do{ | val, i | conventions.put(header[i], val); } };

    conventions
};

~metaData = { | hrtfPath |

    // sample output
    //
    // ### SOFAinfo: ###
    // SOFA 2.0
    // Conventions: SimpleFreeFieldHRIR 1.0
    // API:         ARI SOFA API for Matlab/Octave 2.0
    // Data Type:   FIR
    // Room Type:   free field
    // Date Created:   2022-01-15 13:30:21
    // Date Modified:  2022-01-15 13:30:22
    // Author Contact:
    // Organization:
    // License:     No license provided, ask the author for permission
    // Title:
    // Datebase Name:  CIPIC
    // 
    // Measurement details (SimpleFreeFieldHRIR):
    // --------------------
    // Number of azimuth angles:   805
    // Number of elevation angles: 537
    // Number of radii:            1
    // Sampling Rate: 44100 hertz
    // Listener Short Name: subject_003
    // Listener Position: 0  0  0 metre
    // Listener View:     1  0  0 metre
    // 2 Receiver Positions
    //    from 0        0.09           0 [metre]
    //    to   0       -0.09           0 [metre]
    // Receiver Position Type: cartesian
    // 1250 Source Positions
    //    from 82.8929     -7.05302            1 [degree, degree, metre]
    //    to   263.6174     -7.714204             1 [degree, degree, metre]
    // Source Position Type: spherical
    // ###    end    ###
    var output, global, sofaObj;
};
)
