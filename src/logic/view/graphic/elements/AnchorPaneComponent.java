package logic.view.graphic.elements;

import javafx.scene.layout.AnchorPane;
import logic.view.ViewComponent;

public class AnchorPaneComponent implements ViewComponent{
	
	private AnchorPane ap = new AnchorPane();
	
	public AnchorPaneComponent(double layoutX, double layoutY) {
		ap.setLayoutX(layoutX);
		ap.setLayoutY(layoutY);
	}
	
	@Override
	public AnchorPane draw() {
		return (this.ap);
	}
	
	
}
