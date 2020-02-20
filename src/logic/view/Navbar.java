package logic.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import logic.view.graphic.controller.NavbarGraphicController;


public class Navbar extends Page{
	private static Navbar instance = null;
	private NavbarGraphicController controller;
	
	private Navbar() {
		controller  = new NavbarGraphicController();
		try {
			createPage();
		} catch (IOException e) {
			AlertControl.infoBox("Internal error on create navbar","ERROR");
		}
	}
	
	public static synchronized Navbar getNavbar() {
		if (Navbar.instance == null) {
			Navbar.instance = new Navbar();
			}
		return instance;
	}
	

	
	public NavbarGraphicController controller() {
		return controller;	
	}
	
	protected void createPage() throws IOException {
	
		FXMLLoader load = new FXMLLoader(getClass().getResource("resources/NavbarView.fxml"));	
		load.setController(controller);
		AnchorPane pane = load.load();
		this.getChildren().add(pane) ;
		
	}
}
