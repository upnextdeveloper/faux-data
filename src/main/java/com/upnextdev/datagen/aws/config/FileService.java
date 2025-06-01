package com.upnextdev.datagen.aws.config;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uploadFile(MultipartFile file);
}
