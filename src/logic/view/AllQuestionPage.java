package logic.view;

import java.io.IOException;

import logic.view.graphic.elements.GraphicElementInterface;

import logic.view.graphic.elements.SimpleGraphicElement;

public class AllQuestionPage extends Page {
	
	public AllQuestionPage() throws IOException {
		createPage();
	}
	
	
	

	@Override
	protected void createPage() throws IOException {
		Navbar nav =  Navbar.getNavbar();
		GraphicElementInterface hm = new SimpleGraphicElement("../../resources/AllQuestionView.fxml");
		
		nav.controller().setForum();
		
		this.getChildren().add(nav);
		this.getChildren().add(hm.draw());
		
	}

}
