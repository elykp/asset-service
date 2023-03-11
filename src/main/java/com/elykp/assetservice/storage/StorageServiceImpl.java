package com.elykp.assetservice.storage;

import jakarta.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

  private final Path rootLocation;

  public StorageServiceImpl(StorageProperties properties) {
    this.rootLocation = Paths.get(properties.getLocation());
  }

  @Override
  public void init() {
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage", e);
    }
  }

  @Override
  public Path store(MultipartFile file) {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file.");
      }
      Path destinationFile = this.rootLocation.resolve(
              Paths.get(String.format("%o.%s",
                  Instant.now().getEpochSecond(),
                  getExtension(file.getOriginalFilename()).orElse(""))
              ))
          .normalize().toAbsolutePath();
      if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
        // This is a security check
        throw new StorageException(
            "Cannot store file outside current directory.");
      }
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile,
            StandardCopyOption.REPLACE_EXISTING);
        return destinationFile;
      }
    } catch (IOException e) {
      throw new StorageException("Failed to store file.", e);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

  private Optional<String> getExtension(@Nullable String filename) {
    return Optional.ofNullable(filename)
        .filter(f -> f.contains("."))
        .map(f -> f.substring(filename.lastIndexOf(".") + 1));
  }
}
