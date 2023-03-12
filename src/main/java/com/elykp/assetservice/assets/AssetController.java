package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.CloudUploadRQ;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/assets")
public class AssetController {

  private final AssetService assetService;

  @PostMapping
  public ResponseEntity upload(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "photo_id", required = false) String photoId,
      JwtAuthenticationToken authenticationToken) {

    CloudUploadRQ cloudUploadRQ = new CloudUploadRQ();
    cloudUploadRQ.setFile(file);
    cloudUploadRQ.setOwnerId(authenticationToken.getTokenAttributes().get("sub").toString());
    cloudUploadRQ.setPhotoId(photoId);
    try {
      assetService.uploadToCloud(cloudUploadRQ);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/{photo_id}")
  public ResponseEntity deleteByPhotoId(@PathVariable("photo_id") String photoId) {
    assetService.deleteByPhotoId(photoId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
