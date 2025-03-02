package com.upnextdev.datagen.output;


import java.util.List;

public interface DataOutput {

	// class to have the data outputted to the user (in files)
	public void createOutputFile(List<String> dataRows, List<String> columnValues, String tableName);
	// this uses the datagenerator to create the files
}
