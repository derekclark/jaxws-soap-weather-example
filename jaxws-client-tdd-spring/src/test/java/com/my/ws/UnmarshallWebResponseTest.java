package com.my.ws;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.then;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.when;
import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import net.webservicex.GetWeatherResponse;

import org.junit.Before;
import org.junit.Test;

import com.my.unmarshall.UnmarshallXMLToObject;
import com.my.wsResponse.CurrentWeatherResponse;
import com.my.wsResponse.GetCitiesResponse;

public class UnmarshallWebResponseTest {
	UnmarshallXMLToObject unmarshallXMLToObject = new UnmarshallXMLToObject();
	String response;

	private final static String DEWPOINT =  "43.0 F (6.1 C)";
	private final static String LOCATION="NEW YORK LA GUARDIA AIRPORT , NY, United States (KLGA) 40-47N 73-53W 11M";
	private final static String TIME="Oct 24, 2014 - 04:51 AM EDT / 2014.10.24 0851 UTC";
	private final static String WIND="from the WNW (300 degrees) at 14 MPH (12 KT):0";
	private final static String VISIBILITY=" 10 mile(s):0";
	private final static String SKY_CONDITIONS="overcast";
	private final static String TEMPERATURE =  "51.1 F (10.6 C)";
	private final static String RELATIVE_HUMIDITY = "73%";
	private final static String PRESSURE="29.78 in. Hg (1008 hPa)";
	private final static String PRESSURE_TENDENCY = "0.02 inches (0.8 hPa) higher than three hours ago";
	private final static String STATUS="Success";
	private final static String COUNTRY = "United Kingdom";
	private final static String CITY_BELFAST="Belfast / Aldergrove Airport";
	private final static String CITY_WAINFLEET = "Wainfleet";
	
	@Before
	public void init(){
	}
	
	@Test
	public void testUnmarshallGetWeather(){
		CurrentWeatherResponse currentWeatherResponse;

		response="<?xml version=\"1.0\" encoding=\"utf-16\"?>"
				+"<CurrentWeather>"
				+"  <Location>"+LOCATION+"</Location>"
				+"<Time>"+TIME+"</Time>"
				+"<Wind>"+WIND+"</Wind>"
				+"<Visibility>"+VISIBILITY+"</Visibility>"
				+"<SkyConditions>"+SKY_CONDITIONS+"</SkyConditions>"
				+"<Temperature>"+TEMPERATURE+"</Temperature>"
				+"<DewPoint>"+DEWPOINT+"</DewPoint>"
				+"<RelativeHumidity>"+RELATIVE_HUMIDITY+"</RelativeHumidity>"
				+"<Pressure>"+PRESSURE+"</Pressure>"
				+"<PressureTendency>"+PRESSURE_TENDENCY+"</PressureTendency>"
				+"<Status>"+STATUS+"</Status>"
				+"</CurrentWeather>";

		currentWeatherResponse= unmarshallXMLToObject.unmarshal(CurrentWeatherResponse.class,response);
		assertEquals(LOCATION,currentWeatherResponse.getLocation());
		assertEquals(TIME,currentWeatherResponse.getTime());
		assertEquals(VISIBILITY,currentWeatherResponse.getVisibility());
		assertEquals(SKY_CONDITIONS,currentWeatherResponse.getSkyConditions());
		assertEquals(DEWPOINT,currentWeatherResponse.getDewPoint());
		assertEquals(RELATIVE_HUMIDITY,currentWeatherResponse.getRelativeHumidity());
		assertEquals(PRESSURE,currentWeatherResponse.getPressure());
		assertEquals(PRESSURE_TENDENCY,currentWeatherResponse.getPressureTendency());
		assertEquals(STATUS,currentWeatherResponse.getStatus());
	}
	
	@Test
	public void testUnmarshallGetCitiesByCountry() throws JAXBException{
		GetCitiesResponse getCitiesByCountryResponse; 

		response="<NewDataSet>"
		  		+"<Table>"
		    	+"<Country>"+COUNTRY+"</Country>"
		    	+"<City>"+CITY_BELFAST+"</City>"
		  		+"</Table>"
		  		+"<Table>"
		        +"<Country>"+COUNTRY+"</Country>"
		        +"<City>"+CITY_WAINFLEET+"</City>"
		        +"</Table>"
				+"</NewDataSet>";
		
		getCitiesByCountryResponse = unmarshallXMLToObject.unmarshal(GetCitiesResponse.class,response);

		assertEquals(COUNTRY,getCitiesByCountryResponse.getCities().get(0).getCountry());
		assertEquals(CITY_BELFAST,getCitiesByCountryResponse.getCities().get(0).getCity());
		assertEquals(COUNTRY,getCitiesByCountryResponse.getCities().get(1).getCountry());
		assertEquals(CITY_WAINFLEET,getCitiesByCountryResponse.getCities().get(1).getCity());
	}

	@Test
	public void unexpectedGetWeatherResponseShouldThrowException() throws JAXBException{
		response="<UNEXPECTED RESPONSE>";
		
		when(unmarshallXMLToObject).unmarshal(GetWeatherResponse.class, response);
		then(caughtException()).isInstanceOf(RuntimeException.class)
			.hasMessage("Error interpreting XML response");
	}

	@Test
	public void unexpectedGetCitiesByCountryResponseShouldThrowException() throws JAXBException{
		response="<UNEXPECTED RESPONSE>";
		
		when(unmarshallXMLToObject).unmarshal(GetCitiesResponse.class, response);
		then(caughtException()).isInstanceOf(RuntimeException.class)
			.hasMessage("Error interpreting XML response");
	}

}
