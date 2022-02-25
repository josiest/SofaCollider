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

// "metaData.Conventions: ".post; ~sofaObj.metaData.Conventions.postln;
// "metaData.SofaConventions: ".post; ~sofaObj.metaData.SofaConventions.postln;
// "metaData.SofaConventionsVersion: ".post;
// ~sofaObj.metaData.SofaConventionsVersion.postln;
// "metaData.APIName: ".post; ~sofaObj.metaData.APIName.postln;
// "metaData.APIVersion: ".post; ~sofaObj.metaData.APIVersion.postln;
// "metaData.DataType: ".post; ~sofaObj.metaData.DataType.postln;
// "metaData.RoomType: ".post; ~sofaObj.metaData.RoomType.postln;
// "metaData.DateCreated: ".post; ~sofaObj.metaData.DateCreated.postln;
// "metaData.DateModified: ".post; ~sofaObj.metaData.DateModified.postln;
// "metaData.AuthorContact: ".post; ~sofaObj.metaData.AuthorContact.postln;
// "metaData.Organization: ".post; ~sofaObj.metaData.Organization.postln;
// "metaData.License: ".post; ~sofaObj.metaData.License.postln;
// "metaData.Title: ".post; ~sofaObj.metaData.Title.postln;

~sofaObj.metaData.keysValuesDo{ | key, value | key.post; ": ".post; value.postln; };

"globalDatabaseName: ".post; ~sofaObj.globalDatabaseName.postln;
"globalListenerShortName: ".post; ~sofaObj.globalListenerShortName.postln;
"listenerPositionType: ".post; ~sofaObj.listenerPositionType.postln;
"listenerPositionUnits: ".post; ~sofaObj.listenerPositionUnits.postln;
"receiverPositionType: ".post; ~sofaObj.receiverPositionType.postln;
"receiverPositionUnits: ".post; ~sofaObj.receiverPositionUnits.postln;
"sourcePositionType: ".post; ~sofaObj.sourcePositionType.postln;
"sourcePositionUnits: ".post; ~sofaObj.sourcePositionUnits.postln;
"emitterPositionType: ".post; ~sofaObj.emitterPositionType.postln;
"emitterPositionUnits: ".post; ~sofaObj.emitterPositionUnits.postln;
"listenerViewPositionType: ".post; ~sofaObj.listenerViewType.postln;
"listenerViewPositionUnits: ".post; ~sofaObj.listenerViewUnits.postln;
"dataSamplingRateUnits: ".post; ~sofaObj.dataSamplingRateUnits.postln;
0.exit;
)
