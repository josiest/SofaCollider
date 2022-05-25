RIEC : Database {

    classvar <prRootURL;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootURL = "https://sofacoustics.org/data/database/riec";
        prSubjectFmt = "RIEC_hrir_subject_%.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "RIEC";

        // make the database directory if it doesn't exist yet
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
        var padded;
        padded = id.asStringToBase(10, 3);
        ^RIEC.prSubjectFmt.format(padded)
    }

    // Get the url of a subject
    *subjectURL{ | id |
        ^(RIEC.rootURL ++ "/" ++ RIEC.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(RIEC.localRoot +/+ RIEC.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = RIEC.subjectURL(id);

        if (path.isNil, {
            path = RIEC.subjectLocalPath(id);
        });
        ^Database.downloadSubject(url, path);
    }
}
