package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.AssetRS;
import com.elykp.assetservice.assets.dto.CloudUploadRQ;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/assets")
public class AssetController {

  private final AssetService assetService;

  @GetMapping("/{photo_id}")
  public ResponseEntity<Map<String, AssetRS>> getByPhotoId(
      @PathVariable("photo_id") String photoId) {
    return ResponseEntity.ok(assetService.getByPhotoId(photoId));
  }

  @PostMapping
  public ResponseEntity upload(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "photo_id", required = false) String photoId,
      JwtAuthenticationToken authenticationToken) {

    CloudUploadRQ cloudUploadRQ = new CloudUploadRQ();
    cloudUploadRQ.setFile(file);
    cloudUploadRQ.setOwnerId(authenticationToken.getTokenAttributes().get("sub").toString());
    cloudUploadRQ.setPhotoId(photoId);
    assetService.uploadToCloud(cloudUploadRQ);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{photo_id}")
  public ResponseEntity deleteByPhotoId(@PathVariable("photo_id") String photoId) {
    assetService.deleteByPhotoId(photoId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
