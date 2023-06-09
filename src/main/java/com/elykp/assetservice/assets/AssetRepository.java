package com.elykp.assetservice.assets;

import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends CrudRepository<Asset, Long> {

  Iterable<Asset> findAllByPhotoId(String photoId);
  void deleteAllByPhotoId(String photoId);
}
