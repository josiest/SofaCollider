CHEDAR : Database {

    classvar <prRootURL;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootURL = "https://sofacoustics.org/data/database/chedar";
        prSubjectFmt = "chedar_%_UV%m.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir ++ "/" ++ "CHEDAR";

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
    *subjectFilename{ | id, num |
        var padded;
        padded = id.asStringToBase(10, 4);
        ^CHEDAR.prSubjectFmt.format(padded, num)
    }

    // Get the url of a subject
    *subjectURL{ | id, num |
        ^(CHEDAR.rootURL ++ "/" ++ CHEDAR.subjectFilename(id, num));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id, num |
        ^(CHEDAR.localRoot ++ "/" ++ CHEDAR.subjectFilename(id, num));
    }

    *downloadSubject{ | id, path=nil |
        var nums;
        nums = ["02", "05", "1", "2"];
        nums.do({ | num |
            var url, path;
            url = CHEDAR.subjectURL(id, num);
            path = CHEDAR.subjectLocalPath(id, num);
            Database.downloadSubject(url, path);
        });
    }
}
