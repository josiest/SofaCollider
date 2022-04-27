Crossmod : Database {

    classvar <prRootUrl;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootUrl = "https://sofacoustics.org/data/database/crossmod (dtf)";
        prSubjectFmt = "IRC_%_C_44100.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "Crossmod";
    }

    // The url of the database online
    *rootUrl { ^prRootUrl; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        ^Crossmod.prSubjectFmt.format(id)
    }

    // Get the url of a subject
    *subjectUrl{ | id |
        ^(Crossmod.rootUrl +/+ Crossmod.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(Crossmod.localRoot +/+ Crossmod.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = Crossmod.subjectUrl(id);

        if (path.isNil, {
            path = Crossmod.localRoot;
        });
        ^Database.downloadSubject(url, path);
    }
}
