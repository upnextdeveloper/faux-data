package com.upnextdev.datagen.generator;

public enum DataType {
	ID("Identification Number"),
	FIRST_NAME("First Name"),
	MIDDLE_NAME("Middle Name"),
	LAST_NAME("Last Name"),
	FULL_NAME("Full Name"),
	FULL_NAME_WMIDDLE("Full Name with Middle"),
	USERNAME("Username"),
	PAST_DATE("Past Date - Up to 50 Years"),
	FUTURE_DATE("Future Date - Up to 50 Years"),
	STREET("Street"),
	CITY("City"),
	STATE("State"),
	STATE_ABBR("State Abbreviation"),
	ZIP_CODE("Zip Code"),
	TRUE_FALSE("True/False"),
	GENDER("Gender"),
	BIRTHDAY("Birthday"),
	MONEY_POS("Money - Positive Only"),
	MONEY_POSNEG("Money - Positive/Negative"),
	POSITIVE_NUM("Positive Numbers"),
	EMAIL("Email"),
	PHONE_NUM("Phone Number"),
	RACE("Race"),
	MARITAL_STATUS("Marital Status"),
	CURRENT_EDUCATION("Current Education"),
	AGE("Age")
	;
	
	private final String dataType;
	
	DataType(String dataType){
		this.dataType = dataType;
	}

	public String getDataType() {
		return dataType;
	}
}
