package com.upnextdev.datagen.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MySQLDataOutput implements DataOutput {

	@Override
	public void createOutputFile(List<String> dataRows, List<String> columnValues) {
		// TODO Auto-generated method stub
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
					fileContent.append("(");
					for(int j=0; j < arr.length; j++) {
						fileContent.append("'");
						fileContent.append(arr[j] + "'" + ",");
					}
					fileContent.deleteCharAt(fileContent.length()-1);
					fileContent.append(")");
					fileContent.append(",");
					fileContent.append("\n");
				}
				fileContent.deleteCharAt(fileContent.length()-2);
				fileContent.append(";");
				System.out.println(fileContent.toString() + fileContent.length());
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
