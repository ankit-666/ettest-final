//package com.ettest.service.impl;
//
//import com.ettest.constants.Constants;
//import com.ettest.entity.Document;
//import com.ettest.repository.DocumentRepository;
//import com.ettest.service.DocumentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//
//@Service("com.ettest.service.impl.DocumentServiceImpl")
//public class DocumentServiceImpl implements DocumentService {
//
//  @Autowired
//  @Qualifier("com.ettest.repository.DocumentRepository")
//  private DocumentRepository documentRepository;
//
//  @PostConstruct
//  public void init() {
//    try {
//      Files.createDirectories(Paths.get(Constants.unsignedUploadPath));
//    } catch (IOException e) {
//      throw new RuntimeException("Could not create upload folder!");
//    }
//  }
//
//  @Override
//  public void save(MultipartFile file, Long userId, Long toBeSignedBy) {
//    try {
//      Path root = Paths.get(Constants.unsignedUploadPath);
//      if (!Files.exists(root)) {
//        init();
//      }
//      Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
//      Document document = new Document(null, userId, LocalDateTime.now(), toBeSignedBy, null, null,
//          file.getOriginalFilename(), null, null);
//      documentRepository.save(document);
//    } catch (Exception e) {
//      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
//    }
//
//  }
//}
//
//
