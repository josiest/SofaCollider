(
"sofaInterface.scd".load;

// define the hrtfPath
~hrtfDir = ~sofaRoot +/+ "HRTFs/SOFA/sofa_api_mo_test";
~hrtfFilename = "CIPIC_subject_003_hrir_final.sofa";
~hrtfPath = ~hrtfDir +/+ ~hrtfFilename;

// "closest source".postln;
// ~closestSourceFromVector.(~hrtfPath, [89, 13, 2])
//     .do({ | val | val.postln; });
// 
// "source at".postln;
// ~sourceVectorFromIndex.(~hrtfPath, 91)
//     .do({ | val | val.postln; });
// 
// "IR from index 91".postln;
// ~irFromIndex.(~hrtfPath, 91).postln;

~conventionsFromFile.(~hrtfPath).do{ | header, val |
    "%s: %s".format(header, val).postln;
};

"sofa object:".postln;
~newSimpleFreeFieldHRIRFromFile.(~hrtfPath)
    .pairsDo{ | attr, val | "%: %".format(attr, val).postln; };
0.exit;
)
