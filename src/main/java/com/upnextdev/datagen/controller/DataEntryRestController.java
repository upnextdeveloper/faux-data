package com.upnextdev.datagen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upnextdev.datagen.service.DataEntryService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class DataEntryRestController {

	@Autowired DataEntryService dataEntryService;
	
	@PostMapping("/entry")
	public void readDataEntry(@RequestBody String body){
		dataEntryService.parseJsonRequest(body);
	}
}
