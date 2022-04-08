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
}
