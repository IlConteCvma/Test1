package logic.view.graphic.controller;

import javafx.scene.layout.AnchorPane;
import logic.view.ViewComponent;

public abstract class Decorator implements ViewComponent {

	private ViewComponent anchorPaneComponent;
	
	public Decorator(ViewComponent anchorPaneComponent){
		this.anchorPaneComponent = anchorPaneComponent;
	}
	
	@Override
	public AnchorPane draw() {
		return this.anchorPaneComponent.draw();
	}

}