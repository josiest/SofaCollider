Emitter : SpatialArray {
    var <position;

    *new{ | coordinateType, unitType, positionVals |
        ^super.new(coordinateType, unitType)
              .initPosition(positionVals);
    }

    initPosition{ | vals |
        position = vals;
    }
}
