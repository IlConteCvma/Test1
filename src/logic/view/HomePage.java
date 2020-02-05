package logic.view;

import java.io.IOException;


import logic.view.graphicElements.GraphicElementInterface;
import logic.view.graphicElements.SimpleGraphicElement;



public class HomePage extends Page{
	public HomePage() throws IOException {
		createPage();		
	}

	@Override
	public void createPage() throws IOException {
		GraphicElementInterface nav = new SimpleGraphicElement("../resources/NavbarView.fxml");
		GraphicElementInterface hpe = new SimpleGraphicElement("../resources/HomeView.fxml");
		
		this.getChildren().add(nav.draw());
		this.getChildren().add(hpe.draw());
		
	}
}
