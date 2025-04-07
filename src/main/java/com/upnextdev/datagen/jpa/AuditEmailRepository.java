package com.upnextdev.datagen.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upnextdev.datagen.entity.AuditEmail;

@Repository
public interface AuditEmailRepository extends JpaRepository<AuditEmail, Integer>{
}
