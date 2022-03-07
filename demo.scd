(
// define the hrtfPath
~hrtfDir = SofaInterface.rootDir ++ "HRTFs/SOFA/sofa_api_mo_test/";
~hrtfFilename = "CIPIC_subject_003_hrir_final.sofa";
~hrtfPath = ~hrtfDir ++ ~hrtfFilename;

// "closest source".postln;
// "path in demo: ".post;
// ~hrtfPath.postln;
// SofaInterface.closestSourceFromVector(~hrtfPath, [89, 13, 2])
//     .do({ | val | val.postln; });
//
// "source at".postln;
// ~sourceVectorFromIndex.(~hrtfPath, 91)
//     .do({ | val | val.postln; });
//
// "IR from index 91".postln;
// ~irFromIndex.(~hrtfPath, 91).postln;

// ~conventionsFromFile.(~hrtfPath).do{ | header, val |
//     "%s: %s".format(header, val).postln;
// };

~sofaObj = SimpleFreeFieldHRIR.newFromFile(~hrtfPath);

"== Loaded meta data ==".postln;
postf("... metadata.conventions: %\n", ~sofaObj.metadata.conventions);
postf("... metadata.sofaConventions: %\n", ~sofaObj.metadata.sofaConventions);
postf("... metadata.sofaConventionsVersion: %\n",
      ~sofaObj.metadata.sofaConventionsVersion);
postf("... metadata.apiName: %\n", ~sofaObj.metadata.apiName);
postf("... metadata.apiVersion: %\n", ~sofaObj.metadata.apiVersion);
postf("... metadata.dataType: %\n", ~sofaObj.metadata.dataType);
postf("... metadata.roomType: %\n", ~sofaObj.metadata.roomType);
postf("... metadata.dateCreated: %\n", ~sofaObj.metadata.dateCreated);
postf("... metadata.dateModified: %\n", ~sofaObj.metadata.dateModified);
postf("... metadata.authorContact: %\n", ~sofaObj.metadata.authorContact);
postf("... metadata.organization: %\n", ~sofaObj.metadata.organization);
postf("... metadata.license: %\n", ~sofaObj.metadata.license);
postf("... metadata.title: %\n", ~sofaObj.metadata.title);
postf("... metadata.databaseName: %\n", ~sofaObj.metadata.databaseName);
postf("... metadata.listenerShortName: %\n",
      ~sofaObj.metadata.listenerShortName);

// "... listenerPositionType: ".post;
// ~sofaObj.listenerPositionType.postln;
// "... listenerPositionUnits: ".post;
// ~sofaObj.listenerPositionUnits.postln;
"== Receiver ==".postln;
postf("... receiver.isCartesian: %\n", ~sofaObj.receiver.isCartesian);
postf("... receiver.isSpherical: %\n", ~sofaObj.receiver.isSpherical);
postf("... receiver.units: %\n", ~sofaObj.receiver.units);
postf("... receiver.position: %\n", ~sofaObj.receiver.position);
// "... sourcePositionType: ".post;
// ~sofaObj.sourcePositionType.postln;
// "... sourcePositionUnits: ".post;
// ~sofaObj.sourcePositionUnits.postln;
"== Emitter ==".postln;
postf("... emitter.isCartesian: %\n", ~sofaObj.emitter.isCartesian);
postf("... emitter.isSpherical: %\n", ~sofaObj.emitter.isSpherical);
postf("... emitter.units: %\n", ~sofaObj.emitter.units);
postf("... emitter.position: %\n", ~sofaObj.emitter.position);
// "... listenerViewPositionType: ".post;
// ~sofaObj.listenerViewType.postln;
// "... listenerViewPositionUnits: ".post;
// ~sofaObj.listenerViewUnits.postln;
// "... dataSamplingRateUnits: ".post;
// ~sofaObj.dataSamplingRateUnits.postln;
0.exit;
)
