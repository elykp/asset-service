package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.CloudUploadRQ;
import com.elykp.assetservice.cloudinary.CloudinaryService;
import com.elykp.assetservice.cloudinary.dto.CloudinaryRS;
import com.elykp.assetservice.storage.StorageService;
import java.io.IOException;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

  private final CloudinaryService cloudinaryService;

  private final StorageService storageService;

  private final AssetRepository assetRepository;

  @Override
  public Asset uploadToCloud(CloudUploadRQ cloudUploadRQ) throws IOException {
    Path path = storageService.store(cloudUploadRQ.getFile());
    CloudinaryRS cloudinaryResponse = cloudinaryService.upload(path.toAbsolutePath().toString());
    Asset asset = new Asset();
    asset.setPhotoId(cloudUploadRQ.getPhotoId());
    asset.setHeight(cloudinaryResponse.getHeight());
    asset.setWidth(cloudinaryResponse.getWidth());
    asset.setUrl(cloudinaryResponse.getSecureUrl());
    asset.setOwnerId(cloudUploadRQ.getOwnerId());
    asset.setResponsiveKey("Test");

    return assetRepository.save(asset);
  }
}
