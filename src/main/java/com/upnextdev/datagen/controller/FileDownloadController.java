package com.upnextdev.datagen.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upnextdev.datagen.util.FileLocations;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class FileDownloadController {
private FileLocations fileLocations = new FileLocations();
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable
			String fileName) throws FileNotFoundException{
		try {
			String fileLocation = fileLocations.getLocalFileLocation() +
					fileName;
			System.out.println("File location: " + fileLocation);
			File file = new File(fileLocation);
			InputStreamResource resource = new InputStreamResource(
					new FileInputStream(file));
			
			HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
			
			return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(resource);
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
