Listener : SpatialArray{
    var <position;
    var <view;

    *new{ | coordinateType, unitType, positionVals, viewVals |
        ^super.new(coordinateType, unitType)
              .initPosition(positionVals)
              .initView(viewVals);
    }

    initPosition{ | vals |
        position = vals;
    }

    initView{ | vals |
        view = vals;
    }
}
