package logic.view;

import java.io.IOException;
import javafx.scene.layout.HBox;
import logic.view.graphic.elements.GraphicElementInterface;
import logic.view.graphic.elements.NavbarElement;
import logic.view.graphic.elements.SimpleGraphicElement;



public class HomePage extends Page{
	public HomePage() throws IOException {
		createPage();		
	}

	@Override
	protected void createPage() throws IOException {
		GraphicElementInterface nav = new NavbarElement();
		GraphicElementInterface hm = new SimpleGraphicElement("../../resources/HomeMenuView.fxml");
		GraphicElementInterface hb = new SimpleGraphicElement("../../resources/HomeBoxView.fxml");

		HBox hbox = new HBox(hm.draw(),hb.draw());
		hbox.setSpacing(0);
		
		this.getChildren().add(nav.draw());
		this.getChildren().add(hbox);
		
	}
}
