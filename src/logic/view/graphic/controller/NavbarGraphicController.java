package logic.view.graphic.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import logic.Session;
import logic.model.Student;
import logic.view.AlertControl;
import logic.view.NamePage;


public class NavbarGraphicController extends GraphicController{

	@FXML Button homeButton;
	@FXML Button calButton;
	@FXML Button profButton;
	@FXML MenuButton forum;
	@FXML Label nameLab;
	
	private static final  String CLICKOFF = "-fx-background-color :  #FBF9F9;"
			+ "-fx-border-color:  #626262;"
			+ "-fx-text-fill:  #000000";
	private static final  String CLICKON =  "-fx-background-color :  #DEDEDE;"
			+ "-fx-border-color:  #626262;"
			+ "-fx-text-fill:  #000000";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setStyle(CLICKON);
		
	}
	
	@FXML
	public void homeButton(ActionEvent e){
			goToPage(NamePage.HOME);
			clearButton();
		
			homeButton.setStyle(CLICKON);
	}	
	
	public void calendarButton() {
		AlertControl.infoBox("Operation not available","COMING SOON");
	}
	
	public void allQuestion() {
		goToPage(NamePage.ALLQUESTION);
		
	}
	public void newQuestion() {
		goToPage(NamePage.QUESTIONTYPE);
		
	}
	
	public void profileButton() {
		AlertControl.infoBox("Operation not available", "COMING SOON");
	}
	public void logOutButton() throws IOException {
		if(AlertControl.confirmation()) {
			
			goToPage(NamePage.LOGIN);
		}
	}
	
	private void clearButton() {
		
		homeButton.setStyle(CLICKOFF);
		profButton.setStyle(CLICKOFF);
		calButton.setStyle(CLICKOFF);
		forum.setStyle(CLICKOFF);
		
	}
	
	public void setHome() {
		clearButton();
		homeButton.setStyle(CLICKON);
	}
	
	public void setForum() {
		clearButton();
		forum.setStyle(CLICKON);
	}
	
	public void setProfile() {
		clearButton();
		profButton.setStyle(CLICKON);
	}
	
	public void setCalendar() {
		clearButton();
		calButton.setStyle(CLICKON);
	}
	
	public void setMyStudent(){
		Student app = Session.getSession().getStudent();
		
		String visual = app.getName()+" "+app.getSurname()+" ("+app.getUsername()+")";
		nameLab.setText(visual);
	}
			
}