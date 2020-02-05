package logic.view;

import java.io.IOException;

public class PageFactory {
	
	private PageFactory() {
		
	}
	
	public static Page createPage(String type) throws IOException{
	
		switch(type) {
			case "homePage": return new HomePage();
			case "logPage": return new LoginPage();
			
			default: throw new IOException("Invalid type : " + type);
			
		}		
	}
}
