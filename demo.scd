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

~hrtf = SimpleFreeFieldHRIR.newFromFile(~hrtfPath);

"== Global Metadata ==".postln;
postf("... hrtf.convention: %\n", ~hrtf.convention);
postf("... hrtf.version: %\n", ~hrtf.version);
postf("... hrtf.sofaConvention: %\n", ~hrtf.sofaConvention);
postf("... hrtf.sofaConventionVersion: %\n",
      ~hrtf.sofaConventionVersion);
postf("... hrtf.apiName: %\n", ~hrtf.apiName);
postf("... hrtf.apiVersion: %\n", ~hrtf.apiVersion);
postf("... hrtf.dataType: %\n", ~hrtf.dataType);
postf("... hrtf.roomType: %\n", ~hrtf.roomType);
postf("... hrtf.dateCreated: %\n", ~hrtf.dateCreated);
postf("... hrtf.dateModified: %\n", ~hrtf.dateModified);
postf("... hrtf.authorContact: %\n", ~hrtf.authorContact);
postf("... hrtf.organization: %\n", ~hrtf.organization);
postf("... hrtf.license: %\n", ~hrtf.license);
postf("... hrtf.title: %\n", ~hrtf.title);
postf("... hrtf.databaseName: %\n", ~hrtf.databaseName);
postf("... hrtf.listenerShortName: %\n",
      ~hrtf.listenerShortName);

"== Listener ==".postln;
postf("... hrtf.listener.isCartesian: %\n", ~hrtf.listener.isCartesian);
postf("... hrtf.listener.isSpherical: %\n", ~hrtf.listener.isSpherical);
postf("... hrtf.listener.units: %\n", ~hrtf.listener.units);
postf("... hrtf.listener.position: %\n", ~hrtf.listener.position);
postf("... hrtf.listener.view: %\n", ~hrtf.listener.view);

"== Receiver ==".postln;
postf("... hrtf.receiver.isCartesian: %\n", ~hrtf.receiver.isCartesian);
postf("... hrtf.receiver.isSpherical: %\n", ~hrtf.receiver.isSpherical);
postf("... hrtf.receiver.units: %\n", ~hrtf.receiver.units);
postf("... hrtf.receiver.left: %\n", ~hrtf.receiver.left);
postf("... hrtf.receiver.right: %\n", ~hrtf.receiver.right);

"== Source ==".postln;
postf("... hrtf.source.isCartesian: %\n", ~hrtf.source.isCartesian);
postf("... hrtf.source.isSpherical: %\n", ~hrtf.source.isSpherical);
postf("... hrtf.source.units: %\n", ~hrtf.source.units);
postf("... hrtf.source.positions.size: %\n", ~hrtf.source.positions.size);
postf("... hrtf.source.positions[0]: %\n", ~hrtf.source.positions[0]);
postf("... hrtf.source.positions[%]: %\n",
      ~hrtf.source.positions.size-1,
      ~hrtf.source.positions[~hrtf.source.positions.size-1]);

"== Emitter ==".postln;
postf("... hrtf.emitter.isCartesian: %\n", ~hrtf.emitter.isCartesian);
postf("... hrtf.emitter.isSpherical: %\n", ~hrtf.emitter.isSpherical);
postf("... hrtf.emitter.units: %\n", ~hrtf.emitter.units);
postf("... hrtf.emitter.position: %\n", ~hrtf.emitter.position);

0.exit;
)
