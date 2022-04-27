CHEDAR : Database {

    classvar <prRootUrl;
    classvar <prSubjectFmt;
    classvar <prLocalRoot;

    *initClass {
        prRootUrl = "https://sofacoustics.org/data/database/chedar";
        prSubjectFmt = "chedar_%_UV%m.sofa";
        prLocalRoot = SofaColliderConfig.hrtfDataDir +/+ "CHEDAR";
    }

    // The url of the database online
    *rootUrl { ^prRootUrl; }

    // The local path of the database root
    *localRoot { ^prLocalRoot; }

    // Get the filename for a subject
    *subjectFilename{ | id, num |
        var padded;
        padded = id.asStringToBase(10, 4);
        ^CHEDAR.prSubjectFmt.format(padded, num)
    }

    // Get the url of a subject
    *subjectUrl{ | id, num |
        ^(CHEDAR.rootUrl +/+ CHEDAR.subjectFilename(id, num));
    }

    // Get the local path for a subject
    *subjectLocalPath{ | id, num |
        ^(CHEDAR.localRoot +/+ CHEDAR.subjectFilename(id, num));
    }

    *downloadSubject{ | id, path=nil |
        var url;
        nums = ["02", "05", "1", "2"];
        urls = nums.collect({ | num | CHEDAR.subjectUrl(id, num) });

        if (path.isNil, {
            path = CHEDAR.localRoot;
        });
        urls.do({ | url | Database.downloadSubject(url, path) })
    }
}