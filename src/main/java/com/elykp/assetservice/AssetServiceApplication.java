package com.elykp.assetservice;

import com.elykp.assetservice.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AssetServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AssetServiceApplication.class, args);
  }

  @Bean
  @LoadBalanced
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return args -> {
      storageService.deleteAll();
      storageService.init();
    };
  }
}
