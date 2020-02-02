package logic.view;



import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.controller.LoginController;
import javafx.event.*;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class LoginGraphicController{
	
	@FXML private PasswordField psw;
	@FXML private TextField user;
	
	LoginController lg = new LoginController();
	
	@FXML
	public void signIn(ActionEvent e) {
		if(lg.findStudent(user.getText(),psw.getText()) == true) {
			
		}
			
	}
	
	public void forgotPassword(ActionEvent e) {
		System.out.println("Remember");
	}
	
	public void signUp(ActionEvent e) {
		System.out.println("Sign Up");
	}

}
