package logic.view;

import java.io.IOException;

import logic.view.graphic.elements.GraphicElementInterface;
import logic.view.graphic.elements.SimpleGraphicElement;

public class RegistrationPage extends Page{

	public RegistrationPage() throws IOException {
		createPage();
	}

	@Override
	protected void createPage() throws IOException {
		GraphicElementInterface lpe = new SimpleGraphicElement("../../resources/RegistrationView.fxml");
		this.getChildren().add(lpe.draw());
	}	
	
}
