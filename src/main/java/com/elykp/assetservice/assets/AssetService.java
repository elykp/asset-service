package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.CloudUploadRQ;
import java.io.IOException;

public interface AssetService {
  Asset uploadToCloud(CloudUploadRQ cloudUploadRQ) throws IOException;
}
