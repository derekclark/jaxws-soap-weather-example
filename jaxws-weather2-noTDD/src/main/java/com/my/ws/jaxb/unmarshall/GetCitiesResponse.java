package com.my.ws.jaxb.unmarshall;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NewDataSet")
public class GetCitiesResponse {

	List<Cities> cities;
	
	public List<Cities> getCities() {
		return cities;
	}
	
	@XmlElement(name="Table")	
	public void setCities(List<Cities> cities) {
		this.cities = cities;
	}
}
