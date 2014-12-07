package com.my.ws;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;
import org.springframework.beans.factory.annotation.Autowired;

public class WebServiceCall {
	@Autowired GlobalWeather globalWeather;
	@Autowired GlobalWeatherSoap port;
	
	public GlobalWeatherSoap getPort() {
		return port;
	}

	public void setPort(GlobalWeatherSoap port) {
		this.port = port;
	}

	public GlobalWeather getGlobalWeather() {
		return globalWeather;
	}

	public void setGlobalWeather(GlobalWeather globalWeather) {
		this.globalWeather = globalWeather;
	}

	public String getWeather(String city, String countryName) {
		return port.getWeather(city, countryName); 
	}

	public String getCitiesByCountry(String countryName) {
		return port.getCitiesByCountry(countryName); 
	}
	

}
