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

"globalConventions: ".post; ~sofaObj.globalConventions.postln;
"globalSofaConventions: ".post; ~sofaObj.globalSofaConventions.postln;
"globalSofaConventionsVersion: ".post; ~sofaObj.globalSofaConventionsVersion.postln;
"globalAPIName: ".post; ~sofaObj.globalAPIName.postln;
"globalAPIVersion: ".post; ~sofaObj.globalAPIVersion.postln;
"globalDataType: ".post; ~sofaObj.globalDataType.postln;
"globalRoomType: ".post; ~sofaObj.globalRoomType.postln;
"globalDateCreated: ".post; ~sofaObj.globalDateCreated.postln;
"globalDateModified: ".post; ~sofaObj.globalDateModified.postln;
"globalAuthorContact: ".post; ~sofaObj.globalAuthorContact.postln;
"globalOrganization: ".post; ~sofaObj.globalOrganization.postln;
"globalLicense: ".post; ~sofaObj.globalLicense.postln;
"globalTitle: ".post; ~sofaObj.globalTitle.postln;
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
