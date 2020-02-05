package logic.view;

import java.io.IOException;

import logic.view.graphicElements.GraphicElementInterface;
import logic.view.graphicElements.SimpleGraphicElement;

public class LoginPage extends Page {
	public LoginPage() throws IOException {
		createPage();
	}

	@Override
	public void createPage() throws IOException {
		GraphicElementInterface lpe = new SimpleGraphicElement("../resources/LoginView.fxml");
		this.getChildren().add(lpe.draw());
	}
}
