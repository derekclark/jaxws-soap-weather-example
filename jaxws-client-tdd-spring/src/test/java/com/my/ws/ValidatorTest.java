package com.my.ws;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.then;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.when;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.my.wsResponse.InvalidCapabilityException;
import com.my.wsResponse.InvalidNumberOfArgumentsException;

public class ValidatorTest {
	public final static String GET_WEATHER = "getWeather";
	public final static String GET_CITIES_BY_COUNTRY = "getCitiesByCountry";
	public final static String INVALID_CAPABILITY = "invalidCapability";

	WebServiceCallValidator validator = new WebServiceCallValidator();
	
	private final static String ARGUMENT = "An argument";
	
	@Test
	public void getWeatherIsValidCapability() throws InvalidCapabilityException{
		assertTrue(validator.isValidCapability(GET_WEATHER));
	}

	@Test
	public void getCitiesByCountryIsValidCapability() throws InvalidCapabilityException{
		assertTrue(validator.isValidCapability(GET_CITIES_BY_COUNTRY));
	}

	@Test
	public void shouldFailIfInvalidCapability() throws InvalidCapabilityException{
		boolean response = validator.isValidCapability(INVALID_CAPABILITY);
		assertFalse(response);
	}	

	@Test
	public void shouldNotThrowExceptionIfCorrectNoOfArgsAreSupplied(){
		int noOfArgsSupplied=2;
		boolean response = 
				validator.hasCapabilityRightNumberOfArguments
				(noOfArgsSupplied,ARGUMENT,ARGUMENT);
		assertTrue(response);
	}

	@Test
	public void shouldFailIfWrongNumberOfArgumentsSupplied(){
		int noOfArgsSupplied=5;
		
		boolean response = 
				validator.hasCapabilityRightNumberOfArguments(noOfArgsSupplied,
				ARGUMENT,ARGUMENT);
		assertFalse(response);
	}

	@Test
	public void shouldSucceedIfRightNumberOfArgumentsSuppliedWhenZeroArgumentsExpected() {
		int noOfArgsSupplied=0;
		boolean response = 
				validator.hasCapabilityRightNumberOfArguments(noOfArgsSupplied);
		assertTrue(response);
	}

}
