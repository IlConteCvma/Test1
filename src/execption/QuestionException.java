package execption;

public class QuestionException extends Exception{
	private static final long serialVersionUID = 123457869000L;
	
	public QuestionException() {
		super();
	}
	
	public QuestionException(String message) {
		super("Problem in question operation:" + message);
		printStackTrace();
	}
	
	public QuestionException(String message,StackTraceElement[] elements) {
		super("Problem in question operation:" + message);
		this.setStackTrace(elements);
	}
	
	public QuestionException(Throwable message) {
	
		this.initCause(message);
		fillInStackTrace();
		
	}
	

}
