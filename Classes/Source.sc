Source : SpatialArray{
    var <positions;

    *new{ | coordinateType, unitType, positionVals |
        ^super.new(coordinateType, unitType)
              .initPositions(positionVals);
    }

    initPositions{ | vals |
        positions = vals;
    }
}
