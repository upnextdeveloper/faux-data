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
import com.upnextdev.datagen.util.CityStateData;
import com.upnextdev.datagen.util.CityStateData.CityStateZip;
import com.upnextdev.datagen.util.GenderedNames;

public abstract class DataGenerator {

	// class to do all the generation tasks
	// class to print output depending on child class
	private Faker dataFaker = new Faker(new Locale("en-US"));
	int newId = dataFaker.number().numberBetween(1, 100000);
	
	GenderedNames genderedNames = new GenderedNames();
	CityStateData csd = new CityStateData();
	List<CityStateZip> csdList = csd.getCityStates();
	Date randomBirthdayDate = null;
	int randomAgeFromBirthday = 0;
	String randomGender = "";
	
	String firstName = "";
	String middleName = "";
	String lastName = "";
	
	public DataGenerator() {
		// generate a random date by default (for birthday and create an age based off of it)
	}

	protected List<String> generateData(List<DataEntry> dataEntries, int rowCount) throws Exception {
		List<String> dataList = new ArrayList<String>();

		for (int i = 0; i < rowCount; i++) {
			randomBirthdayDate = dataFaker.date().birthday(0, 120);
			LocalDate localDateBday = randomBirthdayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int birthdayYear = localDateBday.getYear();
			int currentYear = Year.now().getValue();
			randomAgeFromBirthday = currentYear - birthdayYear;
			
			randomGender = dataFaker.demographic().sex();
			
			firstName = getGenderedName(randomGender);
			middleName = getGenderedName(randomGender);
			lastName = dataFaker.name().lastName();
			
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
//			String firstName = dataFaker.name().firstName();
//			if(randomGender.equalsIgnoreCase("Female")){
//				firstName = genderedNames.getRandomFemaleName();
//			}else if(randomGender.equalsIgnoreCase("Male")) {
//				firstName = genderedNames.getRandomMaleName();
//				// gender is optional non binary
//			}else {
//				firstName = dataFaker.name().firstName();
//			}
//			firstName = firstName.replaceAll("\'","");
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
//			String middleName = dataFaker.name().firstName();
//			if(randomGender.equalsIgnoreCase("Female")){
//				middleName = genderedNames.getRandomFemaleName();
//			}else if(randomGender.equalsIgnoreCase("Male")) {
//				middleName = genderedNames.getRandomMaleName();
//				// gender is optional non binary
//			}else {
//				middleName = dataFaker.name().firstName();
//			}
//			middleName = middleName.replaceAll("\'","");
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
//			String lastName = dataFaker.name().lastName();
//			lastName = lastName.replaceAll("\'","");
			if (required) {
				dataReturned = lastName;
			} else {
				if (shouldAddData()) {
					dataReturned = lastName;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.USERNAME.getDataType())) {
			String username = getFauxUserName(firstName, lastName);
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
			if(zipCode.trim() != "") {
				Integer parsedZipCode = (int) Double.parseDouble(zipCode);
				zipCode = parsedZipCode.toString();
			}else {
				zipCode = "";
			}
			
			
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
			String gender = randomGender;

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
			String birthday = formmatedDate(randomBirthdayDate);
			if (required) {
				dataReturned = birthday;
			} else {
				if (shouldAddData()) {
					dataReturned = birthday;
				} else {
					dataReturned = "";
				}
			}
		} else if (dataType.equalsIgnoreCase(DataType.AGE.getDataType())) {
			String age = String.valueOf(randomAgeFromBirthday);
			if (required) {
				dataReturned = age;
			} else {
				if (shouldAddData()) {
					dataReturned = age;
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
			String email = getFauxEmailAddress(firstName, lastName);
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
			String marital = "";
			
			if(randomAgeFromBirthday < 18) {
				marital = "Never married";
			}else {
				marital = dataFaker.demographic().maritalStatus();
			}
			
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
			String education = "";
			if(randomAgeFromBirthday < 5) {
				education = "No schooling completed";
			} else if(randomAgeFromBirthday == 5) {
				education = "Kindergarten";
			}
			else if(randomAgeFromBirthday > 5 && randomAgeFromBirthday < 18) {
				education = "Grade 1 though 11";
			} else {
				education = dataFaker.demographic().educationalAttainment();
			}
			
			if (required) {
				dataReturned = education;
			} else {
				if (shouldAddData()) {
					dataReturned = education;
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

	private String formmatedDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formmattedDate = formatter.format(date);
		return formmattedDate;
	}
	
	private String getGenderedName(String randomGender) {
		String firstName = "";
		if(randomGender.equalsIgnoreCase("Female")) {
			firstName = genderedNames.getRandomFemaleName();
		}else if(randomGender.equalsIgnoreCase("Male")) {
			firstName = genderedNames.getRandomMaleName();
		}else {
			firstName = dataFaker.name().firstName();
		}
		firstName = firstName.replaceAll("\'","");
		
		return firstName;
	}
	
	private String getFauxEmailAddress(String firstName, String lastName) {
		String emailAddress = "";
		if(firstName.trim().equalsIgnoreCase("")) {
			emailAddress = emailAddress + dataFaker.name().firstName();
		}else {
			emailAddress = emailAddress + firstName;
		}
		
		emailAddress = emailAddress + ".";
		
		if(lastName.trim().equalsIgnoreCase("")) {
			emailAddress = emailAddress + dataFaker.name().lastName();
		}else {
			emailAddress = emailAddress + lastName;
		}
		
		emailAddress = emailAddress + "@fauxemail.com";
		
		return emailAddress;
	}
	
	private String getFauxUserName(String firstName, String lastName) {
		String userName = "";
		if(firstName.trim().equalsIgnoreCase("")) {
			userName = userName + dataFaker.name().firstName();
		}else {
			userName = userName + firstName;
		}
		
		userName = userName + ".";
		
		if(lastName.trim().equalsIgnoreCase("")) {
			userName = userName + dataFaker.name().lastName();
		}else {
			userName = userName + lastName;
		}
		
		return userName;
	}
}
