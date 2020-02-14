
package logic.view.graphic.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import execption.LessonNotFoundException;
import execption.TimeException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import logic.Session;
import logic.bean.StudentBean;
import logic.controller.ViewTimeToExitController;
import logic.view.AlertControl;

public class HomeBoxGraphicController extends GraphicController implements Initializable{

	private ViewTimeToExitController controlUC = new ViewTimeToExitController();
	
	@FXML private Label dataOfStudent;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dataOfStudent.setText(""+Session.getSession().getStudent().getName()+" "+Session.getSession().getStudent().getSurname());
	}
	
	public void getStarted() throws IOException, SQLException  {
		StudentBean studLog = new StudentBean();
		studLog.setUsername(Session.getSession().getStudent().getUsername());
		try {
			controlUC.estimateTimeToExit();
		} catch (TimeException e) {
			AlertControl.infoBox("It's too late", "I'm sorry");
		} catch (LessonNotFoundException e) {
			AlertControl.infoBox("Today you haven't lesson, you can relax!", "I'm happy for you!");
		}
	}
}
