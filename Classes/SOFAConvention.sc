// A super class for initializing common data in all sofa conventions
SofaConvention {
    var <dataFile;  // where the sofa is object saved on disk
    var <metaData;  // the common and specific global meta data

    // call by subclass, only
    // initialize a convention type from loaded attributes and 
    *new{ | filePath |
        ^super.newCopyArgs(filePath);
    }

    initMetaDataFromAttributes{ | attributes |
        var conventionAttributeName;

        // get the key to acces the specific convention name from attributes
        conventionAttributeName = SofaInterface
            .globalAttributeAsSymbol("GLOBAL:SOFAConventions");

        // transform each meta data attribute name
        // into a mapping of field-name to attribute value
        metaData = SofaInterface
            .metaDataAttributeNames(attributes[conventionAttributeName])
            .collect{ | attr |

                var fieldName, attrSymbol;

                fieldName = SofaInterface.globalAttributeAsField(attr);
                attrSymbol = SofaInterface.globalAttributeAsSymbol(attr);

                fieldName -> attributes[attrSymbol]
            }
            .asDict.know_(true); // indexing can be done through messaging
    }
}
