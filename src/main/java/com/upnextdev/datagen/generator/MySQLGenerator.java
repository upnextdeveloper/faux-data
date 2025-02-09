package com.upnextdev.datagen.generator;

import java.util.List;

import com.upnextdev.datagen.entity.DataEntry;

public class MySQLGenerator extends DataGenerator {

	public void printData(List<DataEntry> e, int count) {
		generateData(e, count);
		
		for(String entry: generateData(e, count)) {
			System.out.println(entry);
		}
	}
}
