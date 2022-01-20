(
var runSOFAroutine;

// define where to find the sofa source repository
~sofaRoot = PathName("/home/josiest/sofa/API_MO");
~sofaSrcDir = ~sofaRoot +/+ "API_MO";

runSOFAroutine = { | source |
    var sourceFile, octaveCmd;
    var allSourceCode, pipe, output, lastLine, nextLine;

    // the name of the octave command and the temporary source file
    octaveCmd = "octave";
    sourceFile = "temp.m";

    // prepend the source code with sofa import
    allSourceCode = [
        "warning('off', 'all');",
        "addpath('%');".format(~sofaSrcDir.fullPath),
        "SOFAstart;"
    ] ++ source;

    // write the source code to the temporary file
    File.use(sourceFile, "w", { | fp |
        allSourceCode.do({ | line | fp.write(line ++ "\n"); });
    });

    // run the octave command, and pipe input into it
    pipe = Pipe.argv([octaveCmd, sourceFile], "r");

    // scan the output until the last line
    lastLine = pipe.getLine; nextLine = pipe.getLine;
    while ({ nextLine.notNil }, { lastLine = nextLine; nextLine = pipe.getLine; });

    pipe.close;
    File.delete(sourceFile);

    lastLine
};

~apparentSourceVector = { | hrtfPath, index, precision |

    // the octave source code that computes what we want
    runSOFAroutine.([
        "hrtf = SOFAload('%');".format(hrtfPath.fullPath),
        "apv = SOFAcalculateAPV(hrtf);",
        "v = apv(%, :);".format(index),
        "printf('\\%.%f,\\%.%f,\\%.%f\\n', v(1), v(2), v(3));".format(
            precision, precision, precision),
    ])
    // split the output into list of values, collect as floats
    .split($,).collect({ | val | val.asFloat })
};

~closestSource = { | hrtfPath, azi, ele, r, precision |

    // the octave source code that computes what we want
    runSOFAroutine.([
        "hrtf = SOFAload('%');".format(hrtfPath.fullPath),
        "[idx, azi, ele, r] = SOFAfind(hrtf, %, %, %);".format(azi, ele, r),
        "printf('\\%d,\\%.%f,\\%.%f,\\%.%f\\n', idx, azi, ele, r);".format(
            precision, precision, precision),
    ])
    // split the output into list of values, collect as floats
    .split($,).collect({|val, i|
        if(i == 0, { val.asInteger }, { val.asFloat })
    })
};
)
