package logic.view.graphic.controller;

import java.io.IOException;

import execption.QuestionException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import logic.bean.QuestionBean;
import logic.controller.InsertQuestionController;
import logic.model.QuestionFactory;
import logic.view.AlertControl;
import logic.view.NamePage;

public class QuestionGraphic extends GraphicController {
	
	protected QuestionFactory factory;
	protected String subject;
	protected QuestionBean qBean;
	@FXML protected Label message;
	@FXML protected TextArea title;
	@FXML protected TextArea body;
	
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
		
		}
		catch(QuestionException e){
			
			AlertControl.infoBox("error on save DB", "ERROR");
			
			this.message.setText("Error on save");
		}
		this.message.setText("finito");
	}
	
	public void back() throws IOException {
		goToPage(NamePage.QUESTIONTYPE);
	}
}
