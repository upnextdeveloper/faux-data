package com.upnextdev.datagen.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.upnextdev.datagen.util.FileLocations;

public class MySQLDataOutput implements DataOutput {

	
	@Override
	public void createOutputFile(List<String> dataRows, List<String> columnValues, String tableName, String fileName) {
		FileLocations loc = new FileLocations();
		String mySqlFileName = loc.getLocalFileLocation() + fileName;
		File mySQLFile = null;
		
		try {
			mySQLFile = new File(mySqlFileName);
			
			if(mySQLFile.exists()) {
				System.out.println("File Already exists. error should not occur");
			}else {
				FileWriter fileWriter = new FileWriter(mySQLFile);
				
				for(int i =0; i < dataRows.size(); i++) {
					String insertRow = "";
					insertRow = insertRow + "INSERT INTO ";
					insertRow = insertRow + tableName + " (";
					
					
					for(String col: columnValues) {
						insertRow = insertRow + col + ",";
					}
					// remove the last comma
					insertRow = insertRow.substring(0, insertRow.length() - 1);
					
					insertRow = insertRow + ")";
					
					// now add in the row
					insertRow = insertRow + " VALUES";
					insertRow = insertRow + " (";
					
					String[] arr = dataRows.get(i).split(",");
					for(String arrValue: arr) {
						if(arrValue.trim().isBlank()) {
							// for blank values, we want to return the word NULL
							insertRow = insertRow + "NULL";
							insertRow = insertRow + ",";
						}else {
							insertRow = insertRow + "\"" + arrValue + "\"";
							insertRow = insertRow + ",";
						}
						
					}
					insertRow = insertRow.substring(0, insertRow.length() - 1);
					insertRow = insertRow + ");";
					
					fileWriter.write(insertRow);
					fileWriter.write("\n");
				}
				
				fileWriter.close();
			}
		}
			
		catch(IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("End of SQL File Creation " + mySqlFileName);
		}
		
	}
}
