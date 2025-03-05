package com.upnextdev.datagen.generator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
import com.upnextdev.datagen.entity.DataEntry;
import com.upnextdev.datagen.util.CityStateData;
import com.upnextdev.datagen.util.CityStateData.CityStateZip;

public abstract class DataGenerator {

	// class to do all the generation tasks
	// class to print output depending on child class
	private Faker dataFaker = new Faker(new Locale("en-US"));
	int newId = dataFaker.number().numberBetween(1, 100000);
	
	CityStateData csd = new CityStateData();
	
	List<CityStateZip> csdList = csd.getCityStates();

	protected List<String> generateData(List<DataEntry> dataEntries, int rowCount) throws Exception {
		List<String> dataList = new ArrayList<String>();

		for (int i = 0; i < rowCount; i++) {
			dataList.add(printRowOfData(dataEntries));
			newId++;
		}

		return dataList;
	}

	private String printRowOfData(List<DataEntry> entries) throws Exception {
		String row = "";
		Random random = new Random();
		int randomCsdIndex = random.nextInt(csdList.size() - 1);
		
		CityStateZip randomlyChoosenCsz = csdList.get(randomCsdIndex);
		for (DataEntry entry : entries) {
			StringBuilder stringB = new StringBuilder();
			
			stringB.append(getRandomData(entry, randomlyChoosenCsz));
			row = row + "," + stringB.toString();
		}
		// remove first comma
		row = row.substring(1);
		return row;
	}

