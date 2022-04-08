SofaColliderConfig {

    classvar <filepath;

    *initClass {
        filepath = Platform.userConfigDir +/+ "SofaCollider/sofa_interface.yml"
    }

    *sofaOctaveRepo {
        var settings;

        settings = SofaColliderConfig.filepath.parseYAMLFile;
        ^settings[\sofaOctaveRepo]
    }

    *hrtfDataDir {
        var settings;

        settings = SofaColliderConfig.filepath.parseYAMLFile;
        ^settings[\hrtfDataDir]
    }
}
