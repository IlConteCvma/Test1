package logic.view.graphic.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import logic.bean.QuestionBean;
import logic.controller.AllQuestionController;
import logic.view.AlertControl;
import logic.view.NamePage;

public class AllQuestionGraphicController extends GraphicController{
	
	@FXML VBox table;
	
	
	@FXML
	public void homeButton(){
			goToPage(NamePage.HOME);
		
	}
	
	@FXML
	public void newQuestButton(){
		goToPage(NamePage.QUESTIONTYPE);
	}
	
	@FXML
	public void myQuestButton() {
		//goToPage(NamePage.Myquest)
		AlertControl.infoBox("Sorry", "Operation not avaible");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
		AllQuestionController controller = new AllQuestionController();
		List<QuestionBean> bean = controller.getQuestions();
		table.setSpacing(5);
	
		if(bean != null) {
			for(int i = 0; i<bean.size();i++) {
				HBox quest = makeBox(bean.get(i));
				this.table.getChildren().add(quest);
			}
		}
		else {
			Label lab = new Label("No Question");
			this.table.getChildren().add(lab);
		}
		
	}
	
	private HBox makeBox(QuestionBean bean) {
		//create a line
		HBox box = new HBox(0.0);
		HBox.setMargin(box, new Insets(5));
		
		StackPane blank = new StackPane();
		blank.setMinSize(10, 54);
		
	
		StackPane pane1 = makePane(40,"  "+String.valueOf(bean.getId()).toString(),null);//34
		StackPane pane2 = makePane(210,"  "+bean.getTitle(),bean);//205,150
		StackPane pane3 = makePane(125,"  "+bean.getStudent(),null);//125
		StackPane pane4 = makePane(70,"  "+bean.getSubject(), null);//75
		StackPane pane5 = makePane(80,"  "+bean.getType().toString(),null);//120
		StackPane pane6 = makeValPane(bean.isSolved());
		
		box.getChildren().addAll(blank,pane1,pane2,pane3,pane4,pane5,pane6);
		
		return box;		
				
		}
	
	private StackPane makeValPane(boolean val) {
		//for column validate
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(7,54);
		StackPane.setAlignment(rec, Pos.CENTER_LEFT);
		rec.setStroke(Color.BLACK);
		rec.setStrokeWidth(1);
		if(val) {
			rec.setFill(Color.FORESTGREEN);
		}
		else {
			rec.setFill(Color.valueOf("f80c0c"));
		}
		pane.getChildren().add(rec);
		return pane;
	}
	
	private StackPane makePane(double width,String text,QuestionBean bean) {
		//for all Type
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(width,54);
		rec.setFill(Color.web("ffc21c", 0.47));
		rec.setStroke(Color.BLACK);
		rec.setStrokeWidth(1);
		StackPane.setAlignment(rec, Pos.CENTER_LEFT);
		pane.getChildren().add(rec);
		
		if(bean == null) {
			Label lab = new Label(text);
			pane.getChildren().add(lab);
			
			
		}
		else {
			
			Hyperlink ln = new Hyperlink(text);
			ln.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
					goToPage(NamePage.SHOWQUEST, (Object)bean);
			
			}
		

		});
			pane.getChildren().add(ln);
		}
				
		return pane ;
	}

	
}
