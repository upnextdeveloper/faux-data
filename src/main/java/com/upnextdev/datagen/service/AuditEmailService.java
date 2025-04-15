package com.upnextdev.datagen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upnextdev.datagen.entity.AuditEmail;
import com.upnextdev.datagen.jpa.AuditEmailRepository;

@Service
public class AuditEmailService {

	@Autowired
	private AuditEmailRepository auditEmailRepository;
	
	public List<AuditEmail> getAllAudits(){
		return auditEmailRepository.findAll();
	}
	
	public AuditEmail addAuditEmail(AuditEmail auditEmail) {
		return auditEmailRepository.save(auditEmail);
	}
}
