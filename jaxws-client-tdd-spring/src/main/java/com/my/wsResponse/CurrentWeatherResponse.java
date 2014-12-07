package com.my.wsResponse;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CurrentWeather")
public class CurrentWeatherResponse {
	String location, time, wind, visibility, skyConditions, temperature, dewPoint,
		relativeHumidity, pressure, pressureTendency, status;

	@XmlElement(name="Location")
	public void setLocation(String location) {
		this.location = location;
	}
	@XmlElement(name="Time")
	public void setTime(String time) {
		this.time = time;
	}

	@XmlElement(name="Wind")
	public void setWind(String wind) {
		this.wind = wind;
	}

	@XmlElement(name="Visibility")
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	@XmlElement(name="SkyConditions")
	public void setSkyConditions(String skyConditions) {
		this.skyConditions = skyConditions;
	}

	@XmlElement(name="Temperature")
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	@XmlElement(name="DewPoint")
	public void setDewPoint(String dewPoint) {
		this.dewPoint = dewPoint;
	}

	@XmlElement(name="RelativeHumidity")
	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	@XmlElement(name="Pressure")
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	@XmlElement(name="PressureTendency")
	public void setPressureTendency(String pressureTendency) {
		this.pressureTendency = pressureTendency;
	}

	@XmlElement(name="Status")
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public String getTime() {
		return time;
	}
	public String getWind() {
		return wind;
	}
	public String getVisibility() {
		return visibility;
	}
	public String getSkyConditions() {
		return skyConditions;
	}
	public String getTemperature() {
		return temperature;
	}
	public String getDewPoint() {
		return dewPoint;
	}
	public String getRelativeHumidity() {
		return relativeHumidity;
	}
	public String getPressure() {
		return pressure;
	}
	public String getPressureTendency() {
		return pressureTendency;
	}
	public String getStatus() {
		return status;
	}
	
}
