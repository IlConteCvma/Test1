package logic.view.graphic.elements;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class NavbarElement implements GraphicElementInterface {
	
	@Override
	public AnchorPane draw() throws IOException {
		
		return FXMLLoader.<AnchorPane>load(getClass().getResource("../../resources/NavbarView.fxml"));
	}

}
