package logic.view;

import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import logic.controller.LoginController;
import javafx.event.*;
import javafx.scene.control.TextField;

public class LoginGraphicController{
	
	@FXML private PasswordField psw;
	@FXML private TextField user;
	
	LoginController lg = new LoginController();
	
	@FXML
	public void signIn(ActionEvent e) throws Exception {
		lg.login(user.getText(),psw.getText());
	}
	
	public void forgotPassword(ActionEvent e) {
		System.err.println("Remember");
	}
	
	public void signUp(ActionEvent e) {
		System.err.println("Sign Up");
	}

}
