package com.upnextdev.datagen.generator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
import com.upnextdev.datagen.entity.DataEntry;
import com.upnextdev.datagen.entity.DataFakerEntryRow;
import com.upnextdev.datagen.util.CityStateData;
import com.upnextdev.datagen.util.CityStateData.CityStateZip;
import com.upnextdev.datagen.util.GenderedNames;

public abstract class DataGenerator {

	// class to do all the generation tasks
	// class to print output depending on child class
	private Faker githubFaker = new Faker(new Locale("en-US"));
	private net.datafaker.Faker newFaker = new net.datafaker.Faker();
	private CityStateData csd = new CityStateData();
	private GenderedNames genderedNames = new GenderedNames();
	int newId = githubFaker.number().numberBetween(1, 100000);
	
	public DataGenerator() {
		// generate a random date by default (for birthday and create an age based off of it)
		
	}

	protected List<String> generateData(List<DataEntry> dataEntries, int rowCount) throws Exception {
		List<String> dataList = new ArrayList<String>();

		for (int i = 0; i < rowCount; i++) {
			dataList.add(printRowOfData(dataEntries));
			newId++;
		}

		return dataList;
	}

	private String printRowOfData(List<DataEntry> entries) throws Exception {
		DataFakerEntryRow dfer = new DataFakerEntryRow(githubFaker, newFaker, csd, genderedNames);
		String row = "";
		for (DataEntry entry : entries) {
			StringBuilder stringB = new StringBuilder();
			stringB.append(getRandomData(entry, dfer));
			row = row + "," + stringB.toString();
		}
		// remove first comma
		row = row.substring(1);
		return row;
	}

	private String getRandomData(DataEntry entry, DataFakerEntryRow dfer) throws Exception {
		String isRequired = entry.getIsRequired();
		String dataType = entry.getDataType();
		boolean required = true;
		String dataReturned = "";
		

		if (isRequired.equalsIgnoreCase("Y")) {
			required = true;
		} else {
			required = false;
		}

		if (dataType.equalsIgnoreCase(DataType.ID.getDataType())) {
			if (required) {
				dataReturned = String.valueOf(newId);
			} else {
				// the column value is not required
				// therefore it will be generated randomly
				if (shouldAddData()) {
					dataReturned = String.valueOf(newId);
				} else {
					dataReturned = "";
				}
			}

		} else if (dataType.equalsIgnoreCase(DataType.PAST_DATE.getDataType())) {
			String pastDate = dfer.getPastDate();
			if (required) {
				dataReturned = pastDate;
			} else {
				if (shouldAddData()) {
					dataReturned = pastDate;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.FIRST_NAME.getDataType())) {
			if (required) {
				dataReturned = dfer.getFirstName();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getFirstName();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.MIDDLE_NAME.getDataType())) {
			if (required) {
				dataReturned = dfer.getMiddleName();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getMiddleName();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.LAST_NAME.getDataType())) {
			if (required) {
				dataReturned = dfer.getLastName();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getLastName();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.USERNAME.getDataType())) {
			if (required) {
				dataReturned = dfer.getUserName();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getUserName();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.FUTURE_DATE.getDataType())) {
			if (required) {
				dataReturned = dfer.getFutureDate();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getFutureDate();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.STREET.getDataType())) {
			if (required) {
				dataReturned = dfer.getStreet();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getStreet();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.CITY.getDataType())) {
			if (required) {
				dataReturned = dfer.getCity();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getCity();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.STATE.getDataType())) {
			if (required) {
				dataReturned = dfer.getState();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getState();
				} else {
					dataReturned = "";
				}
			}

		} else if (dataType.equalsIgnoreCase(DataType.STATE_ABBR.getDataType())) {
			if (required) {
				dataReturned = dfer.getStateAbbr();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getStateAbbr();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.ZIP_CODE.getDataType())) {
			if (required) {
				dataReturned = dfer.getZipCode();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getZipCode();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.TRUE_FALSE.getDataType())) {
			if (required) {
				dataReturned = dfer.getTrueorFalse();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getTrueorFalse();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.GENDER.getDataType())) {
			if (required) {
				dataReturned = dfer.getGender();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getGender();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.BIRTHDAY.getDataType())) {
			if (required) {
				dataReturned = dfer.getBirthday();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getBirthday();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.AGE.getDataType())) {
			if (required) {
				dataReturned = dfer.getAge();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getAge();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.MONEY_POS.getDataType())) {
			if (required) {
				dataReturned = dfer.getMoneyPos();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getMoneyPos();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.MONEY_POSNEG.getDataType())) {
			if (required) {
				dataReturned = dfer.getMoneyPosNeg();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getMoneyPosNeg();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.POSITIVE_NUM.getDataType())) {
			if (required) {
				dataReturned = dfer.getPositiveNum();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getPositiveNum();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.EMAIL.getDataType())) {
			if (required) {
				dataReturned = dfer.getEmail();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getEmail();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.PHONE_NUM.getDataType())) {
			if (required) {
				dataReturned = dfer.getPhoneNum();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getPhoneNum();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.RACE.getDataType())) {
			if (required) {
				dataReturned = dfer.getRace();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getRace();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.MARITAL_STATUS.getDataType())) {
			if (required) {
				dataReturned = dfer.getMaritalStatus();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getMaritalStatus();
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.CURRENT_EDUCATION.getDataType())) {
			if (required) {
				dataReturned = dfer.getCurrentEducation();
			} else {
				if (shouldAddData()) {
					dataReturned = dfer.getCurrentEducation();
				} else {
					dataReturned = "";
				}
			}
		}  else {
			System.out.println(dataType + " does not exist");
			throw new Exception("Data type: " + dataType + " does not exist");
		}
		
		return dataReturned;
	}
	
	abstract public void printData(List <String> columnValues, List<DataEntry> e, int count, String tableName) throws Exception;
	// if a column is optional, it will generate data based on
	// if the below value is returned true
	private boolean shouldAddData() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
}
