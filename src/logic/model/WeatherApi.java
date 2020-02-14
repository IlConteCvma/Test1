package logic.model;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherApi {

	private static RequestHttpApi connection = new RequestHttpApi();
	private static String apikey = "ZAAAfY4M6OPp0uA0GelvMwZPTZRAANm3";
	
	public String getRainIntensity() throws IOException{
		String urlRequest = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/120?apikey="+apikey;
    	String response = connection.sendRequest(urlRequest);
    	JSONObject positionObj = new JSONObject(response);
    	JSONArray arr = positionObj.getJSONArray("DailyForecasts");
    	JSONObject obj = arr.getJSONObject(0).getJSONObject("Day");
    	Boolean piove = obj.getBoolean("HasPrecipitation");
    	String intensity = "null";
    	if(Boolean.TRUE.equals(piove)) {
    		intensity = obj.getString("PrecipitationIntensity");
    	}
    	return intensity;
    }
	
}
