package com.upnextdev.datagen.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import com.upnextdev.datagen.util.FileLocations;

@Component
public class ExcelDataOutput implements DataOutput{
	
	@Override
	public void createOutputFile(List<String> dataRows, List<String> columnValues, String tableName, String fileName) {
		// TODO Auto-generated method stub
		FileOutputStream opStream = null;
		FileLocations loc = new FileLocations();
		String excelName = loc.getLocalFileLocation() +  fileName;
		
		File excelFile = new File(excelName);
		
		Workbook workbook = new XSSFWorkbook();
		
		// create new sheet
		Sheet sheet = workbook.createSheet(tableName);
		
		// create the first row for the columns
		Row headersRow = sheet.createRow(0);
		
		for(int i=0; i < columnValues.size(); i++) {
			headersRow.createCell(i).setCellValue(columnValues.get(i));
		}

		for(int i=0; i < dataRows.size(); i++) {
			Row newRow = sheet.createRow(i + 1);
			String[] arr = dataRows.get(i).split(",");
			
			for(int j=0; j < arr.length; j++) {
				newRow.createCell(j).setCellValue(arr[j]);
			}
		}
	}

}
