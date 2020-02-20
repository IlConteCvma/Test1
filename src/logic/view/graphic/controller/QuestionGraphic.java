package logic.view.graphic.controller;


import java.net.URL;
import java.util.ResourceBundle;

import execption.QuestionException;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import logic.bean.QuestionBean;
import logic.controller.InsertQuestionController;
import logic.model.QuestionFactory;
import logic.view.AlertControl;
import logic.view.NamePage;


public class QuestionGraphic extends GraphicController {

	private static final String WARNING = "COMING SOON";
	private static final String NOTAVAILABLE = "Operation not available";
	protected QuestionFactory factory;
	protected String subject;
	protected QuestionBean qBean;
	@FXML protected Label message;
	@FXML protected TextArea title;
	@FXML protected TextArea body;
	@FXML protected Button saveButton;
	
	public QuestionGraphic(String param) {
				
		this.subject = param;
	}
	
	
	
	public boolean commData() throws QuestionException{
		
		
		if(title.getText().isEmpty() ) {
			this.message.setText("Please enter a title");
			return false;
			
		}
		else if(body.getText().isEmpty()) {
			this.message.setText("Please the body");
			return false;
		}
		
		else {
			this.message.setText("Starting save");
			
			try {
				qBean.setTitle(title.getText());
				qBean.getClass().getMethod("setText", String.class).invoke(qBean,this.body.getText());
				
			}
			catch(ReflectiveOperationException e){
				this.message.setText("Sorry some error");
				throw new QuestionException(e.getMessage());
			}
			return true;
		}
	}
	
	public void sendData() {
		
		try {
			InsertQuestionController controller = new InsertQuestionController(this.factory,qBean);
			controller.startSave(this.subject);
			this.message.setText("Save completed");
			saveButton.setDisable(true);
		
		}
		catch(QuestionException e){
			//il messaggio deve catturlo dall'exception e
			AlertControl.infoBox("error on save DB", "ERROR");
			
			this.message.setText("Error on save");
		}
		
		
	}
	
	public void back(){
		goToPage(NamePage.QUESTIONTYPE);
		
	}
	
	public void saveLocal() {
		AlertControl.infoBox(NOTAVAILABLE, WARNING);
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		message.setAlignment(Pos.CENTER);
		message.setText(subject);
	}

}
