package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.FileProcessMessage;
import com.elykp.assetservice.cloudinary.CloudinaryService;
import com.elykp.assetservice.cloudinary.dto.CloudinaryRS;
import com.elykp.assetservice.cloudinary.dto.ImageTransformation;
import com.elykp.assetservice.storage.StorageService;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
@RabbitListener(queues = "${queue.name}")
public class FileProcessConsumer {

  @Autowired
  private StorageService storageService;

  @Autowired
  private CloudinaryService cloudinaryService;

  @Autowired
  private AssetRepository assetRepository;

  @RabbitHandler
  public void receiveMsg(final FileProcessMessage fileProcessMessage) {
    log.info("Start processing task");
    process(fileProcessMessage);
    log.info("Finish processing task");
  }

  private void process(final FileProcessMessage fileProcessMessage) {
    try {
      CloudinaryRS cloudinaryRS = cloudinaryService.upload(fileProcessMessage.getPath());
      for (ImageTransformation trans : cloudinaryRS.getEager()) {
        Asset asset = Asset.builder()
            .responsiveKey("image" + trans.getWidth())
            .width(trans.getWidth())
            .height(trans.getHeight())
            .photoId(fileProcessMessage.getPhotoId())
            .ownerId(fileProcessMessage.getOwnerId())
            .url(trans.getSecureUrl())
            .build();
        assetRepository.save(asset);
      }

      storageService.deleteByPath(fileProcessMessage.getPath());
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
