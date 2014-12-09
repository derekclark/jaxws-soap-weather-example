package com.my.ws;

import net.webservicex.GlobalWeather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.my.wsResponse.InvalidCapabilityException;
import com.my.wsResponse.InvalidNumberOfArgumentsException;

@Component
public class WebServiceWrapper {
	private final static String GET_WEATHER= "getWeather";
	private final static String GET_CITIES_BY_COUNTRY= "getCitiesByCountry";

	private final static int GET_WEATHER_EXPECTED_NO_OF_ARGUMENTS = 2;
	private final static int GET_CITIES_BY_COUNTRY_EXPECTED_NO_OF_ARGUMENTS = 1;

	static GlobalWeather service;  

	@Autowired WebServiceCall webServiceCall;
	@Autowired 
	WebServiceCallValidator validator;
	
	public WebServiceCallValidator getValidator() {
		return validator;
	}

	public void setValidator(WebServiceCallValidator validator) {
		this.validator = validator;
	}

	public WebServiceCall getWebServiceCall() {
		return webServiceCall;
	}

	public void setWebServiceCall(WebServiceCall webServiceCall) {
		this.webServiceCall = webServiceCall;
	}

	public String callWebService(String capability, String... arguments) 
			throws InvalidCapabilityException, InvalidNumberOfArgumentsException {
		
		if (!validator.isValidCapability(capability)){
			throw new InvalidCapabilityException("Not a valid capability");
		}
				
		if (capability.equals(GET_WEATHER)){
			return callGetWeather(arguments);
		}

		if (capability.equals(GET_CITIES_BY_COUNTRY)){
			return callGetCitiesByCountry(arguments);
		}

		return null;
	}

	private String callGetCitiesByCountry(String... arguments) throws InvalidNumberOfArgumentsException {
		int noOfExpectedArgs = GET_CITIES_BY_COUNTRY_EXPECTED_NO_OF_ARGUMENTS;
		if (!validator.hasCapabilityRightNumberOfArguments(noOfExpectedArgs, arguments)){
			throwException(noOfExpectedArgs, arguments);
		}

		String countryName=arguments[0];
		return webServiceCall.getCitiesByCountry(countryName);

	}

	private String callGetWeather(String... arguments) throws InvalidNumberOfArgumentsException {
		int noOfExpectedArgs = GET_WEATHER_EXPECTED_NO_OF_ARGUMENTS;

		if (!validator.hasCapabilityRightNumberOfArguments(noOfExpectedArgs, arguments)){
			throwException(noOfExpectedArgs, arguments);
		}

		String city=arguments[0];
		String countryName = arguments[1];
		return webServiceCall.getWeather(city, countryName);
	}

	private void throwException(int noOfExpectedArgs, String... arguments) throws InvalidNumberOfArgumentsException {
		throw new InvalidNumberOfArgumentsException("expected " + noOfExpectedArgs + " arguments" 
		+ " but got " + arguments.length + " arguments");
	}

}
