(
    "sofaInterface.scd".load;

    // arguments
    ~index = 1;
    ~precision = 10;

    // define the hrtfPath
    ~hrtfDir = ~sofaRoot +/+ "HRTFs/SOFA/sofa_api_mo_test";
    ~hrtfFilename = "CIPIC_subject_003_hrir_final.sofa";
    ~hrtfPath = ~hrtfDir +/+ ~hrtfFilename;

    // the octave source code that computes what we want
    ~sourceCode = [
        "hrtf = SOFAload('%');".format(~hrtfPath.fullPath),
        "apv = SOFAcalculateAPV(hrtf);",
        "v = apv(%, :);".format(~index),
        "printf('\\%.%f,\\%.%f,\\%.%f\\n', v(1), v(2), v(3));".format(
            ~precision, ~precision, ~precision),
    ];
    ~lastLine = ~runSOFAroutine.(~sourceCode);

    // split the output into list of values, collect as floats
    ~vals = ~lastLine.split($,).collect({|val| val.asFloat });
    ~vals.do({|val| val.postln });

    0.exit;
)
