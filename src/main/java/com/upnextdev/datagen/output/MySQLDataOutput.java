package com.upnextdev.datagen.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MySQLDataOutput implements DataOutput {

	
	@Override
	public void createOutputFile(List<String> dataRows, List<String> columnValues) {
		String mySqlFileName = "faux-data-mysql-" + System.currentTimeMillis() + ".sql";
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
					insertRow = insertRow + "table (";
					
					
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
						insertRow = insertRow + "'" + arrValue + "'";
						insertRow = insertRow + ",";
					}
					insertRow = insertRow.substring(0, insertRow.length() - 1);
					insertRow = insertRow + ");";
					
					System.out.println("Insert Row: " + insertRow);
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
