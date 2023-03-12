package com.elykp.assetservice.assets.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileProcessMessage implements Serializable {

  private String path;

  private String photoId;

  private String ownerId;
}
