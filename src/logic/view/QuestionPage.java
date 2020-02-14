package logic.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.view.graphic.controller.GraphicController;
import logic.view.graphic.controller.QuestionExerciseGraphicController;
import logic.view.graphic.controller.QuestionProblemGraphicController;
import logic.view.graphic.elements.GraphicElementInterface;
import logic.view.graphic.elements.NavbarElement;
import logic.view.graphic.elements.NoControllerGrapichElement;

public class QuestionPage extends Page {
	
	public QuestionPage(String param,String destination) throws IOException{
		createPage(param,destination);
	}
	
	private void createPage(String param,String destination) throws IOException {
		GraphicElementInterface nav = new NavbarElement();
		NoControllerGrapichElement qPage = new NoControllerGrapichElement(destination);
		GraphicController controller;
		if(destination.equals("../../resources/QuestionProblemView.fxml") ) {
			controller = new QuestionProblemGraphicController(param);
		}else {
			controller = new QuestionExerciseGraphicController(param);
		}
		
		
		
		FXMLLoader load =qPage.draw();
		load.setController(controller);
		AnchorPane pane = load.load();

		this.getChildren().add(nav.draw());
		this.getChildren().add(pane);
		
		
	}

	@Override
	protected void createPage() throws IOException {
		//nothing to do overriding Page method

	}

}
