package com.my.ws.jaxb.unmarshall;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UnmarshallXMLToObject {
	public CurrentWeatherResponse unmarshall(String xmlString){
		
     CurrentWeatherResponse currentWeather = new CurrentWeatherResponse();
	 try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CurrentWeatherResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			currentWeather = 
					(CurrentWeatherResponse) unmarshaller.unmarshal(reader);
						
			System.out.println(currentWeather);
	 
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
	 return currentWeather;
	}
}
