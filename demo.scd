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
"... metaData.conventions: ".post;
~sofaObj.metaData.conventions.postln;
"... metaData.sofaConventions: ".post;
~sofaObj.metaData.sofaConventions.postln;
"... metaData.sofaConventionsVersion: ".post;
~sofaObj.metaData.sofaConventionsVersion.postln;
"... metaData.apiName: ".post;
~sofaObj.metaData.apiName.postln;
"... metaData.apiVersion: ".post;
~sofaObj.metaData.apiVersion.postln;
"... metaData.dataType: ".post;
~sofaObj.metaData.dataType.postln;
"... metaData.roomType: ".post;
~sofaObj.metaData.roomType.postln;
"... metaData.dateCreated: ".post;
~sofaObj.metaData.dateCreated.postln;
"... metaData.dateModified: ".post;
~sofaObj.metaData.dateModified.postln;
"... metaData.authorContact: ".post;
~sofaObj.metaData.authorContact.postln;
"... metaData.organization: ".post;
~sofaObj.metaData.organization.postln;
"... metaData.license: ".post;
~sofaObj.metaData.license.postln;
"... metaData.title: ".post;
~sofaObj.metaData.title.postln;
"... metaData.databaseName: ".post;
~sofaObj.metaData.databaseName.postln;
"... metaData.listenerShortName: ".post;
~sofaObj.metaData.listenerShortName.postln;

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
