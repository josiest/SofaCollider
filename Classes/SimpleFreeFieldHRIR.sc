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
        var attributes;
        attributes = SofaInterface.prLoadSofaMetaData(filePath);
        ^super.new(\SimpleFreeFieldHRIR, filePath, attributes)
              .initFromAttributes(attributes);
    }

    initFromAttributes{ | attributes |

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