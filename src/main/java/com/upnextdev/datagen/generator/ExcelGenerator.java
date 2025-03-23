package com.upnextdev.datagen.generator;

import java.util.List;

import com.upnextdev.datagen.entity.DataEntry;
import com.upnextdev.datagen.output.ExcelDataOutput;

public class ExcelGenerator extends DataGenerator {

	@Override
	public void printData(List<String> columnValues, List<DataEntry> e, int count, String tableName) throws Exception {

		ExcelDataOutput edo = new ExcelDataOutput();
		edo.createOutputFile(generateData(e, count), columnValues, tableName);
	}

}
