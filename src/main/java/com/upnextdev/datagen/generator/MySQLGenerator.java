package com.upnextdev.datagen.generator;

import java.util.List;

import com.upnextdev.datagen.entity.DataEntry;
import com.upnextdev.datagen.output.MySQLDataOutput;

public class MySQLGenerator extends DataGenerator {

	public void printData(List <String> columnValues, List<DataEntry> e, int count) {
		generateData(e, count);
		
		MySQLDataOutput mdo = new MySQLDataOutput();
		mdo.createOutputFile(generateData(e, count), columnValues);
	}
}
