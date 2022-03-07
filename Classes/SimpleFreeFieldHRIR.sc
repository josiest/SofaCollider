SimpleFreeFieldHRIR : SofaConvention {
    var <globalDatabaseName;
    var <globalListenerShortName;

    var <listenerPositionType;
    var <listenerPositionUnits;

    var <receiverPositionType;
    var <receiverPositionUnits;

    var <sourcePositionType;
    var <sourcePositionUnits;

    var <emitterPositionType;
    var <emitterPositionUnits;

    var <listenerViewType;
    var <listenerViewUnits;

    var <dataSamplingRateUnits;

    *newFromFile{ | filePath |
        var attributes, convention;
        var spatialAttributeNames;

        // load everything from the sofa object except the data
        convention = \SimpleFreeFieldHRIR;
        attributes = SofaInterface.loadMetadata(filePath, convention);

        SofaInterface.spatialArrayNames(convention).do{ | name |
            var key;

            key = SofaInterface.attributeAsSymbol(name);
            ("== "++key++" ==").postln;
            attributes[key].postln;
        };

        "== attributes ==".postln;
        attributes.keysDo{ | key |  ("... " ++ key).postln; };

        ^super.new(filePath)
              .initMetadataFromAttributes(attributes)
              .initSimpleFreeFieldHRIRFromAttributes(attributes);
    }

    initSimpleFreeFieldHRIRFromAttributes{ | attributes |

        globalDatabaseName = attributes[\GLOBAL_DatabaseName];
        globalListenerShortName = attributes[\GLOBAL_ListenerShortName];
        listenerPositionType = attributes[\ListenerPositionType];
        listenerPositionUnits = attributes[\ListenerPositionUnits];
        receiverPositionType = attributes[\ReceiverPositionType];
        receiverPositionUnits = attributes[\ReceiverPositionUnits];
        sourcePositionType = attributes[\SourcePositionType];
        sourcePositionUnits = attributes[\SourcePositionUnits];
        emitterPositionType = attributes[\EmitterPositionType];
        emitterPositionUnits = attributes[\EmitterPositionUnits];
        listenerViewType = attributes[\ListenerViewType];
        listenerViewUnits = attributes[\ListenerViewUnits];
        dataSamplingRateUnits = attributes[\DataSamplingRateUnits];
    }
}
