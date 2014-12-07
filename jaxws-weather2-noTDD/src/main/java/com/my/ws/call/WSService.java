package com.my.ws.call;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.WebServiceRef;

import com.my.ws.jaxb.unmarshall.CurrentWeatherResponse;
import com.my.ws.jaxb.unmarshall.Wrapper;

import net.webservicex.GlobalWeather;

public abstract class WSService{
	@WebServiceRef(wsdlLocation="http://www.webservicex.com/globalweather.asmx?WSDL") 
	static GlobalWeather service;  
	String response=null;

	public String callWS(String[] args) throws JAXBException{
		return null;
	}
	
	public <T> T unmarshal(Class clazz, String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller um = context.createUnmarshaller();
            return (T) um.unmarshal(new StringReader(xml));
        } catch (JAXBException je) {
            throw new RuntimeException("Error interpreting XML response", je);
        }
    }
	
	
}
