(
"sofaInterface.scd".load;

// define the hrtfPath
~hrtfDir = ~sofaRoot +/+ "HRTFs/SOFA/sofa_api_mo_test";
~hrtfFilename = "CIPIC_subject_003_hrir_final.sofa";
~hrtfPath = ~hrtfDir +/+ ~hrtfFilename;

"closest source".postln;
~closestSource.(~hrtfPath, 89, 13, 2, 10)
    .do({ | val | val.postln; });

"source at".postln;
~sourceAt.(~hrtfPath, 91, 10)
    .do({ | val | val.postln; });

"IR at".postln;
~irAt.(~hrtfPath, 91, 10);

// ~metaData.(~hrtfPath);
// ~conventionsOf.(~hrtfPath);

0.exit;
)
