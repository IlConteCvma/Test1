package logic.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import logic.bean.TimeToExitBean;
import logic.view.graphic.controller.GraphicController;
import logic.view.graphic.controller.TimeToExitGraphicController;
import logic.view.graphic.elements.GraphicElementInterface;
import logic.view.graphic.elements.NoControllerGrapichElement;
import logic.view.graphic.elements.SimpleGraphicElement;

public class HomeTimePage extends Page {

	public HomeTimePage(TimeToExitBean tBean) throws IOException {
		createPage(tBean);		
	}
	
	public void createPage(TimeToExitBean tBean) throws IOException {
		
		Navbar nav = Navbar.getNavbar();
		nav.controller().setHome();
		
		GraphicElementInterface home = new SimpleGraphicElement("../../resources/HomeMenuView.fxml");
		NoControllerGrapichElement qPage = new NoControllerGrapichElement("../../resources/TimeToExitView.fxml");
		GraphicController controller = new TimeToExitGraphicController(tBean);
		FXMLLoader load =qPage.draw();
		load.setController(controller);
		AnchorPane pane = load.load();
		
		this.getChildren().add(nav);
		HBox hbox = new HBox(home.draw(),pane);
			
		this.getChildren().add(hbox);
	}

	@Override
	public void createPage() throws IOException {
		//nothing to do ovverriding Page		
	}

}
