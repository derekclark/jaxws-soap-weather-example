import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import com.my.ws.call.GetCitiesByCountry;
import com.my.ws.call.GetWeatherService;
import com.my.ws.call.WSService;
import com.my.ws.jaxb.unmarshall.CurrentWeatherResponse;
import com.my.ws.jaxb.unmarshall.GetCitiesResponse;
import com.my.ws.jaxb.unmarshall.UnmarshallXMLToObject;


public class TestUnmarshallToObject {
	UnmarshallXMLToObject unmarshallXMLToObject;
	GetCitiesResponse getCities; 
	static WSService webService;

	String response;

	@Before
	public void init(){
	}
	
	@Test
	public void testUnmarshallGetWeather(){
		webService = new GetWeatherService();
		CurrentWeatherResponse currentWeatherResponse;

		response="<?xml version=\"1.0\" encoding=\"utf-16\"?>"
				+"<CurrentWeather>"
				+"  <Location>NEW YORK LA GUARDIA AIRPORT , NY, United States (KLGA) 40-47N 73-53W 11M</Location>"
				+"<Time>Oct 24, 2014 - 04:51 AM EDT / 2014.10.24 0851 UTC</Time>"
				+"<Wind> from the WNW (300 degrees) at 14 MPH (12 KT):0</Wind>"
				+"<Visibility> 10 mile(s):0</Visibility>"
				+"<SkyConditions> overcast</SkyConditions>"
				+"<Temperature> 51.1 F (10.6 C)</Temperature>"
				+"<DewPoint> 43.0 F (6.1 C)</DewPoint>"
				+"<RelativeHumidity> 73%</RelativeHumidity>"
				+"<Pressure> 29.78 in. Hg (1008 hPa)</Pressure>"
				+"<PressureTendency> 0.02 inches (0.8 hPa) higher than three hours ago</PressureTendency>"
				+"<Status>Success</Status>"
				+"</CurrentWeather>";

		currentWeatherResponse= webService.unmarshal(CurrentWeatherResponse.class,response);
		System.out.println(currentWeatherResponse.getDewPoint());
	}
	
	@Test
	public void testUnmarshallGetCities() throws JAXBException{
		webService = new GetCitiesByCountry();
		GetCitiesResponse getCitiesResponse;

		response="<NewDataSet>"
		  		+"<Table>"
		    	+"<Country>United Kingdom</Country>"
		    	+"<City>Belfast / Aldergrove Airport</City>"
		  		+"</Table>"
		  		+"<Table>"
		        +"<Country>United Kingdom</Country>"
		        +"<City>Wainfleet</City>"
		        +"</Table>"
				+"</NewDataSet>";
		String[] args = {"","UNITED KINGDOM"};
		response = webService.callWS(args);
		System.out.println(response);
		getCitiesResponse= webService.unmarshal(GetCitiesResponse.class,response);
		System.out.println(getCitiesResponse.getCities().get(0).getCity());

	}

}
