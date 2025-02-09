package com.upnextdev.datagen.generator;

public enum DataType {
	ID("Identification Number"),
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
	CURRENT_EDUCATION("Current Education");
	
	private final String dataType;
	
	DataType(String dataType){
		this.dataType = dataType;
	}

	public String getDataType() {
		return dataType;
	}
}
