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

"== Global Metadata ==".postln;
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

"== Listener ==".postln;
postf("... listener.isCartesian: %\n", ~sofaObj.listener.isCartesian);
postf("... listener.isSpherical: %\n", ~sofaObj.listener.isSpherical);
postf("... listener.units: %\n", ~sofaObj.listener.units);
postf("... listener.position: %\n", ~sofaObj.listener.position);
postf("... listener.view: %\n", ~sofaObj.listener.view);

"== Receiver ==".postln;
postf("... receiver.isCartesian: %\n", ~sofaObj.receiver.isCartesian);
postf("... receiver.isSpherical: %\n", ~sofaObj.receiver.isSpherical);
postf("... receiver.units: %\n", ~sofaObj.receiver.units);
postf("... receiver.left: %\n", ~sofaObj.receiver.left);
postf("... receiver.right: %\n", ~sofaObj.receiver.right);

"== Source ==".postln;
postf("... source.isCartesian: %\n", ~sofaObj.source.isCartesian);
postf("... source.isSpherical: %\n", ~sofaObj.source.isSpherical);
postf("... source.units: %\n", ~sofaObj.source.units);
postf("... source.positions.size: %\n", ~sofaObj.source.positions.size);
postf("... source.positions[0]: %\n", ~sofaObj.source.positions[0]);
postf("... source.positions[%]: %\n",
      ~sofaObj.source.positions.size-1,
      ~sofaObj.source.positions[~sofaObj.source.positions.size-1]);

"== Emitter ==".postln;
postf("... emitter.isCartesian: %\n", ~sofaObj.emitter.isCartesian);
postf("... emitter.isSpherical: %\n", ~sofaObj.emitter.isSpherical);
postf("... emitter.units: %\n", ~sofaObj.emitter.units);
postf("... emitter.position: %\n", ~sofaObj.emitter.position);

0.exit;
)
