SofaConvention {
    var <dataFile;
    var <metaData;

    // call by subclass, only
    // initialize a convention type from loaded attributes and 
    *new{ | filePath, attributes |

        ^super.newCopyArgs(filePath)
              .prInitMetaDataFromAttributes(attributes)
    }

    prInitMetaDataFromAttributes{ | attributes |

        metaData = SofaInterface.attributeNames[\Common_MetaData]
            .collect{ | attr |
                var fieldName, attrSymbol;
                fieldName = SofaInterface.globalAttributeAsField(attr);
                attrSymbol = SofaInterface.globalAttributeAsSymbol(attr);

                fieldName -> attributes[attrSymbol]
            }
            .asDict.know_(true); // indexing can be done through messaging
    }
}
