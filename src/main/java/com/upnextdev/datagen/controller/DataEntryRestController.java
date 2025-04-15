package com.upnextdev.datagen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upnextdev.datagen.service.AuditEmailService;
import com.upnextdev.datagen.service.DataEntryService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class DataEntryRestController {

	@Autowired DataEntryService dataEntryService;
	
	@PostMapping("/entry")
	public ResponseEntity<String> generateData(@RequestBody String body) {
		try {
			dataEntryService.parseJsonRequest(body);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>("Error Occurred generating file.",HttpStatus.BAD_GATEWAY);
		}
	}
}
