package com.upnextdev.datagen.output;

import java.io.File;
import java.util.List;

public interface DataOutput {

	// class to have the data outputted to the user (in files)
	public File createOutputFile(List<String> dataRows);
	// this uses the datagenerator to create the files
}
