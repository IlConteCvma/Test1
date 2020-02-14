package logic.view.graphic.controller;

import java.io.IOException;

import javafx.scene.Scene;
import logic.MainClass;
import logic.view.Page;
import logic.view.PageFactory;
import logic.view.NamePage;

public abstract class GraphicController {
	
	protected void goToPage(NamePage page) throws IOException {
		Page root = PageFactory.createPage(page);
		Scene scene = new Scene(root);
		MainClass.getStage().setScene(scene);
	}
	
	protected void goToPage(NamePage page,String[] args) throws IOException {
		Page root = PageFactory.createPage(page, args);
		Scene scene = new Scene(root);
		MainClass.getStage().setScene(scene);
	}
}
