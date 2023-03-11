package com.elykp.assetservice.cloudinary;

import com.elykp.assetservice.cloudinary.dto.CloudinaryRS;
import java.io.IOException;

public interface CloudinaryService {

  CloudinaryRS upload(String pathname) throws IOException;
}
