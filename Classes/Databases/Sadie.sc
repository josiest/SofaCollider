Sadie : Database {

    classvar <prRootUrl;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootUrl = "https://sofacoustics.org/data/database/sadie";
        prSubjectFmt = "H%_48K_24bit_256tap_FIR_SOFA.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "SADIE";
    }

    // The url of the database online
    *rootUrl { ^prRootUrl; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        ^Sadie.prSubjectFmt.format(id)
    }

    // Get the url of a subject
    *subjectUrl{ | id |
        ^(Sadie.rootUrl +/+ Sadie.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(Sadie.localRoot +/+ Sadie.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = Sadie.subjectUrl(id);

        if (path.isNil, {
            path = Sadie.localRoot;
        });
        ^Database.downloadSubject(url, path);
    }
}
