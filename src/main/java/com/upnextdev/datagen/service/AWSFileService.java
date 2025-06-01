package com.upnextdev.datagen.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.upnextdev.datagen.aws.config.FileService;

@Service
public class AWSFileService implements FileService {


	private AmazonS3Client amazonS3Client = new AmazonS3Client();
	
	public String uploadFile(MultipartFile file) {
		
		String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		String key = UUID.randomUUID().toString() + "." + extension;
		
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentLength(file.getSize());
		metaData.setContentType(file.getContentType());
		
		try {
			amazonS3Client.putObject("faux-data-files-test",
					key,
					file.getInputStream(),
					metaData);
		}catch(IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occurred while uploading the file");
		}
		
		amazonS3Client.setObjectAcl("faux-data-files-test",key,CannedAccessControlList.PublicRead);
		
		return amazonS3Client.getResourceUrl("faux-data-files-test", key);
	}
}
