package com.my.app;
import java.util.Arrays;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.my.ws.WebServiceCallValidator;
import com.my.ws.WebServiceWrapper;
import com.my.wsResponse.InvalidCapabilityException;
import com.my.wsResponse.InvalidNumberOfArgumentsException;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MyClientApp {

	GlobalWeather globalWeather;
	static WebServiceWrapper webServiceWrapper;
	
	public static void main(String[] args) throws InvalidCapabilityException{
		ApplicationContext ctx = SpringApplication.run(MyClientApp.class, args);

		System.out.println("calling getWeather");
//		WebServiceWrapper ws=new WebServiceWrapper();
		try {
			String response = webServiceWrapper.callWebService("getWeather",args);
			System.out.println("getWeather response="+response);
		} catch (InvalidNumberOfArgumentsException e) {
			e.printStackTrace();
		}
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
	}

	@Bean
	public WebServiceWrapper webServiceWrapper(){
		return new WebServiceWrapper();
	}
	
	@Bean
	public WebServiceCallValidator validator(){
		WebServiceCallValidator validator = new WebServiceCallValidator();
		return validator;
	}
	
	@Bean
	public GlobalWeather globalWeather(){
		globalWeather = new GlobalWeather();
		return globalWeather;
	}
	
	@Bean
	public GlobalWeatherSoap port(){
		GlobalWeather globalWeather = new GlobalWeather();
		GlobalWeatherSoap port = globalWeather.getGlobalWeatherSoap(); 
		return port;
	}
	
}
