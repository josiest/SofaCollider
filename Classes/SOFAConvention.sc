// A super class for initializing common data in all sofa conventions
SofaConvention {
    var <dataFile;  // where the sofa is object saved on disk
    var <prGlobalMetadata;  // the common and specific global meta data

    // call by subclass, only
    // initialize a convention type from loaded attributes and 
    *new{ | filePath |
        ^super.newCopyArgs(filePath);
    }

    initGlobalMetadata{ | attributes |
        var conventionAttributeName;

        // get the key to acces the specific convention name from attributes
        conventionAttributeName = SofaInterface
            .globalAttributeAsSymbol("GLOBAL:SOFAConventions");

        // transform each meta data attribute name
        // into a mapping of field-name to attribute value
        prGlobalMetadata = SofaInterface
            .metadataNames(attributes[conventionAttributeName])
            .collect{ | attr |

                var fieldName, attrSymbol;

                fieldName = SofaInterface.globalAttributeAsField(attr);
                attrSymbol = SofaInterface.globalAttributeAsSymbol(attr);

                fieldName -> attributes[attrSymbol]
            }
            .asDict.know_(true); // indexing can be done through messaging
    }

    convention{ ^prGlobalMetadata.conventions; }
    version{ ^prGlobalMetadata.version; }

    sofaConvention{ ^prGlobalMetadata.sofaConventions; }
    sofaConventionVersion{ ^prGlobalMetadata.sofaConventionsVersion; }

    apiName{ ^prGlobalMetadata.apiName; }
    apiVersion{ ^prGlobalMetadata.apiVersion; }

    dataType{ ^prGlobalMetadata.dataType; }
    roomType{ ^prGlobalMetadata.roomType; }

    dateCreated{ ^prGlobalMetadata.dateCreated; }
    dateModified{ ^prGlobalMetadata.dateModified; }

    authorContact{ ^prGlobalMetadata.authorContact; }
    organization{ ^prGlobalMetadata.organization; }
    license{ ^prGlobalMetadata.license; }
    title{ ^prGlobalMetadata.title; }
}
