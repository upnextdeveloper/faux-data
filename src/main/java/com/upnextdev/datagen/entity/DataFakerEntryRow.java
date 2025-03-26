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
	private String pastDate;
	private String futureDate;
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
		String pastDate = formmatedDate(githubDataFaker.date().past(18250, TimeUnit.DAYS));
		String futureDate = formmatedDate(githubDataFaker.date().future(18250, TimeUnit.DAYS));
		setPastDate(pastDate);
		setFutureDate(futureDate);
		
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

	public String getPastDate() {
		return pastDate;
	}

	public void setPastDate(String pastDate) {
		this.pastDate = pastDate;
	}

	public String getFutureDate() {
		return futureDate;
	}

	public void setFutureDate(String futureDate) {
		this.futureDate = futureDate;
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
