Crossmod : Database {

    classvar <prRootURL;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootURL = "https://sofacoustics.org/data/database/crossmod (dtf)";
        prSubjectFmt = "IRC_%_C_44100.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "Crossmod";
    }

    // The url of the database online
    *rootURL { ^prRootURL; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        ^Crossmod.prSubjectFmt.format(id)
    }

    // Get the url of a subject
    *subjectURL{ | id |
        ^(Crossmod.rootURL +/+ Crossmod.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(Crossmod.localRoot +/+ Crossmod.subjectFilename(id));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        url = Crossmod.subjectURL(id);

        if (path.isNil, {
            path = Crossmod.localRoot;
        });
        ^Database.downloadSubject(url, path);
    }
}
