package com.my.ws;

import static org.junit.Assert.assertEquals;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

import org.junit.Before;
import org.junit.Test;

import com.my.wsResponse.InvalidNumberOfArgumentsException;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.*;

public class WebServiceCallTest {

	GlobalWeather mockGlobalWeather;
	GlobalWeatherSoap mockPort;

	public final static String GET_WEATHER = "getWeather";
	public final static String GET_CITIES_BY_COUNTRY = "getCitiesByCountry";
	public final static String ANOTHER_ARGUMENT= "some value";
	public final static String CITY_NEW_YORK = "NEW YORK";
	public final static String CITY_WASHINGTON = "WASHINGTON";
	public final static String COUNTRY_UNITED_STATES = "US";
	public final static String GET_WEATHER_NEW_YORK_RESPONSE = "<?xml version=\"1.0\" encoding=\"utf-16\"?>"
			+"<CurrentWeather>"
			+"  <Location>NEW YORK LA GUARDIA AIRPORT , NY, United States (KLGA) 40-47N 73-53W 11M</Location>"
			+"<Time>Oct 24, 2014 - 04:51 AM EDT / 2014.10.24 0851 UTC</Time>"
			+"<Wind> from the WNW (300 degrees) at 14 MPH (12 KT):0</Wind>"
			+"<Visibility> 10 mile(s):0</Visibility>"
			+"<SkyConditions> overcast</SkyConditions>"
			+"<Temperature> 51.1 F (10.6 C)</Temperature>"
			+"<DewPoint> 43.0 F (6.1 C)</DewPoint>"
			+"<RelativeHumidity> 73%</RelativeHumidity>"
			+"<Pressure> 29.78 in. Hg (1008 hPa)</Pressure>"
			+"<PressureTendency> 0.02 inches (0.8 hPa) higher than three hours ago</PressureTendency>"
			+"<Status>Success</Status>"
			+"</CurrentWeather>";

	private final static String GET_CITIES_BY_COUNTRY_RESPONSE = "<NewDataSet>"
			+"<Table>"
			+"<Country>United States</Country>"
			+"<City>Claiborne Range, Airways Facilit</City>"
			+"</Table>"
			+"<Table>"
			+"<Country>United States</Country>"
			+"<City>Payson</City>"
			+"</Table>"
			+"<Table>"
			+"<Country>United States</Country>"
			+"<City>Custer, Custer County Airport</City>"
			+"</Table>"
			+"</NewDataSet>";

	WebServiceCall webServiceCall = new WebServiceCall();
	
	@Before
	public void setup(){
		initMocks(this);
		webServiceCall = new WebServiceCall();
		createWebServiceMocks();
	}

	private void createWebServiceMocks() {
		mockGlobalWeather = mock(GlobalWeather.class);		
		mockPort = mock(GlobalWeatherSoap.class);
		webServiceCall.setPort(mockPort);
		webServiceCall.setGlobalWeather(mockGlobalWeather);				
		
		GlobalWeatherSoap gws = mock(GlobalWeatherSoap.class);
		when (mockGlobalWeather.getGlobalWeatherSoap()).thenReturn(gws);
		when(mockPort.getWeather(CITY_NEW_YORK,COUNTRY_UNITED_STATES))
			.thenReturn(GET_WEATHER_NEW_YORK_RESPONSE);
		
		when(mockPort.getCitiesByCountry(COUNTRY_UNITED_STATES))
		.thenReturn(GET_CITIES_BY_COUNTRY_RESPONSE);

	}

	@Test
	public void callingGetWeatherServiceForNewYorkReturnsNewYorkWeather() 
			throws InvalidNumberOfArgumentsException{		
		String response = webServiceCall.getWeather(CITY_NEW_YORK,COUNTRY_UNITED_STATES);
		assertEquals(GET_WEATHER_NEW_YORK_RESPONSE,response);
	}

	@Test
	public void callingGetCitiesByCountryReturnsCityList() 
			throws InvalidNumberOfArgumentsException{		
		String response = webServiceCall.getCitiesByCountry(COUNTRY_UNITED_STATES);
		assertEquals(GET_CITIES_BY_COUNTRY_RESPONSE,response);
	}

}
