package logic.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import execption.EntityNotFoundException;
import execption.TimeException;
import logic.Session;
import logic.bean.TimeToExitBean;
import logic.model.Journey;
import logic.model.Lesson;
import logic.model.MapsApi;
import logic.model.TimeApi;
import logic.model.WeatherApi;

public class ViewTimeToExitController {
	
	//const value
	private static final int WEIGHT = 50;
	private static final int MINUTEOFADVANCE = 15;
	private static final double PERCENTDISTANCEADD = 0.14;
	
	
	
	private Journey nextJourney;
	private TimeToExitBean timeToExitBean;
	
	private void getInfoByMaps() throws JSONException, IOException{
		MapsApi map = new MapsApi();
		//Calculate latitude and longitude 
		nextJourney = new Journey(map.getPosition(Session.getSession().getStudent().getAddress().getFullAddress()));
		//Calculate distance in km
		nextJourney.setDistance(map.calculateDistance(nextJourney.getOriginAddress(),nextJourney.getDestinationAddress()));	
	}
	
	private void getInfoByWeather() throws JSONException, IOException{
		WeatherApi weather = new WeatherApi();
		String rainIntensity = null;
		rainIntensity = weather.getRainIntensity();
		
		if(rainIntensity.equals("Light")) {
			nextJourney.setLateForWeather(5);
		}else if(rainIntensity.equals("Moderate")){
			nextJourney.setLateForWeather(10);
		}
	}
	
	public TimeToExitBean estimateTimeToExit() throws IOException, TimeException, EntityNotFoundException, SQLException{
		ViewNextLessonController nextLessonController = new ViewNextLessonController();
		Lesson nextLesson = nextLessonController.getNextLesson();
		if(nextLesson != null) {
			TimeApi time = new TimeApi();
			timeToExitBean = new TimeToExitBean();
			timeToExitBean.setNextLesson(nextLesson);
			double speedAverage = Session.getSession().getStudent().getVehicle().getSpeed();
			getInfoByMaps();
			getInfoByWeather();
			nextJourney.setDistance(nextJourney.getDistance() + PERCENTDISTANCEADD * nextJourney.getDistance()); //add 14% -> value take by test
			double minutes = (int) (nextJourney.getDistance() / (speedAverage*0.016)) + nextJourney.getDistance() + MINUTEOFADVANCE;
			long timeExit = time.getTimeMinuteDiff(nextLesson.getStartHour().toString(), time.getStringHour(time.getCurrentDate()));
			int i = 0;
			double timeToExit = 0;
			do {
				timeToExit = (int) (timeExit - (minutes + calculateTimeBasedOccupationRoom(i)));
				i++;
			}while(timeToExit<0 && i < 3);
			timeToExitBean.setPriority(i);
			if(timeToExit < 0) {
				throw new TimeException();
			}else {
				timeToExitBean.setHourToExit(time.timeAdd(timeToExit));
				timeToExitBean.setNextJourney(nextJourney);
				timeToExitBean.setNextLesson(nextLesson);
				return timeToExitBean;
			}
		}else {
			throw new EntityNotFoundException("Lesson");
		}
	}
	
	private double calculateTimeBasedOccupationRoom(int priority) {
		int freePlaces = timeToExitBean.getNextLesson().getRoomLesson().getNumberOfFreePlacesForPriority(priority);
		double minute = 0;
		if(freePlaces != 0) {
			List<Integer> range = timeToExitBean.getNextLesson().getRoomLesson().getSeatOfPriority(priority);
			int allPlaces = range.get(1) - range.get(0);
			int busyPlaces = allPlaces - freePlaces;		
			minute = WEIGHT * timeToExitBean.getNextLesson().getSubjectLesson().getIndexOfStudents() * ((double)busyPlaces/allPlaces);
		}else {
			minute = Double.MAX_VALUE;
		}
		return minute;
	}
	
}