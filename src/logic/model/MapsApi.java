package logic.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

public class MapsApi{

	private static RequestHttpApi connection = new RequestHttpApi();
	private static String apikey = "AIzaSyBxqvZv-6yD5NY-JGuO8kuSqdxHNYj3Fs0";
    
    public List<Double> getPosition(String place) throws IOException{
    	String urlRequest = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="+place+"&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=" + apikey;
    	String response = connection.sendRequest(urlRequest);
    	JSONObject positionObj = new JSONObject(response);
    	JSONArray arr = positionObj.getJSONArray("candidates");
    	JSONObject postId = arr.getJSONObject(0).getJSONObject("geometry").getJSONObject("viewport").getJSONObject("southwest");
    	Double lat = (Double)postId.get("lat");
    	Double lng = (Double)postId.get("lng");
    	List<Double> position = new ArrayList<>();
    	position.add(lat);
    	position.add(lng);
		return position;
    	
    }
    
    public Double calculateDistance(List<Double> origin, List<Double> destination) {
    	
    	double r = 6371e3 / 1000; // km
    	double d1 = origin.get(0) * 3.14 / 180;
    	double d2 = destination.get(0) * 3.14 / 180;
    	double d3 = (destination.get(0)-origin.get(0)) * 3.14 / 180;
    	double d4 = (destination.get(1)- origin.get(1)) * 3.14 / 180;

    	double a = Math.sin(d3/2) * Math.sin(d3/2) +
    	        Math.cos(d1) * Math.cos(d2) *
    	        Math.sin(d4/2) * Math.sin(d4/2);
    	
    	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    	return r * c;
    	
    }
}
