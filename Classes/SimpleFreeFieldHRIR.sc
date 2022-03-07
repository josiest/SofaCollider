SimpleFreeFieldHRIR : SofaConvention {
    var <listenerPositionType;
    var <listenerPositionUnits;

    var <receiverPositionType;
    var <receiverPositionUnits;

    var <sourcePositionType;
    var <sourcePositionUnits;

    var <emitter;

    var <listenerViewType;
    var <listenerViewUnits;

    *newFromFile{ | filePath |
        var attributes, convention;
        var spatialAttributeNames;

        // load everything from the sofa object except the data
        convention = \SimpleFreeFieldHRIR;
        attributes = SofaInterface.loadMetadata(filePath, convention);

        ^super.new(filePath)
              .initMetadataFromAttributes(attributes)
              .initEmitterFromAttributes(attributes);
    }

    initListenerFromAttributes{ | attributes |
    }

    initReceiverFromAttributes{ | attributes |
    }

    initSourceFromAttributes{ | attributes |
    }

    initEmitterFromAttributes{ | attributes |
        var attrNames;

        attrNames = SofaInterface.spatialAttributeNames(\EmitterPosition);
        emitter = SpatialArray(attributes[attrNames.type],
                               attributes[attrNames.units],
                               attributes[attrNames.position]);
    }
}
