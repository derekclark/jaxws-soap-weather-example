package com.my.app;
import java.util.Arrays;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.my.ws.WebServiceCall;
import com.my.ws.WebServiceCallValidator;
import com.my.ws.WebServiceWrapper;
import com.my.wsResponse.InvalidCapabilityException;
import com.my.wsResponse.InvalidNumberOfArgumentsException;
import com.my.config.BeanConfig;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages="com.my")
@Import (BeanConfig.class)

public class MyClientApp implements CommandLineRunner{

//	GlobalWeather globalWeather;
	@Autowired private WebServiceWrapper webServiceWrapper;
	
	public static void main(String[] args) throws InvalidCapabilityException{
		ApplicationContext ctx = SpringApplication.run(MyClientApp.class);
//        System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }

	}
	
	public void run(String... args) throws InvalidCapabilityException{
		System.out.println("getWeather for New York");
		try {
			String response = webServiceWrapper.callWebService("getWeather","NEW YORK","UNITED STATES");
			System.out.println("getWeather response="+response);
		} catch (InvalidNumberOfArgumentsException e) {
			e.printStackTrace();
		}
		
		System.out.println("getCities for UNITED STATES");
		try {
			String response = webServiceWrapper.callWebService("getCitiesByCountry","UNITED STATES");
			System.out.println("getWeather response="+response);
		} catch (InvalidNumberOfArgumentsException e) {
			e.printStackTrace();
		}

	}

	
}
