package logic.view;

import java.io.IOException;

import logic.view.graphic.elements.GraphicElementInterface;
import logic.view.graphic.elements.SimpleGraphicElement;

public class LoginPage extends Page {
	public LoginPage() throws IOException {
		createPage();
	}

	@Override
	protected void createPage() throws IOException {
		GraphicElementInterface lpe = new SimpleGraphicElement("../../resources/LoginView.fxml");
		this.getChildren().add(lpe.draw());
	}
}
