Sadie : Database {

    classvar <prRootURL;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootURL = "https://sofacoustics.org/data/database/sadie";
        prSubjectFmt = "H%_48K_24bit_256tap_FIR_SOFA.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "SADIE";

        // make the database directory if it doesn't exist
        prLocalRoot = prLocalRoot.standardizePath;
        if (File.exists(prLocalRoot).not, {
            File.mkdir(prLocalRoot);
        });
    }

    // The url of the database online
    *rootURL { ^prRootURL; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        ^Sadie.prSubjectFmt.format(id)
    }

    // Get the url of a subject
    *subjectURL{ | id |
        ^(Sadie.rootURL ++ "/" ++ Sadie.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(Sadie.localRoot +/+ Sadie.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = Sadie.subjectURL(id);

        if (path.isNil, {
            path = Sadie.subjectLocalPath(id);
        });
        ^Database.downloadSubject(url, path);
    }
}
