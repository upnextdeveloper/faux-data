package com.upnextdev.datagen.generator;

import java.util.List;

import com.upnextdev.datagen.entity.DataEntry;
import com.upnextdev.datagen.output.MySQLDataOutput;

public class MySQLGenerator extends DataGenerator {

	@Override
	public void printData(List<String> columnValues, List<DataEntry> e, int count, String tableName) throws Exception {
		// TODO Auto-generated method stub
		generateData(e, count);

		MySQLDataOutput mdo = new MySQLDataOutput();
		mdo.createOutputFile(generateData(e, count), columnValues, tableName);
	}
}
