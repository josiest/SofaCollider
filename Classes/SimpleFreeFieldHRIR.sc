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

        "== Initializing Emitter ==".postln;

        attrNames = SofaInterface.spatialAttributeNames(\EmitterPosition);
        if (attributes.includesKey(attrNames.type), {
            ("... "++attrNames.type++" exists in attributes").postln;
        }, {
            ("... "++attrNames.type++" doesn't exist in attributes!").postln;
        });
        "... coordinate type".postln
        ("...... key: "++attrNames.type).postln;
        ("...... value: "++attributes[attrNames.type]).postln;
        "... unit type".postln
        ("...... key: "++attrNames.units).postln;
        ("...... value: "++attributes[attrNames.units]).postln;
        attributes.keysValuesDo{ | key, value |
            postf("%: %\n", key, value);
        };

        emitter = SpatialArray(attributes[attrNames.type],
                               attributes[attrNames.units],
                               attributes[attrNames.position]);
    }
}
