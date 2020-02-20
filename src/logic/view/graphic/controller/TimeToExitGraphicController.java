package logic.view.graphic.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import logic.bean.TimeToExitBean;
import logic.view.graphic.elements.AnchorPaneComponent;

public class TimeToExitGraphicController extends GraphicController{

	private TimeToExitBean timeToExitBean;
	private AnchorPaneComponent anchorPane;
	
	@FXML private Button occupateSeat;
	@FXML private Button occupateSeat1;
	@FXML private AnchorPane anchorPaneContainer;
	@FXML private Label nextLessonLabel;
	@FXML private Label nextRoomLabel;
	@FXML private Label minuteToExitLabel;
	@FXML private Label prioritySeatLabel;
	
	public TimeToExitGraphicController(TimeToExitBean timeToExitBean) {
		this.timeToExitBean = timeToExitBean;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nextLessonLabel.setText(timeToExitBean.getNextLesson().getSubjectLesson().getAbbrevation());
		nextRoomLabel.setText("ROOM "+timeToExitBean.getNextLesson().getRoomLesson().getName());
		minuteToExitLabel.setText(""+ timeToExitBean.getHourToExit());
		prioritySeatLabel.setText("FOR SEATS IN THE BAND " + timeToExitBean.getPriority());
	}
	
	@FXML
	public void goToMap() {
		occupateSeat1.setDisable(true);
		occupateSeat.setDisable(false);
		if(anchorPane == null) {
			anchorPane = new AnchorPaneComponent(10,122);
		}else {
			anchorPane.draw().getChildren().remove(0);
			anchorPaneContainer.getChildren().remove(2);
		}
		AnchorPaneMap anchorPaneMap = new AnchorPaneMap(anchorPane);
		anchorPaneContainer.getChildren().add(anchorPaneMap.draw());
	}
	
	@FXML
	public void goToOccupateSeat() {
		occupateSeat.setDisable(true);
		occupateSeat1.setDisable(false);
		if(anchorPane == null) {
			anchorPane = new AnchorPaneComponent(10,122);
		}else {
			anchorPane.draw().getChildren().remove(0);
			anchorPaneContainer.getChildren().remove(2);
		}
		AnchorPaneRoom anchorPaneRoom = new AnchorPaneRoom(anchorPane, timeToExitBean);
		anchorPaneContainer.getChildren().add(anchorPaneRoom.draw());
	}

}