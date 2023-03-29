package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.AssetRS;
import com.elykp.assetservice.assets.dto.CloudUploadRQ;
import java.util.Map;

public interface AssetService {

  Map<String, AssetRS> getByPhotoId(String photoId);

  void uploadToCloud(CloudUploadRQ cloudUploadRQ);

  void deleteByPhotoId(String photoId);
}
