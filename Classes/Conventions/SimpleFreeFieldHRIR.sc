SimpleFreeFieldHRIR : SofaConvention {
    var <listenerPosition;
    var <receiverPosition;
    var <sourcePosition;
    var <emitterPosition;
    var <listenerView;
    var <listenerUp;

    *newFromFile{ | filePath |
        var attributes, convention;
        var spatialAttributeNames;

        // load everything from the sofa object except the data
        convention = \SimpleFreeFieldHRIR;
        attributes = SofaInterface.loadMetadata(filePath, convention);

        ^super.new(filePath)
              .initGlobalMetadata(attributes)
              .initListenerPositionFromAttributes(attributes)
              .initReceiverPositionFromAttributes(attributes)
              .initSourcePositionFromAttributes(attributes)
              .initEmitterPositionFromAttributes(attributes)
              .initListenerViewFromAttributes(attributes);
    }

    initListenerPositionFromAttributes{ | attributes |
        listenerPosition = SpatialArray.prNewFromAttrs(attributes, \ListenerPosition);
    }

    initReceiverPositionFromAttributes{ | attributes |
        receiverPosition = SpatialArray.prNewFromAttrs(attributes, \ReceiverPosition);
    }

    initSourcePositionFromAttributes{ | attributes |
        sourcePosition = SpatialArray.prNewFromAttrs(attributes, \SourcePosition);
    }

    initEmitterPositionFromAttributes{ | attributes |
        emitterPosition = SpatialArray.prNewFromAttrs(attributes, \EmitterPosition);
    }

    initListenerViewFromAttributes { | attributes |
        listenerView = SpatialArray.prNewFromAttrs(attributes, \ListenerView);
    }

    initListenerUpFromAttributes { | attributes |
        listenerUp = SpatialArray.prNewFromAttrs(attributes, \ListenerUp);
    }

    databaseName{ ^prGlobalMetadata.databaseName; }
    listenerShortName{ ^prGlobalMetadata.listenerShortName; }
}
