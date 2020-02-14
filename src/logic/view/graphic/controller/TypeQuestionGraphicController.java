package logic.view.graphic.controller;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import logic.bean.SubjectBean;
import logic.controller.InsertQuestionController;
import logic.view.NamePage;






public class TypeQuestionGraphicController extends GraphicController implements Initializable{
	
	
	@FXML Button buttonExe;
	@FXML Button buttonPro;
	@FXML GridPane gridPane;
	private String subSelect;
	
	private InsertQuestionController controller;
	
	public TypeQuestionGraphicController() {
		this.controller = new InsertQuestionController();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mySubject();
		
	}
	
	
	
	
	
	public void homeButton() throws IOException {
		goToPage(NamePage.HOME);
	}
	

	public void typeExercise() throws IOException {
		
		String[] args = new String[2];
		args[0]= this.subSelect;
		args[1]= "../../resources/QuestionExerciseView.fxml";
		
		goToPage(NamePage.EXERCISE, args);
		
		
	}
	public void back() throws IOException {
		goToPage(NamePage.ALLQUESTION);
	}
	
	public void typeProblem() throws IOException {

		String[] args = new String[2];
		args[0]=this.subSelect;
		args[1]="../../resources/QuestionProblemView.fxml";
		
		goToPage(NamePage.PROBLEM, args);
	}
	

	private void mySubject() {
		 
		List<SubjectBean> bean = this.controller.getSubjects();
		gridPane.setVgap(25);
		gridPane.setHgap(1);
		
		if(bean != null) {
			int row = 0;
			int col = 0;
			for(int i=0; i<bean.size();i++) {
				
				if(i%4 == 0 && i !=0) {
					row++;
				}
				if (col%4 == 0) {
					col = 0;
				}
				
				showSubject(bean.get(i).getName(),bean.get(i).getAbbrevation(),row,col);
				col++;
			}
			
		}
		
		else {
			Label text = new Label();
			text.setMinSize(110, 30);
			text.setText("You don't follow any \nsubject\nCheck your profile");
			text.setTextAlignment(TextAlignment.CENTER);
		
			
			this.gridPane.getChildren().add(text);
		}
		
	}
	
	
	private void showSubject(String name,String abbr,int row,int col) {
		Button subj = new Button();
		subj.setText(abbr);
		subj.setAccessibleText(name);
		subj.setMinSize(125, 40);
		
		
		subj.setTextFill(Color.WHITE);
		subj.setStyle("-fx-background-color:  #272F54;");
		
		
		
		
		
		subj.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				 List<Node> list = gridPane.getChildren();
				 for(int i = 0; i<list.size();i++) {
					 
					 list.get(i).setDisable(false);
					 list.get(i).setStyle("-fx-background-color:  #272F54;");
				 }
				 
				
				
				subj.setStyle("-fx-background-color: #0bbd3d;");
				subj.setDisable(true);
				subSelect = subj.getAccessibleText();
				
				buttonPro.setDisable(false);
				buttonExe.setDisable(false);
				
			}
		

		});
		
		gridPane.add(subj, col, row);
		
	}





	
	



}
