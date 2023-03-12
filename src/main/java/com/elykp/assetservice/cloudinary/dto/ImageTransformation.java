package com.elykp.assetservice.cloudinary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImageTransformation {

  private String transformation;

  private int width;

  private int height;

  @JsonProperty("secure_url")
  private String secureUrl;
}
