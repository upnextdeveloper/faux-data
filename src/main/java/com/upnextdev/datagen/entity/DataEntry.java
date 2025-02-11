package com.upnextdev.datagen.entity;

import java.util.List;

public class DataEntry {

	
	private String columnName;
	private String dataType;
	private String isRequired;
	private Integer rowCount;
	
	public DataEntry() {}
	
	public DataEntry(String columnName, String dataType, String isRequired) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.isRequired = isRequired;
	}

	public DataEntry(String columnName, String dataType, String isRequired, Integer rowCount) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.isRequired = isRequired;
		this.rowCount = rowCount;
	}

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

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	@Override
	public String toString() {
		return "DataEntry [columnName=" + columnName + ", dataType=" + dataType + ", isRequired=" + isRequired
				+ ", rowCount=" + rowCount + "]";
	}
}
