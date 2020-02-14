package logic.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DateApi{

	public List<Integer> getActualHour() {
		GregorianCalendar dataAttuale=new GregorianCalendar();
		int ore = dataAttuale.get(Calendar.HOUR);
		int minuti = dataAttuale.get(Calendar.MINUTE);
		int secondi = dataAttuale.get(Calendar.SECOND);
		List<Integer> actualHour = new ArrayList<>();
		actualHour.add(ore);
		actualHour.add(minuti);
		actualHour.add(secondi);
		return actualHour;
	}
	
}
