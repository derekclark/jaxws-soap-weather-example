package com.my.ws;

public class WebServiceCallValidator {
	public final static String GET_WEATHER = "getWeather";
	public final static String GET_CITIES_BY_COUNTRY = "getCitiesByCountry";

	public boolean isValidCapability(String requestedCapability){
		if (requestedCapability.equals(GET_WEATHER)){
			return true;
		}
		if (requestedCapability.equals(GET_CITIES_BY_COUNTRY)){
			return true;
		}
		
		return false;
	}

	public boolean hasCapabilityRightNumberOfArguments
		(int noOfExpectedArgs, String... args){
		if (args.length != noOfExpectedArgs){
			return false;
		}
		return true;
	}
}
