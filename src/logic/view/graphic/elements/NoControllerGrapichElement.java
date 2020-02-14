package logic.view.graphic.elements;

import java.io.IOException;

import javafx.fxml.FXMLLoader;


public class NoControllerGrapichElement {
	private String myElement;
	
	public NoControllerGrapichElement(String element) {
		this.myElement = element;
	}
	
	
	public FXMLLoader draw() throws IOException {
		
		return new FXMLLoader(getClass().getResource(myElement));
	}
}
