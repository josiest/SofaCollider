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
        var attrNames;
        attrNames = SofaInterface.spatialAttributeNames(\ListenerPosition);
        listenerPosition = SpatialArray(attributes[attrNames.type],
                                        attributes[attrNames.units],
                                        attributes[attrNames.position]);
    }

    initReceiverPositionFromAttributes{ | attributes |
        var attrNames;
        attrNames = SofaInterface.spatialAttributeNames(\ReceiverPosition);
        receiverPosition = SpatialArray(attributes[attrNames.type],
                                        attributes[attrNames.units],
                                        attributes[attrNames.position]);
    }

    initSourcePositionFromAttributes{ | attributes |
        var attrNames;
        attrNames = SofaInterface.spatialAttributeNames(\SourcePosition);
        sourcePosition = SpatialArray(attributes[attrNames.type],
                                      attributes[attrNames.units],
                                      attributes[attrNames.position]);
    }

    initEmitterPositionFromAttributes{ | attributes |
        var attrNames;
        attrNames = SofaInterface.spatialAttributeNames(\EmitterPosition);
        emitterPosition = SpatialArray(attributes[attrNames.type],
                                       attributes[attrNames.units],
                                       attributes[attrNames.position]);
    }

    initListenerViewFromAttributes { | attributes |
        var attrNames;
        attrNames = SofaInterface.spatialAttributeNames(\ListenerView);
        listenerView = SpatialArray(attributes[attrNames.type],
                                    attributes[attrNames.units],
                                    attributes[attrNames.position]);
    }

    initListenerUpFromAttributes { | attributes |
        var attrNames;
        attrNames = SofaInterface.spatialAttributeNames(\ListenerUp);
        listenerUp = SpatialArray(attributes[attrNames.type],
                                  attributes[attrNames.units],
                                  attributes[attrNames.position]);
    }

    databaseName{ ^prGlobalMetadata.databaseName; }
    listenerShortName{ ^prGlobalMetadata.listenerShortName; }
}
