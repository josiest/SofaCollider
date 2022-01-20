(
"sofaInterface.scd".load;

// define the hrtfPath
~hrtfDir = ~sofaRoot +/+ "HRTFs/SOFA/sofa_api_mo_test";
~hrtfFilename = "CIPIC_subject_003_hrir_final.sofa";
~hrtfPath = ~hrtfDir +/+ ~hrtfFilename;

~closestSource.(~hrtfPath, 89, 13, 2, 10)
    .do({ | val | val.postln; });

0.exit;
)
