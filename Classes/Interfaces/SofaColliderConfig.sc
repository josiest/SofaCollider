// A set of functions for interfacing with the SofaCollider Quark configuration
SofaColliderConfig {
    classvar <configDir;
    classvar <configPath;

    *initClass {
        configDir = Platform.userConfigDir +/+ "SofaCollider";
        configPath = configDir +/+ "sofa_interface.yml";

        // create the config file if it doesn't exist
        if (File.exists(configPath).not, {
            File.mkdir(configDir);
            File.use(configPath, "w", { | fp | });
        });
    }

    *octaveCmd {
        ^SofaColliderConfig.prParsePath("octaveCmd", "octave");
    }
    *octaveCmd_{ | path |
        SofaColliderConfig.prWriteSetting("octaveCmd", path);
    }

    // The directory of the SOFA Matlab/Octave API
    *sofaOctaveRepo {
        var key, default;
        key = "sofaOctaveRepo";

        // get the parent directory of the supercollider app suppport dir
        default = Platform.userAppSupportDir.standardizePath
                          .split($/).drop(-1).join($/);

        // the sofa package should be installed in octave/packages
        // relative to the ".local/share" directory
        default = default +/+ "octave/packages/sofa";
        ^SofaColliderConfig.prParsePath(key, default);
    }
    *sofaOctaveRepo_{ | path |
        SofaColliderConfig.prWriteSetting("sofaOctaveRepo", path);
    }

    // The root directory for various HRTF databases
    *hrtfDataDir {
        var key, default;
        key = "hrtfDataDir";
        default = SofaColliderConfig.sofaOctaveRepo +/+ "HRTFs";
        ^SofaColliderConfig.prParsePath(key, default);
    }
    *hrtfDataDir_{ | path |
        SofaColliderConfig.prWriteSetting("hrtfDataDir", path);
    }

    *prParsePath { | key, default |
        var path, settings, parseSuccess;

        // use a default path in case parsing fails
        path = default;

        // parse the settings file to see if
        // the hrtf data directory has been specified
        if (configPath.isNil, { SofaColliderConfig.initClass; });
        settings = configPath.parseYAMLFile;
        parseSuccess = settings.notNil;
        if (parseSuccess, { parseSuccess = settings.includesKey(key)});

        // if it has, use the specified directory instead of default
        if (parseSuccess, { path = settings.at(key); });
        ^path
    }

    *prWriteSetting{ | key, value |
        var settings;

        // make sure the class has been initialized
        if (configPath.isNil, { SofaColliderConfig.initClass; });

        // try to parse the settings file, default to empty dict
        settings = Dictionary.newFrom(configPath.parseYAMLFile);
        if (settings.isNil, { settings = Dictionary(); });

        // update the settings and write to the config file
        settings.put(key, value);
        File.use(configPath, "w", { | fp |
            settings.keysValuesDo({ | setting, settingValue |
                fp.write(key ++ ": " ++ value ++ "\n");
            });
        });
    }
}
