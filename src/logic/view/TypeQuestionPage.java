package logic.view;

import java.io.IOException;

import logic.view.graphic.elements.GraphicElementInterface;

import logic.view.graphic.elements.SimpleGraphicElement;

public class TypeQuestionPage extends Page {
	
	public TypeQuestionPage() throws IOException {
		createPage();
	}

	@Override
	protected void createPage() throws IOException {
		
		Navbar nav = Navbar.getNavbar();
		nav.controller().setForum();
		
		GraphicElementInterface typepage = new SimpleGraphicElement("../../resources/TypeQuestionView.fxml");
		
		this.getChildren().add(nav);
		this.getChildren().add(typepage.draw());
		
	}

}
