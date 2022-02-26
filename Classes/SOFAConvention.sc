SofaConvention {
    var <dataFile;
    var <metaData;

    // call by subclass, only
    // initialize a convention type from loaded attributes and 
    *new{ | filePath |
        ^super.newCopyArgs(filePath);
    }

    initMetaDataFromAttributes{ | attributes |

        var subclassKey, subclass, globalAttributes;
        var dbName, shortName;

        // get the subclass name as a symbol
        subclassKey = SofaInterface
            .globalAttributeAsSymbol("GLOBAL:SOFAConventions");
        subclass = attributes[subclassKey].asSymbol;
        ("== "++subclass ++ \_MetaData).postln;

        SofaInterface.attributeNames
                     .keysValuesDo{ | key, value | "... ".post; key.postln; };
        SofaInterface.attributeNames[(subclass ++ \_MetaData).asSymbol]
                     .do{ | name | "... ".post; name.postln };

        // get all global metadata attributes
        globalAttributes = SofaInterface.attributeNames[\Common_MetaData] ++
                           SofaInterface.attributeNames[subclass ++ \_MetaData];

        dbName = SofaInterface.globalAttributeAsSymbol("GLOBAL:DatabaseName");
        shortName = SofaInterface.globalAttributeAsSymbol("GLOBAL:ListenerShortName");

        attributes.keysValuesDo{ | key, value | "...... ".post; key.postln; };
        "... ".post;
        attributes[dbName.asSymbol].postln;
        "... ".post;
        attributes[shortName.asSymbol].postln;

        metaData = globalAttributes

            // transform each global attribute name
            // into a mapping of field-name to attribute value
            .collect{ | attr |
                var fieldName, attrSymbol;
                fieldName = SofaInterface.globalAttributeAsField(attr);
                attrSymbol = SofaInterface.globalAttributeAsSymbol(attr);

                fieldName -> attributes[attrSymbol]
            }
            .asDict.know_(true); // indexing can be done through messaging
    }
}
