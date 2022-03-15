CIPIC {

    classvar <prRootUrl;
    classvar <prSubjectFmt;

    *initClass {
        prRootUrl = "https://sofacoustics.org/data/database/cipic/";
        prSubjectFmt = "subject_%.sofa";
    }

    *subjectUrl{ | id |
        var padded;
        padded = id.asStringToBase(10, 3);
        ^(prRootUrl ++ prSubjectFmt.format(padded))
    }
}
