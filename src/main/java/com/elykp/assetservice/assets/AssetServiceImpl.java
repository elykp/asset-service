package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.AssetRS;
import com.elykp.assetservice.assets.dto.CloudUploadRQ;
import com.elykp.assetservice.assets.dto.FileProcessMessage;
import com.elykp.assetservice.assets.mapper.AssetMapper;
import com.elykp.assetservice.storage.StorageService;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

  private final AssetRepository assetRepository;

  private final StorageService storageService;

  private final FileProcessProducer fileProcessProducer;


  @Override
  public Map<String, AssetRS> getByPhotoId(String photoId) {
    var assets = assetRepository.findAllByPhotoId(photoId);
    Map<String, AssetRS> assetMap = new HashMap<>();
    for (Asset asset : assets) {
      assetMap.put(asset.getResponsiveKey(), AssetMapper.INSTANCE.mapAssetToAssetRS(asset));
    }
    return assetMap;
  }

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
