Database {
    // The url of the database online
    // Must be implemented by subclass
    *rootUrl { NotYetImplementedError().throw; }

    // The local path of the database root
    // Must be implemented by subclass
    *localRoot { NotYetImplementedError().throw; }

    // Get the filename for a subject
    // Must be implemented by subclass
    *subjectFilename { | id | NotYetImplementedError().throw; }

    // Get the url of a subject
    // Must be implemented by subclass
    *subjectUrl { | id | NotYetImplementedError().throw; }

    // get the local path for a subject
    *subjectLocalPath{ | id | NotYetImplementedError().throw; }

    // Download a subject file
    *downloadSubject { | url, path |
        ^Download(url, path,
            { "success".postln; },
            { "failure".postln; },
            { | rec, tot | (100.0*rec/tot).post; "%".postln; }
        )

    }
}
