SimpleFreeFieldHRIR : SofaConvention {
    var <listener;
    var <receiver;
    var <source;
    var <emitter;

    *newFromFile{ | filePath |
        var attributes, convention;
        var spatialAttributeNames;

        // load everything from the sofa object except the data
        convention = \SimpleFreeFieldHRIR;
        attributes = SofaInterface.loadMetadata(filePath, convention);

        ^super.new(filePath)
              .initMetadataFromAttributes(attributes)
              .initListenerFromAttributes(attributes)
              .initReceiverFromAttributes(attributes)
              .initSourceFromAttributes(attributes)
              .initEmitterFromAttributes(attributes);
    }

    initListenerFromAttributes{ | attributes |
        var attrNames = SofaInterface.listenerAttributeNames;
        listener = Listener(attributes[attrNames.type],
                            attributes[attrNames.units],
                            attributes[attrNames.position],
                            attributes[attrNames.view]);
    }

    initReceiverFromAttributes{ | attributes |
        var attrNames;

        attrNames = SofaInterface.spatialAttributeNames(\ReceiverPosition);
        receiver = Receiver(attributes[attrNames.type],
                            attributes[attrNames.units],
                            attributes[attrNames.position]);
    }

    initSourceFromAttributes{ | attributes |
        var attrNames;
        attrNames = SofaInterface.spatialAttributeNames(\SourcePosition);
        source = Source(attributes[attrNames.type],
                        attributes[attrNames.units],
                        attributes[attrNames.position]);
    }

    initEmitterFromAttributes{ | attributes |
        var attrNames;

        attrNames = SofaInterface.spatialAttributeNames(\EmitterPosition);
        emitter = Emitter(attributes[attrNames.type],
                          attributes[attrNames.units],
                          attributes[attrNames.position]);
    }
}
