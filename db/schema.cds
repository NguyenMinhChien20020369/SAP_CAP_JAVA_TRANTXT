namespace sap.capire.trantxt;

using { managed } from '@sap/cds/common';

@Capabilities.Updatable: true
@Capabilities.Insertable: true
@Capabilities.Deletable: true
entity MediaFiles : managed {
  key ID       : UUID;
  
  @Core.MediaType: mediaType
  @Core.ContentDisposition.Filename: fileName
  content      : LargeBinary;  // Trường chứa dữ liệu file nhị phân

  @Core.IsMediaType: true
  mediaType    : String;       // VD: 'application/pdf', 'image/png'
  
  fileName     : String;       // Tên file khi download về (VD: 'document.pdf')
  size         : Integer;      // Dung lượng file (bytes)

  @Core.MediaType: mediaTypeResult
  @Core.ContentDisposition.Filename: fileNameResult
  contentResult      : LargeBinary;  // Trường chứa dữ liệu file nhị phân

  @Core.IsMediaType: true
  mediaTypeResult    : String;       // VD: 'application/pdf', 'image/png'
  
  fileNameResult     : String;       // Tên file khi download về (VD: 'document.pdf')
  sizeResult         : Integer;      // Dung lượng file (bytes)
}