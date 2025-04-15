package com.upnextdev.datagen.entity;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_table")
public class AuditEmail {

	@Id
	@Column(name = "audit_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer auditId;
	
	@Column(name="reference_no")
	private String referenceNo;
	@Column(name="email")
	private String email;
	@Column(name="datetime")
	private Date dateTime;
	@Column(name="filename")
	private String fileName;
	@Column(name="cost")
	private Double cost;
	
	public AuditEmail() {}

	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "AuditEmail [auditId=" + auditId + ", referenceNo=" + referenceNo + ", email=" + email + ", dateTime="
				+ dateTime + ", fileName=" + fileName + ", cost=" + cost + "]";
	}
}
