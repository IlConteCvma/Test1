package execption;

public class LessonNotFoundException extends Exception{

	private static final long serialVersionUID = 145670897651L;

	public LessonNotFoundException() {
		super("Today you aren't lesson");
	}
	
}
