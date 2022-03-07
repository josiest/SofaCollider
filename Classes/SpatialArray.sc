SpatialArray {
    var <isCartesian;
    var <isSpherical;
    var <units;

    *new{ | coordinateType, unitType, positionVals |

        ^super.newCopyArgs(coordinateType.toLower == "cartesian",
                           coordinateType.toLower == "spherical",
                           unitType.asSymbol);
    }
}
