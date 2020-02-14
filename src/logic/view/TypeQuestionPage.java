package logic.view;

import java.io.IOException;

import logic.view.graphic.elements.GraphicElementInterface;
import logic.view.graphic.elements.NavbarElement;
import logic.view.graphic.elements.SimpleGraphicElement;

public class TypeQuestionPage extends Page {
	
	public TypeQuestionPage() throws IOException {
		createPage();
	}

	@Override
	protected void createPage() throws IOException {
		GraphicElementInterface nav = new NavbarElement();
		GraphicElementInterface typepage = new SimpleGraphicElement("../../resources/TypeQuestionView.fxml");
		
		
		this.getChildren().add(nav.draw());
		this.getChildren().add(typepage.draw());
		
	}

}
