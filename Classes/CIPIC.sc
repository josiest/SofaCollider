CIPIC {

    classvar <prRootUrl;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootUrl = "https://sofacoustics.org/data/database/cipic";
        prSubjectFmt = "subject_%.sofa";
        prLocalRoot = SofaInterface.databaseDir +/+ "CIPIC";
    }

    // The url of the database online
    *rootUrl { ^prRootUrl; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id |
        var padded;
        padded = id.asStringToBase(10, 3);
        ^CIPIC.prSubjectFmt.format(padded)
    }

    // Get the url of a subject
    *subjectUrl{ | id |
        ^(CIPIC.rootUrl +/+ CIPIC.subjectFilename(id));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id |
        ^(CIPIC.localRoot +/+ CIPIC.subjectFilename(id));
    }

    // Download a subject file
    // defaults path to root database directory
    *downloadSubject{ | id, path=nil |
        var url;

        url = CIPIC.subjectUrl(id);
        if (path.isNil, {
            path = CIPIC.localRoot;
        });
        postf("... downloading file %\n", url);
        postf("... downloading into %\n", path);
        "wget % --directory-prefix %".format(url, path).unixCmd;
    }
}
