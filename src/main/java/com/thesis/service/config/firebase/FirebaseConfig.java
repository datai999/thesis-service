package com.thesis.service.config.firebase;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;

// @Configuration
public class FirebaseConfig {

  @Value("${firebase.config.adminSdkPath}")
  private String adminSdkPath;

  @PostConstruct
  public void init() throws IOException {

    InputStream serviceAccount =
        FirebaseConfig.class.getClassLoader().getResourceAsStream(adminSdkPath);

    FirebaseOptions options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();

    FirebaseApp.initializeApp(options);
  }

}
