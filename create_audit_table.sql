CREATE DATABASE IF NOT EXISTS faux_auditdb;

USE faux_auditdb;

CREATE TABLE IF NOT EXISTS audit_table(
	audit_id INT NOT NULL AUTO_INCREMENT,
	reference_no VARCHAR(20) NOT NULL,
	email VARCHAR(150) NOT NULL,
	datetime DATETIME NOT NULL,
	filename VARCHAR(200) NOT NULL,
	cost DOUBLE NOT NULL,
	primary key(audit_id)
);