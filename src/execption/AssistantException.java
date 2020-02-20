package execption;

public class AssistantException extends Exception{
	
	private static final long serialVersionUID = 145670897621151L;

	public AssistantException() {
		super("Assistant Operation error");
	}
	
	public AssistantException(String message) {
		super("Assistant Operation error\n"+message);
	}
	
}
