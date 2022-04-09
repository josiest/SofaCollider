SofaColliderConfig {

    classvar <filepath;

    *initClass {
        filepath = Platform.userConfigDir +/+ "SofaCollider/sofa_interface.yml";
        postf("... filepath in SofaColliderConfig.initClass: %\n", filepath);
    }

    *sofaOctaveRepo {
        var settings;

        postf("... filepath in SofaColliderConfig.sofaOctaveRepo: %\n", filepath);
        settings = filepath.parseYAMLFile;
        ^settings[\sofaOctaveRepo]
    }

    *hrtfDataDir {
        var settings;

        postf("... filepath in SofaColliderConfig.hrtfDataDir: %\n", filepath);
        postf("... user config dir: %\n", Platform.userConfigDir);
        settings = filepath.parseYAMLFile;
        ^settings[\hrtfDataDir]
    }
}
