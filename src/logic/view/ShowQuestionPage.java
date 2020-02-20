package logic.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.view.graphic.controller.GraphicController;
import logic.view.graphic.controller.ShowQuestionGraphicController;
import logic.view.graphic.elements.NoControllerGrapichElement;

public class ShowQuestionPage extends Page {
	
	public ShowQuestionPage(Object arg) throws IOException {
		createPage(arg);
	}

	
	protected void createPage(Object arg) throws IOException {
		Navbar nav = Navbar.getNavbar();
		nav.controller().setForum();
		NoControllerGrapichElement qPage = new NoControllerGrapichElement("../../resources/ShowQuestionView.fxml");
		GraphicController controller;
		controller = new ShowQuestionGraphicController(arg);

		FXMLLoader load =qPage.draw();
		load.setController(controller);
		AnchorPane pane = load.load();
		this.getChildren().add(nav);
		this.getChildren().add(pane);
	}
	@Override
	protected void createPage(){
		//nothing to do inherited method
	}

}
