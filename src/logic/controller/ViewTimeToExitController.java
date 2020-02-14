package logic.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import execption.LessonNotFoundException;
import execption.TimeException;
import javafx.scene.Scene;
import logic.MainClass;
import logic.Session;
import logic.bean.SeatBean;
import logic.bean.TimeToExitBean;
import logic.model.Journey;
import logic.model.Lesson;
import logic.model.MapsApi;
import logic.model.TimeApi;
import logic.model.WeatherApi;
import logic.model.dao.SeatDao;
import logic.view.HomeTimePage;
import logic.view.Page;
import logic.view.graphic.controller.TimeToExitGraphicController;

public class ViewTimeToExitController {
	
	//const value
	private static final int WEIGHT = 50;
	private static final int MINUTEOFADVANCE = 15;
	private static final double PERCENTDISTANCEADD = 0.14;
	
	//association attribute
	private ViewNextLessonController nextLessonController = new ViewNextLessonController();
	
	private Journey nextJourney;
	private TimeToExitBean timeToExitBean;
	
	public void getInfoByMaps(){
		MapsApi map = new MapsApi();
		//Calculate latitude and longitude 
		try {
			nextJourney = new Journey(map.getPosition(Session.getSession().getStudent().getAddress().getFullAddress()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Calculate distance in km
		nextJourney.setDistance(map.calculateDistance(nextJourney.getOriginAddress(),nextJourney.getDestinationAddress()));
	}
	
	public void getInfoByWeather(){
		WeatherApi weather = new WeatherApi();
		String rainIntensity = null;
		try {
			rainIntensity = weather.getRainIntensity();
		} catch (IOException e) {
				e.printStackTrace();
		}
		if(rainIntensity.equals("Light")) {
			nextJourney.setLateForWeather(5);
		}else if(rainIntensity.equals("Moderate")){
			nextJourney.setLateForWeather(10);
		}
	}
	
	public void estimateTimeToExit() throws IOException, TimeException, LessonNotFoundException{
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
				new TimeToExitGraphicController(timeToExitBean);
				
				Page root = new HomeTimePage(timeToExitBean);
				Scene scene = new Scene(root);
				MainClass.getStage().setScene(scene);
			}
		}else {
			throw new LessonNotFoundException();
		}
	}
	
	public double calculateTimeBasedOccupationRoom(int priority) {
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
	
	public void occupateSeat(SeatBean seat) {
		SeatDao seatDao = new SeatDao();
		try {
			seatDao.occupySeat(seat.getRoom().getName(), seat.getIndex());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void freeSeat(SeatBean seat) {
		SeatDao seatDao = new SeatDao();
		try {
			seatDao.freeSeat(seat.getRoom().getName(), seat.getIndex());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}