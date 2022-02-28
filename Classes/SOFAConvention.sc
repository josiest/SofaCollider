SofaConvention {
    var <dataFile;
    var <metaData;

    // call by subclass, only
    // initialize a convention type from loaded attributes and 
    *new{ | filePath |
        ^super.newCopyArgs(filePath);
    }

    initMetaDataFromAttributes{ | attributes |
        var subclass, subclassMetaData, metaDataNames;

        // get the subclass name as a symbol
        subclass = SofaInterface.globalAttributeAsSymbol("GLOBAL:SOFAConventions");
        subclassMetaData = (attributes[subclass] ++ \_MetaData).asSymbol;

        // get all global metadata attribute names
        metaDataNames = SofaInterface.attributeNames[\Common_MetaData] ++
                        SofaInterface.attributeNames[subclassMetaData];

        // transform each global attribute name
        // into a mapping of field-name to attribute value
        metaData = metaDataNames.collect{ | attr |
            var fieldName, attrSymbol;

            fieldName = SofaInterface.globalAttributeAsField(attr);
            attrSymbol = SofaInterface.globalAttributeAsSymbol(attr);

            fieldName -> attributes[attrSymbol]
        }
        .asDict.know_(true); // indexing can be done through messaging
    }
}
