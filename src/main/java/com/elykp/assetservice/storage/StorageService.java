package com.elykp.assetservice.storage;

import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

  void init();

  Path store(MultipartFile file);

  void deleteByPath(Path path);

  void deleteByPath(String path);


  void deleteAll();
}
