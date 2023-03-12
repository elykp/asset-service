package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.CloudUploadRQ;
import java.io.IOException;

public interface AssetService {
  void uploadToCloud(CloudUploadRQ cloudUploadRQ) throws IOException;

  void deleteByPhotoId(String photoId);
}
