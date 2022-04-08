(
"=== CIPIC Database ===".postln;
postf("... CIPIC.subjectUrl(3): %\n", CIPIC.subjectUrl(3));
postf("... CIPIC.subjectLocalPath(3): %\n", CIPIC.subjectLocalPath(3));
CIPIC.downloadSubject(3);

"=== Listen Database ===".postln;
postf("... Listen.subjectUrl(1002): %\n", Listen.subjectUrl(1002));
postf("... Listen.subjectLocalPath(1002): %\n", Listen.subjectLocalPath(1002));
Listen.downloadSubject(1002);

// ~hrtf = SimpleFreeFieldHRIR.newFromFile(~hrtfPath);
// 
// "== Global Metadata ==".postln;
// postf("... hrtf.convention: %\n", ~hrtf.convention);
// postf("... hrtf.version: %\n", ~hrtf.version);
// postf("... hrtf.sofaConvention: %\n", ~hrtf.sofaConvention);
// postf("... hrtf.sofaConventionVersion: %\n",
//       ~hrtf.sofaConventionVersion);
// postf("... hrtf.apiName: %\n", ~hrtf.apiName);
// postf("... hrtf.apiVersion: %\n", ~hrtf.apiVersion);
// postf("... hrtf.dataType: %\n", ~hrtf.dataType);
// postf("... hrtf.roomType: %\n", ~hrtf.roomType);
// postf("... hrtf.dateCreated: %\n", ~hrtf.dateCreated);
// postf("... hrtf.dateModified: %\n", ~hrtf.dateModified);
// postf("... hrtf.authorContact: %\n", ~hrtf.authorContact);
// postf("... hrtf.organization: %\n", ~hrtf.organization);
// postf("... hrtf.license: %\n", ~hrtf.license);
// postf("... hrtf.title: %\n", ~hrtf.title);
// postf("... hrtf.databaseName: %\n", ~hrtf.databaseName);
// postf("... hrtf.listenerShortName: %\n",
//       ~hrtf.listenerShortName);
// 
// "== Listener ==".postln;
// postf("... hrtf.listenerPosition.isCartesian: %\n",
//       ~hrtf.listenerPosition.isCartesian);
// postf("... hrtf.listenerPosition.isSpherical: %\n",
//       ~hrtf.listenerPosition.isSpherical);
// postf("... hrtf.listenerPosition.units: %\n",
//       ~hrtf.listenerPosition.units);
// postf("... hrtf.listenerPosition.at(0): %\n",
//       ~hrtf.listenerPosition.at(0));
// 
// "== ListenerView ==".postln;
// postf("... hrtf.listenerView.isCartesian: %\n",
//       ~hrtf.listenerView.isCartesian);
// postf("... hrtf.listenerView.isSpherical: %\n",
//       ~hrtf.listenerView.isSpherical);
// postf("... hrtf.listenerView.units: %\n",
//       ~hrtf.listenerView.units);
// postf("... hrtf.listenerView.at(0): %\n",
//       ~hrtf.listenerView.at(0));
// 
// "== Receiver ==".postln;
// postf("... hrtf.receiverPosition.isCartesian: %\n",
//       ~hrtf.receiverPosition.isCartesian);
// postf("... hrtf.receiverPosition.isSpherical: %\n",
//       ~hrtf.receiverPosition.isSpherical);
// postf("... hrtf.receiverPosition.units: %\n",
//       ~hrtf.receiverPosition.units);
// postf("... hrtf.receiverPosition.at(0): %\n",
//       ~hrtf.receiverPosition.at(0));
// 
// "== Source ==".postln;
// postf("... hrtf.sourcePosition.isCartesian: %\n",
//       ~hrtf.sourcePosition.isCartesian);
// postf("... hrtf.sourcePosition.isSpherical: %\n",
//       ~hrtf.sourcePosition.isSpherical);
// postf("... hrtf.sourcePosition.units: %\n",
//       ~hrtf.sourcePosition.units);
// postf("... hrtf.sourcePosition.size: %\n",
//       ~hrtf.sourcePosition.size);
// postf("... hrtf.sourcePosition.at(0): %\n",
//       ~hrtf.sourcePosition.at(0));
// postf("... hrtf.sourcePosition.at(%): %\n",
//       ~hrtf.sourcePosition.size-1,
//       ~hrtf.sourcePosition.at(~hrtf.sourcePosition.size-1));
// 
// "== Emitter ==".postln;
// postf("... hrtf.emitterPosition.isCartesian: %\n",
//       ~hrtf.emitterPosition.isCartesian);
// postf("... hrtf.emitterPosition.isSpherical: %\n",
//       ~hrtf.emitterPosition.isSpherical);
// postf("... hrtf.emitterPosition.units: %\n",
//       ~hrtf.emitterPosition.units);
// postf("... hrtf.emitterPosition.at(0): %\n",
//       ~hrtf.emitterPosition.at(0));

0.exit;
)
