###Building SOAP Weather Client

to build

wsimport -keep -d src http://www.webservicex.com/globalweather.asmx?WSDL

this generates web service stubs in src/net/webservicex

###Jaxws-weather-noTDD
to run...

cd bin

java com.my.ws.client.JaxWSClient "getWeather" "NEW YORK" "UNITED STATES"

java com.my.ws.client.JaxWSClient "getCitiesByCountry"  "UNITED STATES"

