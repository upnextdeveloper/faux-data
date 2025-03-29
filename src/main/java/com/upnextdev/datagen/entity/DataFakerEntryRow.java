package com.upnextdev.datagen.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.upnextdev.datagen.util.CityStateData;
import com.upnextdev.datagen.util.GenderedNames;
import com.upnextdev.datagen.util.CityStateData.CityStateZip;

import net.datafaker.Faker;

public class DataFakerEntryRow {

	private String identification;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private String fullNameWithMiddle;
	private String userName;
	private String pastDate2Days;
	private String pastDate1Week;
	private String pastDate1Month;
	private String pastDate6Months;
	private String pastDate1Year;
	private String pastDate10Years;
	private String pastDate25Years;
	private String pastDate50Years;
	private String futureDate2Days;
	private String futureDate1Week;
	private String futureDate1Month;
	private String futureDate6Months;
	private String futureDate1Year;
	private String futureDate10Years;
	private String futureDate25Years;
	private String futureDate50Years;
	private String street;
	private String city;
	private String state;
	private String stateAbbr;
	private String zipCode;
	private String trueorFalse;
	private String gender;
	private String birthday;
	private String moneyPos;
	private String moneyPosNeg;
	private String positiveNum;
	private String email;
	private String phoneNum;
	private String race;
	private String maritalStatus;
	private String currentEducation;
	private String age;
	
	
	public DataFakerEntryRow(com.github.javafaker.Faker githubDataFaker, Faker dataFaker, CityStateData csd, GenderedNames genderedNames) {
		
		List<CityStateZip> csdList = csd.getCityStates();
		
		// birthday and age (person)
		Date randomBirthdayDate = githubDataFaker.date().birthday(0, 120);
		LocalDate localDateBday = randomBirthdayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int birthdayYear = localDateBday.getYear();
		int currentYear = Year.now().getValue();
		int randomAgeFromBirthday = currentYear - birthdayYear;
		
		setBirthday(formmatedDate(randomBirthdayDate));
		setAge(String.valueOf(randomAgeFromBirthday));
		
		
		// names (person)
		String randomGender = githubDataFaker.demographic().sex();
		String firstName = getGenderedName(githubDataFaker, randomGender, genderedNames);
		String middleName = getGenderedName(githubDataFaker, randomGender, genderedNames);
		String lastName = dataFaker.name().lastName();
		
		setGender(randomGender);
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setUserName(getFauxUserName(firstName, lastName, githubDataFaker, dataFaker));
		setEmail(getFauxEmailAddress(firstName, lastName, dataFaker));
		setPhoneNum(githubDataFaker.phoneNumber().cellPhone());
		setRace(githubDataFaker.demographic().race());
		setMaritalStatus(determineMaritalStatus(githubDataFaker, randomAgeFromBirthday));
		setCurrentEducation(determineEducationStatus(githubDataFaker, randomAgeFromBirthday));
		
		// dates
		setPastDate2Days(formmatedDate(githubDataFaker.date().past(2, TimeUnit.DAYS)));
		setPastDate1Week(formmatedDate(githubDataFaker.date().past(7, TimeUnit.DAYS)));
		setPastDate1Month(formmatedDate(githubDataFaker.date().past(30, TimeUnit.DAYS)));
		setPastDate6Months(formmatedDate(githubDataFaker.date().past(180, TimeUnit.DAYS)));
		setPastDate1Year(formmatedDate(githubDataFaker.date().past(365, TimeUnit.DAYS)));
		setPastDate10Years(formmatedDate(githubDataFaker.date().past(3650, TimeUnit.DAYS)));
		setPastDate25Years(formmatedDate(githubDataFaker.date().past(9131, TimeUnit.DAYS)));
		setPastDate50Years(formmatedDate(githubDataFaker.date().past(18250, TimeUnit.DAYS)));
		
		// future dates
		setFutureDate2Days(formmatedDate(githubDataFaker.date().future(2, TimeUnit.DAYS)));
		setFutureDate1Week(formmatedDate(githubDataFaker.date().future(7, TimeUnit.DAYS)));
		setFutureDate1Month(formmatedDate(githubDataFaker.date().future(30, TimeUnit.DAYS)));
		setFutureDate6Months(formmatedDate(githubDataFaker.date().future(180, TimeUnit.DAYS)));
		setFutureDate1Year(formmatedDate(githubDataFaker.date().future(365, TimeUnit.DAYS)));
		setFutureDate10Years(formmatedDate(githubDataFaker.date().future(3650, TimeUnit.DAYS)));
		setFutureDate25Years(formmatedDate(githubDataFaker.date().future(9131, TimeUnit.DAYS)));
		setFutureDate50Years(formmatedDate(githubDataFaker.date().future(18250, TimeUnit.DAYS)));
		
		// locations
		// get a random city/state/zip
		Random random = new Random();
		int randomCsdIndex = random.nextInt(csdList.size() - 1);
		CityStateZip randomlyChoosenCsz = csdList.get(randomCsdIndex);
		
		String street = githubDataFaker.address().streetAddress().toString();
		String city = randomlyChoosenCsz.getCity();
		String state = randomlyChoosenCsz.getStateName();
		String stateAbb = randomlyChoosenCsz.getStateAbbr();
		
		String[] randomZipCodes = randomlyChoosenCsz.getZipCode().split("\\s+");
		Random randomZipCodeIndex = new Random();
		int randomSelectedZipCodeIndex = 0;
		if(randomZipCodes.length - 1 > 0) {
			randomSelectedZipCodeIndex = randomZipCodeIndex.nextInt(randomZipCodes.length - 1);
		}
		String zipCode = randomZipCodes[randomSelectedZipCodeIndex];
		if(zipCode.endsWith(".0")) {
			// remove the decimal point at the end
			zipCode = zipCode.substring(0, zipCode.length() - 2);
		}
		
		setStreet(street);
		setCity(city);
		setState(state);
		setStateAbbr(stateAbb);
		setZipCode(zipCode);
		
		//true false
		String truefalse = String.valueOf(githubDataFaker.bool().bool());
		setTrueorFalse(truefalse);
		
		// money
		String posMoneyAmount = String.valueOf(githubDataFaker.number().randomDouble(2, 0, 10000));
		String posNegMoneyAmount = String.valueOf(githubDataFaker.number().randomDouble(2, -1000, 10000));
		setMoneyPos(posMoneyAmount);
		setMoneyPosNeg(posNegMoneyAmount);
	}
	
