SofaConvention {
    var <kind;
    var <dataFile;

    var <globalConventions;
    var <globalSofaConventions;
    var <globalSofaConventionsVersion;
    var <globalAPIName;
    var <globalAPIVersion;
    var <globalDataType;
    var <globalRoomType;
    var <globalDateCreated;
    var <globalDateModified;
    var <globalAuthorContact;
    var <globalOrganization;
    var <globalLicense;
    var <globalTitle;

    // call by subclass, only
    *new{ | kind, filePath, attributes |
        ^super.newCopyArgs(kind, filePath)
              .initMetaDataFromAttributes(attributes)
    }

    initMetaDataFromAttributes{ | attributes |

        globalConventions = attributes[\GLOBAL_Conventions];
        globalSofaConventions = attributes[\GLOBAL_SOFAConventions];
        globalSofaConventionsVersion = attributes[\GLOBAL_SOFAConventionsVersion];
        globalAPIName = attributes[\GLOBAL_APIName];
        globalAPIVersion = attributes[\GLOBAL_APIVersion];
        globalDataType = attributes[\GLOBAL_DataType];
        globalRoomType = attributes[\GLOBAL_RoomType];
        globalDateCreated = attributes[\GLOBAL_DateCreated];
        globalDateModified = attributes[\GLOBAL_DateModified];
        globalAuthorContact = attributes[\GLOBAL_AuthorContact];
        globalOrganization = attributes[\GLOBAL_Organization];
        globalLicense = attributes[\GLOBAL_License];
        globalTitle = attributes[\GLOBAL_Title];
    }
}
