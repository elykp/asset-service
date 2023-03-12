package com.elykp.assetservice.assets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elykp.assetservice.storage.StorageService;
import com.sun.istack.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(AssetController.class)
public class AssetControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AssetService assetService;

  @MockBean
  private StorageService storageService;

  @Test
  public void deleteAssetByPhotoIdShouldReturn403WhenPathParamIsEmpty() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .delete("/api/assets").with(
            SecurityMockMvcRequestPostProcessors.jwt().jwt(createJwtToken()))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isMethodNotAllowed());
  }

  @Test
  public void deleteAssetByPhotoIdShouldReturn204() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .delete("/api/assets/1").with(
                SecurityMockMvcRequestPostProcessors.jwt().jwt(createJwtToken()))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$").doesNotExist())
    ;
  }


  @NotNull
  private Jwt createJwtToken() {
    String userId = "944b3067-1d77-43ff-93c3-cbffdb988728";
    String userName = "Khoa";
    String applicationId = "elykp-mm-client";

    return Jwt.withTokenValue("fake-token")
        .header("typ", "Bearer")
        .claim("iss",
            "http://localhost:8080/realms/elykp")
        .claim("oid", userId)
        .claim("scope", "asset")
        .claim("name", userName)
        .claim("azp", applicationId)
        .subject(userId)
        .build();
  }

}
