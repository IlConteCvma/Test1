package execption;

public class TimeException extends Exception{

	private static final long serialVersionUID = 12345678987654L;

	public TimeException() {
		super("Time is less than zero");
	}
	
}
