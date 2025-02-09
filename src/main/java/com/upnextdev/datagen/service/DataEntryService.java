package com.upnextdev.datagen.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.upnextdev.datagen.entity.DataEntry;
import com.upnextdev.datagen.generator.DataGenerator;
import com.upnextdev.datagen.generator.MySQLGenerator;

@Service
public class DataEntryService {

	public void parseJsonRequest(String body) {
		int numberofCharToremove = 16;
		int numberofCharToremovefromEnd = 2;
		String newBody = body.substring(numberofCharToremove);
		newBody = newBody.substring(0, newBody.length() - numberofCharToremovefromEnd);
		newBody = StringUtils.replaceChars(newBody, "(){}[]<>", null);
		newBody = newBody.replace("\"", "");
		
		List<String> values = Arrays.asList(newBody.split(","));
		
		List<String> columnValues = new ArrayList<String>();
		List<String> dataTypeValues = new ArrayList<String>();
		List<String> iusRequiredValues = new ArrayList<String>();

		values.forEach(v -> {
			v = v.trim();
			if(v.startsWith("columnName")){
				v = v.substring(11);
				columnValues.add(v);
			}else if(v.startsWith("datatype")) {
				v = v.substring(9);
				dataTypeValues.add(v);
			}else if(v.startsWith("isRequired")) {
				v = v.substring(11);
				iusRequiredValues.add(v);
			}
		});
		
		List<DataEntry> entryList = buildDataArrays(columnValues, dataTypeValues, iusRequiredValues);

		System.out.println("-------------");
		
		MySQLGenerator gen = new MySQLGenerator();
		gen.printData(entryList, 1000);
	}
	
	private List<DataEntry> buildDataArrays(List<String> columns, List<String> dataTypes, List<String> isRequired) {
		int numberofIterations = columns.size();
		List<DataEntry> dataEntries = new ArrayList<DataEntry>();
		for(int i = 0; i < numberofIterations; i++) {
			DataEntry newEntry = new DataEntry(columns.get(i), dataTypes.get(i), isRequired.get(i));
			dataEntries.add(newEntry);
		}
		
		return dataEntries;
	}
}
