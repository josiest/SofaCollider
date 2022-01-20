(
var runSOFAroutine;

// define where to find the sofa source repository
~sofaRoot = PathName("/home/josiest/sofa/API_MO");
~sofaSrcDir = ~sofaRoot +/+ "API_MO";

runSOFAroutine = { | hrtfPath, source |
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
        "hrtf = SOFAload('%');".format(hrtfPath.fullPath),
        "printf('SuperCollider Data Interface\\n');",
    ] ++ source;

    // write the source code to the temporary file
    File.use(sourceFile, "w", { | fp |
        allSourceCode.do({ | line | fp.write(line ++ "\n"); });
    });

    // run the octave command, and pipe input into it
    pipe = Pipe.argv([octaveCmd, sourceFile], "r");

    // scan the output until the last line
    lastLine = pipe.getLine; nextLine = pipe.getLine;
    while ({ lastLine != "SuperCollider Data Interface" }, {
        lastLine = nextLine; nextLine = pipe.getLine;
    });
    while ({ nextLine.notNil }, {
        output = output ++ nextLine ++ "\n";
        nextLine = pipe.getLine;
    });

    pipe.close;
    File.delete(sourceFile);

    output
};

~sourceAt = { | hrtfPath, index, precision |

    // the octave source code that computes what we want
    runSOFAroutine.(hrtfPath, [
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
    .postln;
};
)
