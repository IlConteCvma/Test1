package logic.view.graphicController;

import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import logic.MainClass;
import logic.controller.LoginController;
import logic.view.Page;
import logic.view.PageFactory;

import java.io.IOException;

import javafx.event.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginGraphicController{
	
	@FXML private PasswordField psw;
	@FXML private TextField user;
	@FXML private AnchorPane rootPane;
	
	LoginController lg = new LoginController();
	
	@FXML
	public void signIn(ActionEvent e) throws IOException {
		/*if(lg.login(user.getText(),psw.getText())) {
			goToHomepage();
		}*/
		goToHomepage();
	}
	
	public void goToHomepage() throws IOException {
		/*
		Parent root = FXMLLoader.load(getClass().getResource("resources/HomeView.fxml"));
		*/
		Page root = PageFactory.createPage("homePage");
		Scene scene = new Scene(root);
		MainClass.getStage().setScene(scene);
	}
	
	public void forgotPassword(ActionEvent e) {
		System.err.println("Remember");
	}
	
	public void signUp(ActionEvent e) {
		System.err.println("Sign Up");
	}

}
