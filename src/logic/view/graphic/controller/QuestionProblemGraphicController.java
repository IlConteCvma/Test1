package logic.view.graphic.controller;




import execption.QuestionException;
import logic.model.QuestionProblemFactory;
import logic.model.QuestionType;


public class QuestionProblemGraphicController extends QuestionGraphic{

	public QuestionProblemGraphicController(String param) {
		super(param);
		super.factory = new QuestionProblemFactory();
		super.qBean = this.factory.createBean();
	}
	public void commit() throws QuestionException   {

		if(super.commData()) {
			myData();
		super.sendData();
		}

	}
	
	
	private void myData() {
		super.qBean.setType(QuestionType.PROBLEM);
	}


}
