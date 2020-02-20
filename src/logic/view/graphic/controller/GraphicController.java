package logic.view.graphic.controller;

import java.io.IOException;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import logic.MainClass;
import logic.view.Page;
import logic.view.PageFactory;
import logic.view.AlertControl;
import logic.view.NamePage;

public abstract class GraphicController implements Initializable {
	
	protected static final String PAGEERROR = "Some internal error for change page";
	protected static final String ERROR = "ERROR";
	
	protected void goToPage(NamePage page) {
		Page root;
		try {
			root = PageFactory.createPage(page);
			setSceneWhitPage(root);
		} catch (IOException e) {
			AlertControl.infoBox(PAGEERROR, ERROR);
		}
		
	}
	
	protected void goToPage(NamePage page,String[] args){
		Page root;
		try {
			root = PageFactory.createPage(page, args);
			setSceneWhitPage(root);
		} catch (IOException e) {
			AlertControl.infoBox(PAGEERROR, ERROR);
		}
		
	}
	
	protected void goToPage(NamePage page,Object args){
		Page root;
		try {
			root = PageFactory.createPage(page, args);
			setSceneWhitPage(root);
		} catch (IOException e) {
			AlertControl.infoBox(PAGEERROR, ERROR);
		}
		
	}
	
	private void setSceneWhitPage(Page root) {
		Scene scene = new Scene(root);
		MainClass.getStage().setScene(scene);
	}
	
}