	private String getRandomData(DataEntry entry, CityStateZip randomlyChoosenCsz) throws Exception {
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
			String pastDate = formmatedDate(dataFaker.date().past(18250, TimeUnit.DAYS));
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
			String firstName = dataFaker.name().firstName();
			firstName = firstName.replaceAll("\'","");
			if (required) {
				dataReturned = firstName;
			} else {
				if (shouldAddData()) {
					dataReturned = firstName;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.MIDDLE_NAME.getDataType())) {
			String middleName = dataFaker.name().firstName();
			middleName = middleName.replaceAll("\'","");
			if (required) {
				dataReturned = middleName;
			} else {
				if (shouldAddData()) {
					dataReturned = middleName;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.LAST_NAME.getDataType())) {
			String lastName = dataFaker.name().lastName();
			lastName = lastName.replaceAll("\'","");
			if (required) {
				dataReturned = lastName;
			} else {
				if (shouldAddData()) {
					dataReturned = lastName;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.FULL_NAME.getDataType())) {
			String fullName = dataFaker.name().lastName();
			fullName = fullName.replaceAll("\'","");
			if (required) {
				dataReturned = fullName;
			} else {
				if (shouldAddData()) {
					dataReturned = fullName;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.FULL_NAME_WMIDDLE.getDataType())) {
			String fullNameWithMiddle = dataFaker.name().nameWithMiddle();
			fullNameWithMiddle = fullNameWithMiddle.replaceAll("\'","");
			if (required) {
				dataReturned = fullNameWithMiddle;
			} else {
				if (shouldAddData()) {
					dataReturned = fullNameWithMiddle;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.USERNAME.getDataType())) {
			String username = dataFaker.name().username();
			if (required) {
				dataReturned = username;
			} else {
				if (shouldAddData()) {
					dataReturned = username;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.FUTURE_DATE.getDataType())) {
			String futureDate = formmatedDate(dataFaker.date().future(18250, TimeUnit.DAYS));
			if (required) {
				dataReturned = futureDate;
			} else {
				if (shouldAddData()) {
					dataReturned = futureDate;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.STREET.getDataType())) {
			String street = dataFaker.address().streetAddress().toString();
			if (required) {
				dataReturned = street;
			} else {
				if (shouldAddData()) {
					dataReturned = street;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.CITY.getDataType())) {
			String city = randomlyChoosenCsz.getCity();
			if (required) {
				dataReturned = city;
			} else {
				if (shouldAddData()) {
					dataReturned = city;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.STATE.getDataType())) {
			String state = randomlyChoosenCsz.getStateName();
			if (required) {
				dataReturned = state;
			} else {
				if (shouldAddData()) {
					dataReturned = state;
				} else {
					dataReturned = "";
				}
			}

		} else if (dataType.equalsIgnoreCase(DataType.STATE_ABBR.getDataType())) {
			String stateAbb = randomlyChoosenCsz.getStateAbbr();
			if (required) {
				dataReturned = stateAbb;
			} else {
				if (shouldAddData()) {
					dataReturned = stateAbb;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.ZIP_CODE.getDataType())) {
			String[] randomZipCodes = randomlyChoosenCsz.getZipCode().split("\\s+");
			Random random = new Random();
			int randomSelectedZipCodeIndex = 0;
			if(randomZipCodes.length - 1 > 0) {
				randomSelectedZipCodeIndex = random.nextInt(randomZipCodes.length - 1);
			}
			String zipCode = randomZipCodes[randomSelectedZipCodeIndex];
			Integer parsedZipCode = (int) Double.parseDouble(zipCode);
			
			
			zipCode = parsedZipCode.toString();
			if (required) {
				dataReturned = zipCode;
			} else {
				if (shouldAddData()) {
					dataReturned = zipCode;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.TRUE_FALSE.getDataType())) {
			String truefalse = String.valueOf(dataFaker.bool().bool());
			if (required) {
				dataReturned = truefalse;
			} else {
				if (shouldAddData()) {
					dataReturned = truefalse;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.GENDER.getDataType())) {
			String gender = dataFaker.demographic().sex();
			if (required) {
				dataReturned = gender;
			} else {
				if (shouldAddData()) {
					dataReturned = gender;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.BIRTHDAY.getDataType())) {
			// String birthday = dataFaker.date().birthday().toString();
			String birthday = formmatedDate(dataFaker.date().birthday());
			if (required) {
				dataReturned = birthday;
			} else {
				if (shouldAddData()) {
					dataReturned = birthday;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.MONEY_POS.getDataType())) {
			String moneyAmount = String.valueOf(dataFaker.number().randomDouble(2, 0, 10000));
			if (required) {
				dataReturned = moneyAmount;
			} else {
				if (shouldAddData()) {
					dataReturned = moneyAmount;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.MONEY_POSNEG.getDataType())) {
			String moneyAmount = String.valueOf(dataFaker.number().randomDouble(2, -1000, 10000));
			if (required) {
				dataReturned = moneyAmount;
			} else {
				if (shouldAddData()) {
					dataReturned = moneyAmount;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.POSITIVE_NUM.getDataType())) {
			String moneyAmount = String.valueOf(dataFaker.number().numberBetween(0, 50000));
			if (required) {
				dataReturned = moneyAmount;
			} else {
				if (shouldAddData()) {
					dataReturned = moneyAmount;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.EMAIL.getDataType())) {
			String email = dataFaker.internet().emailAddress();
			if (required) {
				dataReturned = email;
			} else {
				if (shouldAddData()) {
					dataReturned = email;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.PHONE_NUM.getDataType())) {
			String phoneNumber = dataFaker.phoneNumber().cellPhone();
			if (required) {
				dataReturned = phoneNumber;
			} else {
				if (shouldAddData()) {
					dataReturned = phoneNumber;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.RACE.getDataType())) {
			String race = dataFaker.demographic().race();
			if (required) {
				dataReturned = race;
			} else {
				if (shouldAddData()) {
					dataReturned = race;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.MARITAL_STATUS.getDataType())) {
			String marital = dataFaker.demographic().maritalStatus();
			if (required) {
				dataReturned = marital;
			} else {
				if (shouldAddData()) {
					dataReturned = marital;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.CURRENT_EDUCATION.getDataType())) {
			String education = dataFaker.demographic().educationalAttainment();
			if (required) {
				dataReturned = education;
			} else {
				if (shouldAddData()) {
					dataReturned = education;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.AGE.getDataType())) {
			String education = String.valueOf(dataFaker.number().numberBetween(0, 110));
			if (required) {
				dataReturned = education;
			} else {
				if (shouldAddData()) {
					dataReturned = education;
				} else {
					dataReturned = "";
				}
			}
		} else {
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

	private String formmatedDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formmattedDate = formatter.format(date);
		return formmattedDate;
	}
}
