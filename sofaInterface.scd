// define where to find the sofa source repository
~sofaRoot = PathName("/home/josiest/sofa/API_MO");
~sofaSrcDir = ~sofaRoot +/+ "API_MO";

~runSOFAroutine = { | source |
    var sourceFile, octaveCmd;
    var allSourceCode, pipe, output, lastLine, nextLine;

    // the name of the octave command and the temporary source file
    octaveCmd = "octave";
    sourceFile = "temp.m";

    // prepend the source code with sofa import
    allSourceCode = [
        "warning('off', 'all');",
        "addpath('%');".format(~sofaSrcDir.fullPath),
        "SOFAstart;"
    ] ++ source;

    // write the source code to the temporary file
    File.use(sourceFile, "w", { | fp |
        allSourceCode.do({ | line | fp.write(line ++ "\n"); });
    });

    // run the octave command, and pipe input into it
    pipe = Pipe.argv([octaveCmd, sourceFile], "r");

    // scan the output until the last line
    lastLine = pipe.getLine; nextLine = pipe.getLine;
    while ({ nextLine.notNil }, { lastLine = nextLine; nextLine = pipe.getLine; });

    pipe.close;
    File.delete(sourceFile);

    lastLine
};
