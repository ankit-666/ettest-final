//package com.ettest.controller;
//
//import com.ettest.entity.Document;
//import com.ettest.model.BooleanModel;
//import com.ettest.service.DocumentService;
//import com.ettest.utility.TokenUtility;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/doc")
//public class DocumentController {
//
//  @Autowired
//  @Qualifier("com.ettest.service.impl.DocumentServiceImpl")
//  private DocumentService documentService;
//
//  @Autowired
//  @Qualifier("com.ettest.utility.TokenUtility")
//  private TokenUtility tokenUtility;
//
//  @RequestMapping(value = "/upload", method = RequestMethod.POST)
//  public ResponseEntity<BooleanModel> uploadFile(
//      @RequestParam("file") MultipartFile file,
//      @RequestParam("toBeSignedBy") Long toBeSignedBy,
//      @RequestHeader("userId") Long userId,
//      @RequestHeader("token") String token) {
//    String returnToken = tokenUtility.validateToken(userId,token);
//    BooleanModel booleanModel = new BooleanModel(returnToken, Boolean.FALSE);
//    try {
//      documentService.save(file, userId, toBeSignedBy);
//      booleanModel.setBool(Boolean.TRUE);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return ResponseEntity.status(HttpStatus.ACCEPTED)
//        .body(booleanModel);
//  }
//
////  @RequestMapping(value = "/getAllDocumentList", method = RequestMethod.GET)
////  public ResponseEntity<List<Document>> getAllDocumentList(
////
////  )
//
////  @RequestMapping(value = "/download", method = RequestMethod.GET)
////  public ResponseEntity<List<FileData>> getListFiles() {
////    List<FileData> fileInfos = documentService.loadAll()
////        .stream()
////        .map(this::pathToFileData)
////        .collect(Collectors.toList());
////
////    return ResponseEntity.status(HttpStatus.OK)
////        .body(fileInfos);
////  }
////
////  @DeleteMapping
////  public void delete() {
////    documentService.deleteAll();
////  }
////
////  private FileData pathToFileData(Path path) {
////    FileData fileData = new FileData();
////    String filename = path.getFileName()
////        .toString();
////    fileData.setFilename(filename);
////    fileData.setUrl(MvcUriComponentsBuilder.fromMethodName(FilesController.class, "getFile", filename)
////        .build()
////        .toString());
////    try {
////      fileData.setSize(Files.size(path));
////    } catch (IOException e) {
////      e.printStackTrace();
////      throw new RuntimeException("Error: " + e.getMessage());
////    }
////
////    return fileData;
////  }
////
////  @GetMapping("{filename:.+}")
////  @ResponseBody
////  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
////    Resource file = documentService.load(filename);
////    return ResponseEntity.ok()
////        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
////        .body(file);
////  }
////}
//}
