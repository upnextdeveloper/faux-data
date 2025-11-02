package com.upnextdev.datagen.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

public class CityStateData {

	public CityStateData() {
		buildCityStatesList();
	}
	
	private List<CityStateZip> cityStates = new ArrayList<>();

	private void buildCityStatesList() {
		try {
			FileInputStream inputStream = new FileInputStream((ResourceUtils.getFile("classpath:static/" + "uscities.xlsx")));

			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				// skip the first one
				Row row = rowIterator.next();
				
				CityStateZip csz = new CityStateZip();
				// now take the first (0) cell for the city name
				csz.setCity(row.getCell(0).toString().replace(",", " "));
				// the third (2) cell for the state abbr
				csz.setStateAbbr(row.getCell(2).toString().replace(",", " "));
				// take the 4th (3) for the state name
				csz.setStateName(row.getCell(3).toString().replace(",", " "));
				//take the 6th (5) for the latitude
				csz.setLatitude(row.getCell(6).toString().replace(",", " "));
				//take the 7th (6) for the longitude
				csz.setLongitude(row.getCell(7).toString().replace(",", " "));
				// take the 14th (13) for the zip codes
				csz.setTimeZone(row.getCell(13).toString().replace(",", " "));
				// take the 16th (15) for the zip codes
				csz.setZipCode(row.getCell(15).toString());
				
				
				cityStates.add(csz);
			}
			// iterate through each row
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<CityStateZip> getCityStates() {
		return cityStates;
	}



	public void setCityStates(List<CityStateZip> cityStates) {
		this.cityStates = cityStates;
	}



	public class CityStateZip {
		String city;
		String stateAbbr;
		String stateName;
		String zipCode;
		String latitude;
		String longitude;
		String timeZone;
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getStateAbbr() {
			return stateAbbr;
		}
		public void setStateAbbr(String stateAbbr) {
			this.stateAbbr = stateAbbr;
		}
		public String getStateName() {
			return stateName;
		}
		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
		public String getZipCode() {
			return zipCode;
		}
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getTimeZone() {
			return timeZone;
		}
		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}
		
		
		
	}
}
