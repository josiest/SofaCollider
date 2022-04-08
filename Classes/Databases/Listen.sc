Listen : Database {

    classvar <prRootUrl;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootUrl = "https://sofacoustics.org/data/database/listen (hrtf)";
        prSubjectFmt = "IRC_%_R_44100.sofa";
        prLocalRoot = SofaInterface.databaseDir +/+ "LISTEN";
    }

    // The url of the database online
    *rootUrl { ^prRootUrl; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        ^Listen.prSubjectFmt.format(id)
    }

    // Get the url of a subject
    *subjectUrl{ | id |
        ^(Listen.rootUrl +/+ Listen.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(Listen.localRoot +/+ Listen.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = Listen.subjectUrl(id);

        if (path.isNil, {
            path = Listen.localRoot;
        });
        ^Database.downloadSubject(url, path);
    }
}
