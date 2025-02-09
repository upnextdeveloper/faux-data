package com.upnextdev.datagen.entity;

public class InputFields {

	private String columnName;
	private String dataType;
	private String isRequired;
	
	public  InputFields() {}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	@Override
	public String toString() {
		return "DataEntry [columnName=" + columnName + ", dataType=" + dataType + ", isRequired=" + isRequired + "]";
	}
	
	
}
