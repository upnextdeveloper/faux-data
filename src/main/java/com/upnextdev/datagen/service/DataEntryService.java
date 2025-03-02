package com.upnextdev.datagen.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.upnextdev.datagen.entity.DataEntry;
import com.upnextdev.datagen.generator.DataGenerator;
import com.upnextdev.datagen.generator.ExcelGenerator;
import com.upnextdev.datagen.generator.MySQLGenerator;
import com.upnextdev.datagen.output.OutputFileType;

@Service
public class DataEntryService {

	public void parseJsonRequest(String body) throws Exception {
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
		List<String> rowCountValues = new ArrayList<>();
		List<String> fileTypeValues = new ArrayList<>();
		
		List<String> tableNameValues = new ArrayList<String>();

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
			}else if(v.startsWith("rowCount")) {
				v = v.substring(9);
				rowCountValues.add(v);
			}else if(v.startsWith("fileType")) {
				v = v.substring(9);
				fileTypeValues.add(v);
			}else if(v.startsWith("tableName")) {
				v = v.substring(10);
				tableNameValues.add(v);
			}
		});
		
		Integer rowCount = Integer.parseInt(rowCountValues.get(0));
		String tableName = tableNameValues.get(0);
		List<DataEntry> entryList = buildDataArrays(columnValues, dataTypeValues, iusRequiredValues,
				tableName);

		System.out.println("-------------");
		
		String fileType = fileTypeValues.get(0);
		
		DataGenerator gen = null;
		if(fileType.equalsIgnoreCase(OutputFileType.EXCEL_FILE.getFileType())) {
			gen = new ExcelGenerator();
			gen.printData(columnValues, entryList, rowCount, tableName);
		}else if(fileType.equalsIgnoreCase(OutputFileType.MYSQL_FILE.getFileType())) {
			gen = new MySQLGenerator();
			gen.printData(columnValues, entryList, rowCount, tableName);
		}
		
		
		
	}
	
	private List<DataEntry> buildDataArrays(List<String> columns, List<String> dataTypes, List<String> isRequired,
			String tableName) {
		int numberofIterations = columns.size();
		List<DataEntry> dataEntries = new ArrayList<DataEntry>();
		for(int i = 0; i < numberofIterations; i++) {
			DataEntry newEntry = new DataEntry(columns.get(i), dataTypes.get(i), isRequired.get(i), tableName);
			dataEntries.add(newEntry);
		}
		
		return dataEntries;
	}
}
