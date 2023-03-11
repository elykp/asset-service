package com.elykp.assetservice.assets.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class CloudUploadRQ {
  private MultipartFile file;

  private String ownerId;

  private String photoId;
}