	private String determineMaritalStatus(com.github.javafaker.Faker githubDataFaker, int age) {
		String marital = "";
		
		if(age < 18) {
			marital = "Never married";
		}else {
			marital = githubDataFaker.demographic().maritalStatus();
		}
		
		return marital;
	}
	
	private String determineEducationStatus(com.github.javafaker.Faker githubDataFaker, int age) {
		String education = "";
		if(age < 5) {
			education = "No schooling completed";
		} else if(age == 5) {
			education = "Kindergarten";
		}
		else if(age > 5 && age < 18) {
			education = "Grade 1 though 11";
		} else {
			education = githubDataFaker.demographic().educationalAttainment();
		}
		
		return education;
	}
	
	private String formmatedDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formmattedDate = formatter.format(date);
		return formmattedDate;
	}
	
	private String getGenderedName(com.github.javafaker.Faker githubDataFaker, String randomGender, GenderedNames genderedNames) {
		String firstName = "";
		if(randomGender.equalsIgnoreCase("Female")) {
			firstName = genderedNames.getRandomFemaleName();
		}else if(randomGender.equalsIgnoreCase("Male")) {
			firstName = genderedNames.getRandomMaleName();
		}else {
			firstName = githubDataFaker.name().firstName();
		}
		firstName = firstName.replaceAll("\'","");
		
		return firstName;
	}
	
	private String getFauxEmailAddress(String firstName, String lastName, Faker dataFaker) {
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
	
	private String getFauxUserName(String firstName, String lastName, com.github.javafaker.Faker githubDataFaker, Faker dataFaker) {
		String userName = "";
		if(firstName.trim().equalsIgnoreCase("")) {
			userName = userName + githubDataFaker.name().firstName();
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

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullNameWithMiddle() {
		return fullNameWithMiddle;
	}

	public void setFullNameWithMiddle(String fullNameWithMiddle) {
		this.fullNameWithMiddle = fullNameWithMiddle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPastDate2Days() {
		return pastDate2Days;
	}

	public void setPastDate2Days(String pastDate2Days) {
		this.pastDate2Days = pastDate2Days;
	}

	public String getPastDate1Week() {
		return pastDate1Week;
	}

	public void setPastDate1Week(String pastDate1Week) {
		this.pastDate1Week = pastDate1Week;
	}

	public String getPastDate1Month() {
		return pastDate1Month;
	}

	public void setPastDate1Month(String pastDate1Month) {
		this.pastDate1Month = pastDate1Month;
	}

	public String getPastDate6Months() {
		return pastDate6Months;
	}

	public void setPastDate6Months(String pastDate6Months) {
		this.pastDate6Months = pastDate6Months;
	}

	public String getPastDate1Year() {
		return pastDate1Year;
	}

	public void setPastDate1Year(String pastDate1Year) {
		this.pastDate1Year = pastDate1Year;
	}

	public String getPastDate10Years() {
		return pastDate10Years;
	}

	public void setPastDate10Years(String pastDate10Years) {
		this.pastDate10Years = pastDate10Years;
	}

	public String getPastDate25Years() {
		return pastDate25Years;
	}

	public void setPastDate25Years(String pastDate25Years) {
		this.pastDate25Years = pastDate25Years;
	}

	public String getPastDate50Years() {
		return pastDate50Years;
	}

	public void setPastDate50Years(String pastDate50Years) {
		this.pastDate50Years = pastDate50Years;
	}
	
	public String getFutureDate2Days() {
		return futureDate2Days;
	}

	public void setFutureDate2Days(String futureDate2Days) {
		this.futureDate2Days = futureDate2Days;
	}

	public String getFutureDate1Week() {
		return futureDate1Week;
	}

	public void setFutureDate1Week(String futureDate1Week) {
		this.futureDate1Week = futureDate1Week;
	}

	public String getFutureDate1Month() {
		return futureDate1Month;
	}

	public void setFutureDate1Month(String futureDate1Month) {
		this.futureDate1Month = futureDate1Month;
	}

	public String getFutureDate6Months() {
		return futureDate6Months;
	}

	public void setFutureDate6Months(String futureDate6Months) {
		this.futureDate6Months = futureDate6Months;
	}

	public String getFutureDate1Year() {
		return futureDate1Year;
	}

	public void setFutureDate1Year(String futureDate1Year) {
		this.futureDate1Year = futureDate1Year;
	}

	public String getFutureDate10Years() {
		return futureDate10Years;
	}

	public void setFutureDate10Years(String futureDate10Years) {
		this.futureDate10Years = futureDate10Years;
	}

	public String getFutureDate25Years() {
		return futureDate25Years;
	}

	public void setFutureDate25Years(String futureDate25Years) {
		this.futureDate25Years = futureDate25Years;
	}

	public String getFutureDate50Years() {
		return futureDate50Years;
	}

	public void setFutureDate50Years(String futureDate50Years) {
		this.futureDate50Years = futureDate50Years;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateAbbr() {
		return stateAbbr;
	}

	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTrueorFalse() {
		return trueorFalse;
	}

	public void setTrueorFalse(String trueorFalse) {
		this.trueorFalse = trueorFalse;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMoneyPos() {
		return moneyPos;
	}

	public void setMoneyPos(String moneyPos) {
		this.moneyPos = moneyPos;
	}

	public String getMoneyPosNeg() {
		return moneyPosNeg;
	}

	public void setMoneyPosNeg(String moneyPosNeg) {
		this.moneyPosNeg = moneyPosNeg;
	}

	public String getPositiveNum() {
		return positiveNum;
	}

	public void setPositiveNum(String positiveNum) {
		this.positiveNum = positiveNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCurrentEducation() {
		return currentEducation;
	}

	public void setCurrentEducation(String currentEducation) {
		this.currentEducation = currentEducation;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
}
