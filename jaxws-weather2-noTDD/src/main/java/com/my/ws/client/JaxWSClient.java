package com.my.ws.client; 

import com.my.ws.call.GetCitiesByCountry;
import com.my.ws.call.GetWeatherService;
import com.my.ws.call.WSService;
import com.my.ws.jaxb.unmarshall.CurrentWeatherResponse;
import com.my.ws.jaxb.unmarshall.GetCitiesResponse;
import org.apache.log4j.Logger;

public class JaxWSClient { 
	static WSService webService;
	static String response;
	
	public static void main(String[] args) { 	
//		final Logger logger = Logger.getLogger(JaxWSClient.class);
		
//		logger.info("starting");
		CurrentWeatherResponse currentWeatherResponse = null;
		GetCitiesResponse getCitiesResponse = null;
		String capability = args[0];
//		logger.info("calling capability="+capability + " for "+args[1]);
		try { 
			if (capability.equals("getWeather")){
//				logger.info("in get weather");
				webService = new GetWeatherService();
				response = webService.callWS(args);
				currentWeatherResponse= webService.unmarshal(CurrentWeatherResponse.class,response);
//				logger.info("dewpoint =" + currentWeatherResponse.getDewPoint());
			} else{
				webService = new GetCitiesByCountry();				
				response = webService.callWS(args);
				getCitiesResponse = webService.unmarshal(GetCitiesResponse.class,response);
//				logger.info("city =" + getCitiesResponse.getCities().get(0).getCity());
			}
			
		} catch(Exception e) { 
//			logger.info(e); 
		} 
	} 
	
} 

