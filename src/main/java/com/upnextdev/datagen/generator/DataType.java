package com.upnextdev.datagen.generator;

public enum DataType {
	ID("Identification Number"),
	FIRST_NAME("First Name"),
	MIDDLE_NAME("Middle Name"),
	LAST_NAME("Last Name"),
	FULL_NAME("Full Name"),
	FULL_NAME_WMIDDLE("Full Name with Middle"),
	USERNAME("Username"),
	// past dates
	PAST_DATE_2DAYS("Past Date - Up to 2 Days"),
	PAST_DATE_1WEEK("Past Date - Up to 1 Week"),
	PAST_DATE_1MONTH("Past Date - Up to 1 Month"),
	PAST_DATE_6MONTHS("Past Date - Up to 6 Months"),
	PAST_DATE_1YEAR("Past Date - Up to 1 Year"),
	PAST_DATE_10YEARS("Past Date - Up to 10 Years"),
	PAST_DATE_25YEARS("Past Date - Up to 25 Years"),
	PAST_DATE_50YEARS("Past Date - Up to 50 Years"),
	PRESENT_DATE("Present Day"),
	// future dates
	FUTURE_DATE_2DAYS("Future Date - Up to 2 Days"),
	FUTURE_DATE_1WEEK("Future Date - Up to 1 Week"),
	FUTURE_DATE_1MONTH("Future Date - Up to 1 Month"),
	FUTURE_DATE_6MONTHS("Future Date - Up to 6 Months"),
	FUTURE_DATE_1YEAR("Future Date - Up to 1 Year"),
	FUTURE_DATE_10YEARS("Future Date - Up to 10 Years"),
	FUTURE_DATE_25YEARS("Future Date - Up to 25 Years"),
	FUTURE_DATE_50YEARS("Future Date - Up to 50 Years"),
	STREET("Street"),
	CITY("City"),
	STATE("State"),
	STATE_ABBR("State Abbreviation"),
	ZIP_CODE("Zip Code"),
	LATITUDE("Latitude"),
	LONGITUDE("Longitude"),
	TIME_ZONE("Time Zone"),
	TRUE("True"),
	FALSE("False"),
	TRUE_FALSE("True/False"),
	GENDER("Gender"),
	BIRTHDAY("Birthday"),
	BDAY18("Bday18"),
	MONEY_POS("Money - Positive Only"),
	MONEY_POSNEG("Money - Positive/Negative"),
	POSITIVE_NUM("Positive Numbers"),
	EMAIL("Email"),
	PHONE_NUM("Phone Number"),
	RACE("Race"),
	MARITAL_STATUS("Marital Status"),
	CURRENT_EDUCATION("Current Education"),
	AGE("Age"),
	AGE_18("Age18")
	;
	
	private final String dataType;
	
	DataType(String dataType){
		this.dataType = dataType;
	}

	public String getDataType() {
		return dataType;
	}
}
