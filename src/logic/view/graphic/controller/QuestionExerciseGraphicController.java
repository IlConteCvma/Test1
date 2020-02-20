	package logic.view.graphic.controller;




import execption.QuestionException;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import logic.model.QuestionExerciseFactory;
import logic.model.QuestionType;


public class QuestionExerciseGraphicController extends QuestionGraphic {
	

	@FXML private TextArea foto;

	

	
	public QuestionExerciseGraphicController(String param) {
		super(param);
		super.factory = new QuestionExerciseFactory();
		super.qBean = this.factory.createBean();
	}

	
	public void commit() throws QuestionException  {
		if(super.commData()) {
				myData();
			super.sendData();
		}
		
		
	}
	
	private void myData() throws QuestionException {
		super.qBean.setType(QuestionType.EXERCISE);
		
		try {
			super.qBean.getClass().getMethod("setImage", String.class).invoke(super.qBean, this.foto.getText());
		}
		catch(ReflectiveOperationException e) {
			super.message.setText("Sorry some error");
			throw new QuestionException(e.getMessage());
		}
	}
	
}
