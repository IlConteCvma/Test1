package logic.view;

import java.io.IOException;
import javafx.scene.layout.HBox;
import logic.view.graphic.elements.GraphicElementInterface;

import logic.view.graphic.elements.SimpleGraphicElement;



public class HomePage extends Page{
	public HomePage() throws IOException {
		createPage();		
	}

	@Override
	protected void createPage() throws IOException {
		
		Navbar nav = Navbar.getNavbar();
		
		GraphicElementInterface hm = new SimpleGraphicElement("../../resources/HomeMenuView.fxml");
		GraphicElementInterface hb = new SimpleGraphicElement("../../resources/HomeBoxView.fxml");
		nav.controller().setHome();
		
		HBox hbox = new HBox(hm.draw(),hb.draw());
		hbox.setSpacing(0);
		this.getChildren().add(nav);
		this.getChildren().add(hbox);
		
	}
}
