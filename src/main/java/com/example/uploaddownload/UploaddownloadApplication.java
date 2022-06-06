package com.example.uploaddownload;

import com.example.uploaddownload.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class UploaddownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploaddownloadApplication.class, args);
	}

}
