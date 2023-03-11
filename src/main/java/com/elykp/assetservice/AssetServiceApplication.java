package com.elykp.assetservice;

import com.elykp.assetservice.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssetServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AssetServiceApplication.class, args);
  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return args -> {
      storageService.deleteAll();
      storageService.init();
    };
  }
}
