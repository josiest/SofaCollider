Bili : Database {

    classvar <prRootURL;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootURL = "https://sofacoustics.org/data/database/bili (dtf)";
        prSubjectFmt = "IRC_%_C_HRIR_96000.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "Bili";
    }

    // The url of the database online
    *rootURL { ^prRootURL; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        ^Bili.prSubjectFmt.format(id)
    }

    // Get the url of a subject
    *subjectURL{ | id |
        ^(Bili.rootURL +/+ Bili.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(Bili.localRoot +/+ Bili.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = Bili.subjectURL(id);

        if (path.isNil, {
            path = Bili.localRoot;
        });
        ^Database.downloadSubject(url, path);
    }
}
