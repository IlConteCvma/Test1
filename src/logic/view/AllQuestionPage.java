package logic.view;

import java.io.IOException;

import logic.view.graphic.elements.GraphicElementInterface;
import logic.view.graphic.elements.NavbarElement;
import logic.view.graphic.elements.SimpleGraphicElement;

public class AllQuestionPage extends Page {
	
	public AllQuestionPage() throws IOException {
		createPage();
	}
	
	
	

	@Override
	protected void createPage() throws IOException {
		
		GraphicElementInterface nav = new NavbarElement();
		GraphicElementInterface hm = new SimpleGraphicElement("../../resources/AllQuestionView.fxml");
		
		
		this.getChildren().add(nav.draw());
		this.getChildren().add(hm.draw());
	}

}
