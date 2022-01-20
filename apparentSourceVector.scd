(
    // arguments
    var hrtfPath;
    var index;
    var precision;

    ~sofaRoot = PathName("/home/josiest/sofa/API_MO");
    ~sofaSrcDir = ~sofaRoot +/+ "API_MO";

    // define the hrtfPath
    ~hrtfDir = ~sofaRoot +/+ "HRTFs/SOFA/sofa_api_mo_test";
    ~hrtfFilename = "CIPIC_subject_003_hrir_final.sofa";
    hrtfPath = ~hrtfDir +/+ ~hrtfFilename;
    index = 1;
    precision = 4;

    // the octave unix command
    ~octaveCmd = "octave";
    ~sourceFile = "temp.m";

    // the octave source code that computes what we want
    ~sourceCode = [
        "warning('off', 'all');",
        "addpath('%');".format(~sofaSrcDir.fullPath),
        "SOFAstart;",
        "hrtf = SOFAload('%');".format(hrtfPath.fullPath),
        "apv = SOFAcalculateAPV(hrtf);",
        "v = apv(%, :);".format(index),
        "printf('\\%.%f,\\%.%f,\\%.%f\\n', v(1), v(2), v(3));".format(
            precision, precision, precision),
    ];
    File.use(~sourceFile, "w", {|fp|
        ~sourceCode.do({|line| fp.write(line++"\n"); });
    });

    // run the octave command, and pipe input into it
    ~p = Pipe.argv([~octaveCmd, ~sourceFile], "r");

    // // scan the output until the last line
    ~lastLine = ~p.getLine; ~nextLine = ~p.getLine;
    while ({~nextLine.notNil}, {~lastLine = ~nextLine; ~nextLine = ~p.getLine; });
    ~p.close;

    // split the output into list of values, collect as floats
    ~vals = ~lastLine.split($,).collect({|val| val.asFloat });
    ~vals.do({|val| val.postln });

    File.delete(~sourceFile);
    0.exit;
)
