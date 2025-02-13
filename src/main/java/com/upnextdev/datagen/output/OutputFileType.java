package com.upnextdev.datagen.output;

public enum OutputFileType {

	
	
	EXCEL_FILE("Excel File"),
	MYSQL_FILE("MySQL File");
	
	private final String fileType;
	OutputFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileType() {
		return fileType;
	}
}
