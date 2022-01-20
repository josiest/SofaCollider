(
    "sofaInterface.scd".load;

    // arguments
    ~azi = 90;
    ~ele = 10;
    ~r = 13;
    ~precision = 10;

    // define the hrtfPath
    ~hrtfDir = ~sofaRoot +/+ "HRTFs/SOFA/sofa_api_mo_test";
    ~hrtfFilename = "CIPIC_subject_003_hrir_final.sofa";
    ~hrtfPath = ~hrtfDir +/+ ~hrtfFilename;

    // the octave source code that computes what we want
    ~sourceCode = [
        "hrtf = SOFAload('%');".format(~hrtfPath.fullPath),
        "[idx, azi, ele, r] = SOFAfind(hrtf, %, %, %);".format(~azi, ~ele, ~r),
        "printf('\\%d,\\%.%f,\\%.%f,\\%.%f\\n', idx, azi, ele, r);".format(
            ~precision, ~precision, ~precision),
    ];
    ~lastLine = ~runSOFAroutine.(~sourceCode);

    // split the output into list of values, collect as floats
    ~vals = ~lastLine.split($,).collect({|val, i|
        if(i == 0, { val.asInteger }, { val.asFloat })
    });
    ~vals.do({|val| val.postln });

    0.exit;
)
