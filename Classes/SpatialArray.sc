SpatialArray {
    var <isCartesian;
    var <isSpherical;
    var <units;

    *new{ | coordinateType, unitType |

        ^super.newCopyArgs(coordinateType.toLower == "cartesian",
                           coordinateType.toLower == "spherical",
                           unitType.asSymbol);
    }
}
