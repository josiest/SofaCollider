CIPIC : Database {

    classvar <prRootURL;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootURL = "https://sofacoustics.org/data/database/cipic";
        prSubjectFmt = "subject_%.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "CIPIC";

        // make the database directory if it doesn't exist
        prLocalRoot = prLocalRoot.standardizePath;
        if (File.exists(prLocalRoot).not, {
            File.mkdir(prLocalRoot);
        });
    }

    // The uRL of the database online
    *rootURL { ^prRootURL; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        var padded;
        padded = id.asStringToBase(10, 3);
        ^CIPIC.prSubjectFmt.format(padded)
    }

    // Get the uRL of a subject
    *subjectURL{ | id |
        ^(CIPIC.rootURL ++ "/" ++ CIPIC.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(CIPIC.localRoot +/+ CIPIC.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = CIPIC.subjectURL(id);

        if (path.isNil, {
            path = CIPIC.subjectLocalPath(id);
        });
        ^Database.downloadSubject(url, path);
    }
}
