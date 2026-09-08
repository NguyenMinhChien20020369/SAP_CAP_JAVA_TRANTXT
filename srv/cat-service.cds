using { sap.capire.trantxt as db } from '../db/schema';

service CatalogService {
  @odata.draft.enabled
  entity MediaFiles @(Capabilities: {
        InsertRestrictions: {
            $Type: 'Capabilities.InsertRestrictionsType',
            Insertable,
        },
        UpdateRestrictions: {
            $Type: 'Capabilities.UpdateRestrictionsType',
            Updatable,
        },
        DeleteRestrictions: {
            $Type: 'Capabilities.DeleteRestrictionsType',
            Deletable,
        },
    }) as projection on db.MediaFiles;
}