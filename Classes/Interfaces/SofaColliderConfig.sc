SofaColliderConfig {
    classvar <configPath;

    *initClass {
        configPath = Platform.userConfigDir
                 +/+ "SofaCollider/sofa_interface.yml";
    }

    *sofaOctaveRepo {
        var key, default;
        key = "sofaOctaveRepo";
        default = Platform.userAppSupportDir.standardizePath
                          .split($/).drop(-1).join($/);
        default = default +/+ "octave/packages/sofa";
        ^SofaColliderConfig.parsePath(key, default);
    }

    *hrtfDataDir {
        var key, default;
        key = "hrtfDataDir";
        default = SofaColliderConfig.sofaOctaveRepo +/+ "HRTFs";
        ^SofaColliderConfig.parsePath(key, default);
    }

    *parsePath { | key, default |
        var path, settings, parseSuccess;

        // use a default path in case parsing fails
        path = default;

        // parse the settings file to see if
        // the hrtf data directory has been specified
        if (configPath.isNil, { SofaColliderConfig.initClass; });
        settings = configPath.parseYAMLFile;
        parseSuccess = settings.notNil;
        if (parseSuccess, { parseSuccess = settings.includesEquals(key)});

        // if it has, use the specified directory instead of default
        if (parseSuccess, { path = settings.atAssociation(key); });
        ^path
    }
}
