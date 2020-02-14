package logic.view.graphic.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import logic.bean.SeatBean;
import logic.controller.ViewTimeToExitController;
import logic.model.Lesson;
import logic.model.Room;
import logic.view.AlertControl;
import logic.view.ViewComponent;

public class AnchorPaneRoom extends Decorator{

	private Room roomLesson;
	private ViewTimeToExitController controlUC;
	
	public AnchorPaneRoom(ViewComponent anchorPaneComponent, Lesson lesson) {
		super(anchorPaneComponent);
		this.roomLesson = lesson.getRoomLesson();
	}
	
	public AnchorPane createRoom(AnchorPane anchorPane) {
		GridPane room = new GridPane();
		room.setHgap(10);
		room.setVgap(10);
		
		for (int i = 0; i < roomLesson.getNumRow(); i++) {
            RowConstraints rowConst = new RowConstraints();
            room.getRowConstraints().add(rowConst);         
        }
		
		for (int i = 0; i < roomLesson.getNumColumn(); i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            room.getColumnConstraints().add(colConst);
        }
		
        for(int i = 0; i < roomLesson.getNumRow();i++) {
			for(int j = 0; j < roomLesson.getNumColumn();j++) {
				
				int index = (roomLesson.getNumColumn()*i+j) + 1;
				Button b = new Button(""+index);
				b.setMaxWidth(Double.MAX_VALUE);
				
				colorButton(b);
				
				b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						int indexClicled = Integer.parseInt(b.getText());
						
						//popule bean seat
						SeatBean sBean = new SeatBean();
						sBean.setIndex(indexClicled);
						sBean.setRoom(roomLesson);
						controlUC = new ViewTimeToExitController();
						
						if(b.getAccessibleText() == "free") {
							controlUC.occupateSeat(sBean);
							roomLesson.getPlaces().get((indexClicled-1)).occupateSeat();
							b.setStyle("-fx-background-color: red");
							b.setAccessibleText("busy");
							AlertControl.infoBox("Seat booked", "BOOK");
						}else {
							controlUC.freeSeat(sBean);
							roomLesson.getPlaces().get((indexClicled-1)).freeSeat();
							b.setStyle("-fx-background-color: green");
							b.setAccessibleText("free");
							AlertControl.infoBox("Seat unbooked", "UNBOOK");
						}
						
					}
				});
				
				room.add(b, j, i);
	        }
        }
        
        anchorPane.getChildren().add(room);
		return anchorPane;
	}

	public void colorButton(Button b) {
		int indexClicled = Integer.parseInt(b.getText());
		if(roomLesson.getPlaces().get((indexClicled - 1)).getState()) {
			b.setStyle("-fx-background-color: red");
			b.setAccessibleText("busy");
			b.setDisable(true);
		}else {
			b.setStyle("-fx-background-color: green");
			b.setAccessibleText("free");
		}
	}
	
	@Override
	public AnchorPane draw() {
		AnchorPane preliminaryResults = super.draw();
		preliminaryResults = this.createRoom(preliminaryResults);
		return preliminaryResults;
	}
}
