using CatalogService as service from '../../srv/cat-service';
annotate service.MediaFiles with @(
    UI.FieldGroup #GeneratedGroup : {
        $Type : 'UI.FieldGroupType',
        Data : [
            {
                $Type : 'UI.DataField',
                Label : 'content',
                Value : content,
            },
            {
                $Type : 'UI.DataField',
                Label : 'mediaType',
                Value : mediaType,
            },
            {
                $Type : 'UI.DataField',
                Label : 'fileName',
                Value : fileName,
            },
            {
                $Type : 'UI.DataField',
                Label : 'size',
                Value : size,
            },
        ],
    },
    UI.Facets : [
        {
            $Type : 'UI.ReferenceFacet',
            ID : 'GeneratedFacet1',
            Label : 'General Information',
            Target : '@UI.FieldGroup#GeneratedGroup',
        },
        {
            $Type : 'UI.ReferenceFacet',
            Label : 'Result',
            ID : 'Result',
            Target : '@UI.FieldGroup#Result',
        },
    ],
    UI.LineItem : [
        {
            $Type : 'UI.DataField',
            Label : 'content',
            Value : content,
        },
        {
            $Type : 'UI.DataField',
            Label : 'mediaType',
            Value : mediaType,
        },
        {
            $Type : 'UI.DataField',
            Label : 'fileName',
            Value : fileName,
        },
        {
            $Type : 'UI.DataField',
            Label : 'size',
            Value : size,
        },
    ],
    UI.FieldGroup #Result : {
        $Type : 'UI.FieldGroupType',
        Data : [
            {
                $Type : 'UI.DataField',
                Label : 'content result',
                Value : contentResult,
            },
            {
                $Type : 'UI.DataField',
                Label : 'mediaTypeResult',
                Value : mediaTypeResult,
            },
            {
                $Type : 'UI.DataField',
                Label : 'fileNameResult',
                Value : fileNameResult,
            },
            {
                $Type : 'UI.DataField',
                Label : 'sizeResult',
                Value : sizeResult,
            },
        ],
    },
);

