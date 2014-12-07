package com.my.ws.call;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class GetWeatherService extends WSService{

	String city=null, country;

	public String callWS(String[] args) throws JAXBException{
		Logger logger = Logger.getLogger(GetWeatherService.class);

		try { 
			service = new GlobalWeather(); 
			GlobalWeatherSoap port = service.getGlobalWeatherSoap(); 

			city = args[1];
			country= args[2];
			
			logger.info("requesting getWeather for city=" + city + " and country="+country);
			response = port.getWeather(city, country); 
	
			logger.info("response received OK; response=");
			logger.info(response);

			} catch(Exception e) { 
				logger.error(e); 
			} 

		return response;

	}
}
