package com.upnextdev.datagen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upnextdev.datagen.entity.AuditEmail;
import com.upnextdev.datagen.service.AuditEmailService;
import com.upnextdev.datagen.service.FauxMessageProducerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/aud/v1")
public class AuditEmailController {

	@Autowired AuditEmailService auditEmailService;
	@Autowired FauxMessageProducerService fauxMessageProducerService;

	@GetMapping("/audits")
	public ResponseEntity<List<AuditEmail>> getAllAudits() {
		List<AuditEmail> allAudits = auditEmailService.getAllAudits();
		return new ResponseEntity<>(allAudits, HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/audits")
	public ResponseEntity<AuditEmail> addAudit(@RequestBody AuditEmail auditEmail){
		boolean successFulSave = false;
		try {
			AuditEmail ae = auditEmailService.addAuditEmail(auditEmail);
			successFulSave = true;
			return new ResponseEntity<AuditEmail>(ae, HttpStatus.CREATED);
		}catch(Exception e) {
			successFulSave = false;
			return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
		}finally {
			if(successFulSave) {
				String logKeys = auditEmail.getReferenceNo() + ","
						+ auditEmail.getEmail() + "," 
						+ auditEmail.getFileName();
//				fauxMessageProducerService.sendMessage(logKeys);
			}
		}
	}
}
