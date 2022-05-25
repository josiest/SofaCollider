Aachen : Database {

    classvar <prRootURL;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootURL = "https://sofacoustics.org/data/database/aachen";
        prSubjectFmt = "MRT%.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "Aachen";
    }

    // The url of the database online
    *rootURL { ^prRootURL; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        var padded;
        padded = id.asStringToBase(10, 2);
        ^Aachen.prSubjectFmt.format(padded)
    }

    // Get the url of a subject
    *subjectURL{ | id |
        ^(Aachen.rootURL +/+ Aachen.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(Aachen.localRoot +/+ Aachen.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = Aachen.subjectURL(id);

        if (path.isNil, {
            path = Aachen.localRoot;
        });
        ^Database.downloadSubject(url, path);
    }
}
