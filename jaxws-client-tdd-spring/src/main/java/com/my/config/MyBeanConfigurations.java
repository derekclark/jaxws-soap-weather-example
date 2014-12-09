package com.my.config;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.my.ws.WebServiceCall;
import com.my.ws.WebServiceCallValidator;
import com.my.ws.WebServiceWrapper;

@Configuration
@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.my")
public class MyBeanConfigurations {
	@Bean
	public WebServiceWrapper webServiceWrapper(){
		return new WebServiceWrapper();
	}
	
	@Bean
	public WebServiceCall webServiceCall(){
		return new WebServiceCall();
	}
	
	@Bean
	public WebServiceCallValidator validator(){
		return new WebServiceCallValidator();
	}
	
	@Bean
	public GlobalWeather globalWeather(){
		return new GlobalWeather();
	}
	
	@Bean
	public GlobalWeatherSoap port(){
		GlobalWeather globalWeather = new GlobalWeather();
		GlobalWeatherSoap port = globalWeather.getGlobalWeatherSoap(); 
		return port;
	}

}
