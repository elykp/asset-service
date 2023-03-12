package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.CloudUploadRQ;
import com.elykp.assetservice.assets.dto.FileProcessMessage;
import com.elykp.assetservice.storage.StorageService;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

  private final AssetRepository assetRepository;

  private final StorageService storageService;

  private final FileProcessProducer fileProcessProducer;


  @Override
  public void uploadToCloud(CloudUploadRQ cloudUploadRQ) {
    final Path path = storageService.store(cloudUploadRQ.getFile());
    FileProcessMessage fileProcessMessage = new FileProcessMessage(
        path.toAbsolutePath().toString(),
        cloudUploadRQ.getPhotoId(),
        cloudUploadRQ.getOwnerId());
    fileProcessProducer.sendMsg(fileProcessMessage);
  }

  @Override
  public void deleteByPhotoId(String photoId) {
    assetRepository.deleteAllByPhotoId(photoId);
  }

}
