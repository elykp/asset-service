package com.elykp.assetservice.assets;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "asset_entity")
public class Asset {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 36)
  private String ownerId;

  @Column(columnDefinition = "varchar(255) default ''")
  private String url;

  @Column(nullable = false)
  private int width;

  @Column(nullable = false)
  private int height;

  @Column(name = "responsive_key", nullable = false, length = 20)
  private String responsiveKey;

  @Column(name = "created_at")
  private Long createdAt;

  @Column(name = "photo_id", length = 8)
  private String photoId;

  @PrePersist
  protected void prePersist() {
    if (this.createdAt == null) {
      createdAt = Instant.now().getEpochSecond();
    }
  }
}
