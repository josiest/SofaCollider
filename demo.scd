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

"sofa object: ".postln;
~sofaObj = SimpleFreeFieldHRIR.newFromFile(~hrtfPath);

"== Loaded meta data ==".postln;
"... metadata.conventions: ".post;
~sofaObj.metadata.conventions.postln;
"... metadata.sofaConventions: ".post;
~sofaObj.metadata.sofaConventions.postln;
"... metadata.sofaConventionsVersion: ".post;
~sofaObj.metadata.sofaConventionsVersion.postln;
"... metadata.apiName: ".post;
~sofaObj.metadata.apiName.postln;
"... metadata.apiVersion: ".post;
~sofaObj.metadata.apiVersion.postln;
"... metadata.dataType: ".post;
~sofaObj.metadata.dataType.postln;
"... metadata.roomType: ".post;
~sofaObj.metadata.roomType.postln;
"... metadata.dateCreated: ".post;
~sofaObj.metadata.dateCreated.postln;
"... metadata.dateModified: ".post;
~sofaObj.metadata.dateModified.postln;
"... metadata.authorContact: ".post;
~sofaObj.metadata.authorContact.postln;
"... metadata.organization: ".post;
~sofaObj.metadata.organization.postln;
"... metadata.license: ".post;
~sofaObj.metadata.license.postln;
"... metadata.title: ".post;
~sofaObj.metadata.title.postln;
"... metadata.databaseName: ".post;
~sofaObj.metadata.databaseName.postln;
"... metadata.listenerShortName: ".post;
~sofaObj.metadata.listenerShortName.postln;

"== Loaded spatial arrays ==".postln;
"... listenerPositionType: ".post;
~sofaObj.listenerPositionType.postln;
"... listenerPositionUnits: ".post;
~sofaObj.listenerPositionUnits.postln;
"... receiverPositionType: ".post;
~sofaObj.receiverPositionType.postln;
"... receiverPositionUnits: ".post;
~sofaObj.receiverPositionUnits.postln;
"... sourcePositionType: ".post;
~sofaObj.sourcePositionType.postln;
"... sourcePositionUnits: ".post;
~sofaObj.sourcePositionUnits.postln;
"... emitterPositionType: ".post;
~sofaObj.emitterPositionType.postln;
"... emitterPositionUnits: ".post;
~sofaObj.emitterPositionUnits.postln;
"... listenerViewPositionType: ".post;
~sofaObj.listenerViewType.postln;
"... listenerViewPositionUnits: ".post;
~sofaObj.listenerViewUnits.postln;
"... dataSamplingRateUnits: ".post;
~sofaObj.dataSamplingRateUnits.postln;
0.exit;
)
