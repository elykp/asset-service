package com.elykp.assetservice.cloudinary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class CloudinaryRS {

  private String format;

  @JsonProperty("resource_type")
  private String resourceType;

  @JsonProperty("secure_url")
  private String secureUrl;

  @JsonProperty("asset_id")
  private String assetId;

  @JsonProperty("public_id")
  private String publicId;

  private int height;

  private int width;

  private List<ImageTransformation> eager;
}
