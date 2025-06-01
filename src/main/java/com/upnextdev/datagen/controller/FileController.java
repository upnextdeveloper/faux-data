package com.upnextdev.datagen.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.upnextdev.datagen.aws.config.S3Service;
import com.upnextdev.datagen.util.FileLocations;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class FileController {
private FileLocations fileLocations = new FileLocations();

	@Autowired
	private S3Service s3Service;
	
	@PostMapping("/file")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String key = file.getOriginalFilename();
        s3Service.uploadFile(key, file);
        return ResponseEntity.ok("Uploaded: " + key);
    }

	@GetMapping("/url")
    public ResponseEntity<String> generateDownloadUrl(@RequestParam("key") String key) {
        try {
        	URL url = s3Service.generatePresignedUrl(key, HttpMethod.GET);
            return ResponseEntity.ok(url.toString());
        }catch(Exception e) {
        	return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

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
