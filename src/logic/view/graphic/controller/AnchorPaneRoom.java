package logic.view.graphic.controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import logic.MainClass;
import logic.Session;
import logic.bean.SeatBean;
import logic.bean.TimeToExitBean;
import logic.controller.BookSeatController;
import logic.model.Room;
import logic.view.AlertControl;
import logic.view.HomeTimePage;
import logic.view.Page;
import logic.view.ViewComponent;

public class AnchorPaneRoom extends Decorator{

	private TimeToExitBean tBean;
	private Room roomLesson;
	private BookSeatController controlSeat;
	
	public AnchorPaneRoom(ViewComponent anchorPaneComponent, TimeToExitBean tBean) {
		super(anchorPaneComponent);
		this.tBean = tBean;
		this.roomLesson = tBean.getNextLesson().getRoomLesson();
	}
	
	public AnchorPane createRoom(AnchorPane anchorPane) {
		GridPane room = new GridPane();
		room.setLayoutX(-8);
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
						int indexClicked = Integer.parseInt(b.getText());
						
						//popule bean seat
						SeatBean sBean = new SeatBean();
						sBean.setIndex(indexClicked);
						sBean.setRoom(roomLesson);
						controlSeat = new BookSeatController();
						
						if(b.getAccessibleText() == "free") {
							Session.getSession().setIndexOfSeat(indexClicked);
							occupySeat(sBean, b);
						}else {
							Session.getSession().setIndexOfSeat(0);
							freeSeat(sBean, b);
						}	
					}
				});
				
				room.add(b, j, i);
	        }
        }
        
        anchorPane.getChildren().add(room);
		return anchorPane;
	}

	public void freeSeat(SeatBean sBean, Button b) {
		try {
			controlSeat.freeSeat(sBean);
		} catch (SQLException e) {
			AlertControl.infoBox("Error connection db", "ERROR CONNECTION");
		}
		roomLesson.getPlaces().get((sBean.getIndex()-1)).freeSeat();
		b.setStyle("-fx-background-color: green");
		b.setAccessibleText("free");
		AlertControl.infoBox("You don't follow lesson? Ok, enjoy your free time!", "UNBOOK");
		refresh();
	}
	
	public void occupySeat(SeatBean sBean, Button b) {
		try {
			controlSeat.occupateSeat(sBean);
		} catch (SQLException e) {
			AlertControl.infoBox("Error connection db", "ERROR CONNECTION");
		}
		roomLesson.getPlaces().get((sBean.getIndex()-1)).occupateSeat();
		b.setStyle("-fx-background-color: red");
		b.setAccessibleText("busy");
		AlertControl.infoBox("Seat booked! You can book one only seat!!", "BOOK");
		refresh();
	}
	
	public void colorButton(Button b) {
		int index = Integer.parseInt(b.getText());
		if(roomLesson.getPlaces().get((index - 1)).getState()) {
			b.setStyle("-fx-background-color: red");
			b.setAccessibleText("busy");
			b.setDisable(true);
			if(Session.getSession().getIndexOfSeat() != 0 && index == Session.getSession().getIndexOfSeat() ) {
				b.setDisable(false);
			}
		}else {
			b.setStyle("-fx-background-color: green");
			b.setAccessibleText("free");
			if(Session.getSession().getIndexOfSeat() != 0) {
				b.setDisable(true);
			}
		}
	}
	
	private void refresh() {
		Page root = null;
		try {
			root = new HomeTimePage(tBean);
		} catch (IOException e) {
			AlertControl.infoBox("Internal error", "Error");
		}
		Scene scene = new Scene(root);
		MainClass.getStage().setScene(scene);
	}
	
	@Override
	public AnchorPane draw() {
		AnchorPane preliminaryResults = super.draw();
		preliminaryResults = this.createRoom(preliminaryResults);
		return preliminaryResults;
	}
}
