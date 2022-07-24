SpatialArray {
    var <isCartesian;
    var <isSpherical;
    var <units;
    var <values;

    *new{ | coordinateType, unitType, positionVals |

        ^super.newCopyArgs(coordinateType.toLower == "cartesian",
                           coordinateType.toLower == "spherical",
                           unitType.asSymbol,
                           positionVals);
    }

    at{ | i | ^values[i] }
    size{ ^values.size }

    // Create a new spatial array from an attribute dictionary and a dictionary
    // keys that map into the attributes
    *prNewFromAttrs{ | attributes, arrayName |
        var attrNames;

        // make sure the attributes dictionary has the necessary attributes for
        // the array name that was specified
        attrNames = SofaInterface.spatialAttributeNames(arrayName);
        if (SpatialArray.prValidateAttrs(attributes, arrayName).not, {
            ^nil
        });

        ^SpatialArray.new(attributes[attrNames.type],
                          attributes[attrNames.units],
                          attributes[attrNames.position])
    }

    // make sure that the attributes dictionary has
    // all the keys outlined by the keys computed from arrayName
    //
    // print an error message and return false if it's missing any

    *prValidateAttrs{ | attributes, arrayName |
        var attrNames, missingAttrs;

        if (attributes.isEmpty, {
            ("SofaCollider Error: Tried to validate attribute,\n" ++
             "  but the dictionary is empty")
            .postln;

            ^false;
        });

        missingAttrs = List.new(3);
        attrNames = SofaInterface.spatialAttributeNames(arrayName);
        // check that the attrNames dicitonary has the right keys
        if (attrNames.includesKey(attrNames.type).not, {
            missingAttrs.add(attrNames.type);
        });
        if (attrNames.includesKey(attrNames.units).not, {
            missingAttrs.add(attrNames.units);
        });
        if (attrNames.includesKey(attrNames.position).not, {
            missingAttrs.add(attrNames.position);
        });

        // if no missing attributes, validation successful
        if (missingAttrs.isEmpty, {
            ^true
        });

        // otherwise print out which attributes are missing
        ("SofaCollider Error: Tried to validate attribute names,\n" ++
         "  but the dictionary is missing the attributes:\n" ++
         "  %")
          .format(missingAttrs.join(", "))
          .postln;

        ^false
    }
}
