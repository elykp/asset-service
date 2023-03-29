package com.elykp.assetservice.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.utils.ObjectUtils;
import com.elykp.assetservice.cloudinary.dto.CloudinaryRS;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

  private final Cloudinary cloudinary;
  @Value("${cloudinary.upload-preset}")
  private String uploadPreset;

  public CloudinaryServiceImpl(@Value("${cloudinary.key}") String apiKey,
      @Value("${cloudinary.secret}") String apiSecret,
      @Value("${cloudinary.cloudname}") String cloudName) {
    this.cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", cloudName,
        "api_key", apiKey,
        "api_secret", apiSecret,
        "secure", true));
  }

  @Override
  public CloudinaryRS upload(String pathname) throws IOException {
    Map params = ObjectUtils.asMap(
        "upload_preset", uploadPreset,
        "overwrite", true,
        "resource_type", "image",
        "eager", Arrays.asList(
            new EagerTransformation().width(400),
            new EagerTransformation().width(700))
    );

    final ObjectMapper objectMapper = new ObjectMapper().configure(
        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return objectMapper.convertValue(cloudinary.uploader()
        .upload(new File(pathname), params), CloudinaryRS.class);
  }
}
