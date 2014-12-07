package com.my.ws.call;

import org.apache.log4j.Logger;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class GetCitiesByCountry extends WSService{
	String city=null, country;

	public String callWS(String[] args){
//		Logger logger = Logger.getLogger(GetWeatherService.class);

		try { 
			service = new GlobalWeather(); 
			GlobalWeatherSoap port = service.getGlobalWeatherSoap(); 

			country= args[1];
			
//			logger.info("requesting getCitiesByCountry for country="+country);
			response = port.getCitiesByCountry(country); 
		
			System.out.println(response);
//			logger.info("response received OK; response=");
//			logger.info(response);
			} catch(Exception e) { 
				System.out.println(e);
//				logger.error(e); 
			} 
		return response;
	}
}
