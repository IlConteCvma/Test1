package logic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import logic.view.NamePage;
import logic.view.Page;
import logic.view.PageFactory;


public class MainClass extends Application{
	
	private static Stage stageRoot;
	private static Page root;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		setStage(stage);
		setRoot(PageFactory.createPage(NamePage.LOGIN));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:view/resources/img/logo.png"));
		stage.setTitle("MAAL Assistant");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("view/resources/img/logo.png")));
		stage.setResizable(false);
		stage.show();
		
	}
	
	private static void setStage(Stage stage) {
		stageRoot = stage;
	}
	private static void setRoot(Page page) {
		root = page;
	}
	
	public static Stage getStage() {
        return stageRoot;
    }
	
	public static VBox  getRoot() {
        return root;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
