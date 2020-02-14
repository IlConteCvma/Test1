package logic.view;

import java.io.IOException;


public class PageFactory {
	
	private PageFactory() {
		
	}
	
	public static Page createPage(NamePage type) throws IOException{
	
		switch(type) {
			case HOME: return new HomePage();
			case LOGIN: return new LoginPage();
			case REGISTRATION: return new RegistrationPage();
			case QUESTIONTYPE: return new TypeQuestionPage();
			case ALLQUESTION: return new AllQuestionPage();
			
			
			default: throw new IOException("Invalid type : " + type);
			
		}	

	}
	
	public static Page createPage(NamePage type,String[] args) throws IOException {
		
		switch(type) {
		
			case EXERCISE: return new QuestionPage(args[0],args[1]);
			case PROBLEM: return new QuestionPage(args[0],args[1]);
			
		default: throw new IOException("Invalid type or args");
		}
		
		
		
	}
}
