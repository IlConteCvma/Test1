package logic.view.graphic.controller;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import logic.bean.StudentBean;
import logic.controller.LoginController;
import logic.view.AlertControl;
import logic.view.NamePage;
import logic.view.Navbar;
import logic.view.graphic.elements.PhraseSelector;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import execption.AssistantException;
import execption.EntityNotFoundException;
import javafx.event.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginGraphicController extends GraphicController{
	
	@FXML private PasswordField psw;
	@FXML private TextField user;
	@FXML private AnchorPane rootPane;
	@FXML private Label label;
	
	private static final String WARNING = "COMING SOON";
	private static final String ERROR = "ERROR TO LOG";
	LoginController lg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lg = new LoginController();
		
		try {
			this.label.setText(PhraseSelector.select("src/logic/view/resources/LoginPhrase.txt"));
		} catch (AssistantException e) {
			//default message
			this.label.setText("Welcome");
		}
		
		
	}
	
	@FXML
	public void signIn(ActionEvent e){
		
		StudentBean possibleStudent = new StudentBean();
		possibleStudent.setUsername(user.getText());
		possibleStudent.setPassword(psw.getText());
		try {
			lg.login(possibleStudent);
			Navbar.getNavbar().controller().setMyStudent();
			goToPage(NamePage.HOME);
		} catch (SQLException e1) {
			AlertControl.infoBox("Ops.. connection failed", ERROR);
		} catch (EntityNotFoundException e1) {
			AlertControl.infoBox("Ops.. user not found", ERROR);
		}
		
	}
	
	
	public void forgotPassword() {
		AlertControl.infoBox("Operation not available", WARNING);
	}
	
	public void signUp() {
		goToPage(NamePage.REGISTRATION);
		
	}


	

}
