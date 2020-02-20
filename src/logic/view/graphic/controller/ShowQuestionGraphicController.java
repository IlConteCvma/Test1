package logic.view.graphic.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import logic.bean.QuestionBean;
import logic.model.QuestionType;
import logic.view.AlertControl;
import logic.view.NamePage;

public class ShowQuestionGraphicController extends GraphicController {
	
	
	private static final String WARNING = "COMING SOON";
	private static final String NOTAVAILABLE = "Operation not available";
	private QuestionBean bean;
	
	@FXML protected Label message;
	@FXML protected TextArea title;
	@FXML protected TextArea body;
	@FXML protected VBox box;
	
	
	public ShowQuestionGraphicController(Object param) {
		this.bean = (QuestionBean)param;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.message.setText(this.bean.getType().toString()+"\nAutor: "+this.bean.getStudent()+"\nSubject: "+this.bean.getSubject());
		title.setText(bean.getTitle());
		
		Object returned;
		
		try {
			returned = bean.getClass().getMethod("getText").invoke(bean);
			body.setText((String)returned);
			
		} catch (ReflectiveOperationException e) {
			
			AlertControl.infoBox(NOTAVAILABLE, WARNING);
		}
		
		if(bean.getType().equals(QuestionType.EXERCISE)) {
			try {
				returned = bean.getClass().getMethod("getImage").invoke(bean);
				Label text = new Label("Image");
				TextArea image = new TextArea();
				image.setMaxHeight(100);
				image.setEditable(false);
				image.setText((String)returned);
				box.getChildren().addAll(text,image);
				
			} catch (ReflectiveOperationException e) {
				
				AlertControl.infoBox(NOTAVAILABLE, WARNING);
			}
		}
		
	}
	
	public void saveLocal() {
		AlertControl.infoBox(NOTAVAILABLE, WARNING);
	}
	public void answer() {
		AlertControl.infoBox(NOTAVAILABLE, WARNING);
	}
	
	public void back() {
			goToPage(NamePage.ALLQUESTION);
	
	}
	
	
	

}
