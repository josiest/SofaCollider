Receiver : SpatialArray {
    var <left;
    var <right;

    *new{ | coordinateType, unitType, positionVals |
        ^super.new(coordinateType, unitType)
              .initLeftPosition(positionVals)
              .initRightPosition(positionVals);
    }

    initLeftPosition{ | vals |
        left = vals[0];
    }

    initRightPosition{ | vals |
        right = vals[1];
    }
}
