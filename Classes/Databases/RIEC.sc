RIEC : Database {

    classvar <prRootUrl;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootUrl = "https://sofacoustics.org/data/database/riec";
        prSubjectFmt = "RIEC_hrir_subject_%.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "RIEC";
    }

    // The url of the database online
    *rootUrl { ^prRootUrl; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        var padded;
        padded = id.asStringToBase(10, 3);
        ^RIEC.prSubjectFmt.format(padded)
    }

    // Get the url of a subject
    *subjectUrl{ | id |
        ^(RIEC.rootUrl +/+ RIEC.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(RIEC.localRoot +/+ RIEC.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = RIEC.subjectUrl(id);

        if (path.isNil, {
            path = RIEC.localRoot;
        });
        ^Database.downloadSubject(url, path);
    }
}
