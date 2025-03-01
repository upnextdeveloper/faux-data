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
					StringBuilder tempBuilder = new StringBuilder();
					String insertRow = "";
					insertRow = insertRow + "INSERT INTO ";
					
					tempBuilder.append("INSERT INTO ");
					
					insertRow = insertRow + "table (";
					tempBuilder.append("table (");
					
					
					for(String col: columnValues) {
						insertRow = insertRow + col + ",";
						tempBuilder.append(col + ",");
					}
					// remove the last comma
					insertRow = insertRow.substring(0, insertRow.length() - 1);
					tempBuilder.deleteCharAt(tempBuilder.length()-1);
					
					insertRow = insertRow + ")";
					tempBuilder.append(")");
					
					// now add in the row
					insertRow = insertRow + " VALUES";
					tempBuilder.append(" VALUES");
					
					insertRow = insertRow + " (";
					tempBuilder.append(" (");
					
					String[] arr = dataRows.get(i).split(",");
					for(String arrValue: arr) {
						insertRow = insertRow + "'" + arrValue + "'";
						tempBuilder.append("'" + arrValue + "'");
						
						insertRow = insertRow + ",";
						tempBuilder.append(",");
					}
					insertRow = insertRow.substring(0, insertRow.length() - 1);
					tempBuilder.deleteCharAt(tempBuilder.length()-1);
					
					insertRow = insertRow + ");";
					tempBuilder.append(");");
					
					
					System.out.println("Insert Row: " + insertRow);
					fileWriter.write(insertRow);
					fileWriter.write("\n");
				//	fileWriter.write(tempBuilder.toString());
				}
				
			//	FileOutputStream os = new FileOutputStream(new File(mySqlFileName));
				fileWriter.close();
				//os.close();
			}
		}
			
		catch(IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("End of SQL File Creation " + mySqlFileName);
		}
		
	}
	
	public void createOutputFile2(List<String> dataRows, List<String> columnValues) {
		// TODO Auto-generated method stub
		System.out.println(columnValues);
		String mySqlFileName = "faux-data-mysql-" + System.currentTimeMillis() + ".sql";
		StringBuilder fileContent = new StringBuilder();
		File mySQLFile = null;
		try {
			mySQLFile = new File(mySqlFileName);
			
			if(mySQLFile.exists()) {
				System.out.println("File Already exists. error should not occur");
			}else {
				FileWriter fileWriter = new FileWriter(mySQLFile);
				// insert column names
				fileContent.append("INSERT INTO table (");

				columnValues.forEach(col -> {
					fileContent.append(col + ",");
				});
				fileContent.deleteCharAt(fileContent.length()-1);
				fileContent.append(")");
				fileContent.append("\n");
				
				// values for the sql file
				fileContent.append("VALUES ");
				
				for(int i=0; i < dataRows.size(); i++) {
					String[] arr = dataRows.get(i).split(",");
					System.out.println(dataRows.get(i));
					fileContent.append("(");
					for(int j=0; j < arr.length; j++) {
						fileContent.append("'");
						fileContent.append(arr[j] + "'" + ",");
					}
					fileContent.deleteCharAt(fileContent.length()-1);
					fileContent.append(")");
					fileContent.append(",");
					fileContent.append("\n");
					fileWriter.write(fileContent.toString());
				}
				fileContent.deleteCharAt(fileContent.length()-2);
				fileContent.append(";");
				fileContent.trimToSize();
				fileWriter.write(fileContent.toString());

				
				FileOutputStream os = new FileOutputStream(new File(mySqlFileName));
				fileWriter.close();
				os.close();
				
				System.out.println("New file created: " + mySqlFileName);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("End of SQL File Creation");
		}
	}

}
