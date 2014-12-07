package com.my.ws;
import static org.junit.Assert.assertEquals;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.Mockito;

import com.my.wsResponse.InvalidCapabilityException;
import com.my.wsResponse.InvalidNumberOfArgumentsException;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.then;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.when;

public class WebServiceTest {
	public final static String GET_WEATHER = "getWeather";
	public final static String GET_CITIES_BY_COUNTRY = "getCitiesByCountry";
	public final static String INVALID_CAPABILITY = "invalidCapability";
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

	WebServiceWrapper webService;
	@Mock WebServiceCall mockWebServiceCall = new WebServiceCall();
	String response;
	@Mock WebServiceCallValidator mockValidator = new WebServiceCallValidator();	
	GlobalWeather mockGlobalWeather;
	GlobalWeatherSoap mockPort;

	
	@Before
	public void setup(){
		initMocks(this);
		webService = new WebServiceWrapper();
		webService.setWebServiceCall(mockWebServiceCall);
		webService.setValidator(mockValidator);
	}
	
	@Test
	public void callingGetWeatherWith2ArgumentsIsValid() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException{
		mockValidatorToReturnAllValid();
		try{
			webService.callWebService(GET_WEATHER,CITY_NEW_YORK,COUNTRY_UNITED_STATES);
		}
		catch(InvalidNumberOfArgumentsException e){
			fail("exception thrown when it shouldn't have been");
		}
		catch(InvalidCapabilityException e){
			fail("exception thrown when it shouldn't have been");
		}
	}

	@Test
	public void callingGetWeatherWith1ArgumentIsNotvalid() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException{
		mockValidatorToReturnInvalidNumberOfArguments();
		
		when(webService).callWebService(GET_WEATHER,CITY_NEW_YORK);
		then(caughtException()).isInstanceOf(InvalidNumberOfArgumentsException.class)
			.hasMessage("expected 2 arguments but got 1 arguments");
	}

	@Test
	public void getWeatherWith0ArgumentsNotInvalid() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException{
		mockValidatorToReturnInvalidNumberOfArguments();
		
		when(webService).callWebService(GET_WEATHER);
		then(caughtException()).isInstanceOf(InvalidNumberOfArgumentsException.class)
			.hasMessage("expected 2 arguments but got 0 arguments");
	}

	@Test
	public void getWeatherWithMoreThan2ArgumentsIsNotInvalid() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException{
		mockValidatorToReturnInvalidNumberOfArguments();
		
		when(webService).callWebService(GET_WEATHER,CITY_NEW_YORK,COUNTRY_UNITED_STATES,ANOTHER_ARGUMENT);
		then(caughtException()).isInstanceOf(InvalidNumberOfArgumentsException.class)
			.hasMessage("expected 2 arguments but got 3 arguments");
	}

	@Test
	public void callingGetCitiesByCountryWith1ArgumentIsValid() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException{
		mockValidatorToReturnAllValid();
		try{
			webService.callWebService(GET_CITIES_BY_COUNTRY,COUNTRY_UNITED_STATES);
		}
		catch(InvalidNumberOfArgumentsException e){
			fail("exception thrown when it shouldn't have been");
		}
		catch(InvalidCapabilityException e){
			fail("exception thrown when it shouldn't have been");
		}
	}

	@Test
	public void callingGetCitiesByCounryWithMoreThan1ArgumentIsNotInvalid() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException{
		mockValidatorToReturnInvalidNumberOfArguments();
		
		when(webService).callWebService(GET_CITIES_BY_COUNTRY,COUNTRY_UNITED_STATES,ANOTHER_ARGUMENT);
		then(caughtException()).isInstanceOf(InvalidNumberOfArgumentsException.class)
			.hasMessage("expected 1 arguments but got 2 arguments");
	}

	@Test
	public void callingGetCitiesByCounryWithZeroArgumentsIsNotInvalid() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException{
		mockValidatorToReturnInvalidNumberOfArguments();
		
		when(webService).callWebService(GET_CITIES_BY_COUNTRY);
		then(caughtException()).isInstanceOf(InvalidNumberOfArgumentsException.class)
			.hasMessage("expected 1 arguments but got 0 arguments");
	}

	@Test
	public void callingInvalidCapabilityThrowsException() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException{
		mockValidatorToReturnInvalidCapability();
		
		when(webService).callWebService(INVALID_CAPABILITY,ANOTHER_ARGUMENT);
		then(caughtException()).isInstanceOf(InvalidCapabilityException.class)
			.hasMessage("Not a valid capability");
	}


	@Test
	public void callingGetWeatherServiceForNewYorkReturnsNewYorkWeather() 
			throws InvalidNumberOfArgumentsException, InvalidCapabilityException{
		mockValidatorToReturnAllValid();
		Mockito.when(mockWebServiceCall.getWeather(CITY_NEW_YORK,COUNTRY_UNITED_STATES))
			.thenReturn(GET_WEATHER_NEW_YORK_RESPONSE);

		String response = webService.callWebService(GET_WEATHER,CITY_NEW_YORK,COUNTRY_UNITED_STATES);
		assertEquals(GET_WEATHER_NEW_YORK_RESPONSE,response);
	}

	@Test
	public void callingGetCitiesByCountryServiceForUSReturnsUSCities() 
			throws InvalidNumberOfArgumentsException, InvalidCapabilityException{
		mockValidatorToReturnAllValid();
		Mockito.when(mockWebServiceCall.getCitiesByCountry(COUNTRY_UNITED_STATES))
			.thenReturn(GET_CITIES_BY_COUNTRY_RESPONSE);

		String response = webService.callWebService(GET_CITIES_BY_COUNTRY,COUNTRY_UNITED_STATES);
		assertEquals(GET_CITIES_BY_COUNTRY_RESPONSE,response);
	}

	private void mockValidatorToReturnAllValid() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException {
		Mockito.when(mockValidator.isValidCapability(anyString())).thenReturn(true);
		Mockito.when(mockValidator.hasCapabilityRightNumberOfArguments(anyInt(), (String[]) anyVararg()))
			.thenReturn(true);		
	}

	private void mockValidatorToReturnInvalidNumberOfArguments() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException {
		Mockito.when(mockValidator.isValidCapability(anyString())).thenReturn(true);
		Mockito.when(mockValidator.hasCapabilityRightNumberOfArguments(anyInt(), (String[]) anyVararg()))
			.thenReturn(false);		
	}

	private void mockValidatorToReturnInvalidCapability() 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException {
		Mockito.when(mockValidator.isValidCapability(anyString())).thenReturn(false);
		Mockito.when(mockValidator.hasCapabilityRightNumberOfArguments(anyInt(), (String[]) anyVararg()))
			.thenReturn(false);		
	}

}
