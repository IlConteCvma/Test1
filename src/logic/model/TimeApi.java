package logic.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class TimeApi {
	
	public Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}
	
	public String timeAdd(double minutes) {
		String actualHour = getStringHour(getCurrentDate());
		int actualHourTime = Integer.parseInt(""+actualHour.charAt(0)+actualHour.charAt(1));
		int actualMinuteTime = Integer.parseInt(""+actualHour.charAt(3)+actualHour.charAt(4));
		
		int hourAdd = 0;
		int minutesAdd = (int)minutes;
		if(minutes>=60) {
			hourAdd = (int) (minutes/60);
			minutesAdd = (int) (minutes - hourAdd * 60);
		}
		
		int hourToExit = actualHourTime  + hourAdd;
		int minuteToExit = actualMinuteTime /*+ minutes*/ + minutesAdd;
		hourAdd = 0;
		if(minuteToExit>=60) {
			hourAdd = minuteToExit/60;
			minuteToExit = minuteToExit - hourAdd * 60;
		}
		hourToExit = hourToExit + hourAdd;
		String hourToExitString ="";
		if(minuteToExit < 10) {
			hourToExitString=""+hourToExit+":0"+minuteToExit;
		}else {
			hourToExitString=""+hourToExit+":"+minuteToExit;
		}
		return hourToExitString;
	}
	
	public String getStringHour(Date date) {
		String dateString = date.toString();
		return ""+dateString.charAt(11)+dateString.charAt(12)+dateString.charAt(13)+dateString.charAt(14)+dateString.charAt(15)+dateString.charAt(16)+dateString.charAt(17)+dateString.charAt(18);
		 
	}
	
	public long getTimeMinuteDiff(String time1, String time2) {
		    LocalTime t1 = LocalTime.parse(time1);
		    LocalTime t2 = LocalTime.parse(time2);
		    Duration diff = Duration.between(t2, t1);
		    return diff.toMinutes();
	}
	
}
