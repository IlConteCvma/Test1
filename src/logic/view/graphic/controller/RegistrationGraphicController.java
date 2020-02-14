package logic.view.graphic.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.bean.CourseBean;
import logic.bean.UserBean;
import logic.controller.RegistrationController;
import logic.model.Subject;
import logic.model.TypeVehicle;
import logic.view.AlertControl;
import logic.view.NamePage;

public class RegistrationGraphicController extends GraphicController implements Initializable {

	private static final int ROWPERSONALDATA = 2;
	private static final int COLPERSONALDATA = 2;
	private static final String[] PERSONALDATA = { "name", "surname", "street", "city" };
	private static final String[] CREDENTIAL = { "username", "password" };
	private static final int NUMCREDENTIAL = 2;

	@FXML private GridPane personalDataGridPane;
	@FXML private GridPane credentialDataGridPane;
	@FXML private ChoiceBox<String> choiceBoxProfession;
	@FXML private ChoiceBox<TypeVehicle> choiceBoxVehicle;
	@FXML private ImageView refresh;
	@FXML private AnchorPane container;
	@FXML private Label title;
	
	private RegistrationController regCtrl = new RegistrationController();
	TextField[] textFieldPersonalData = new TextField[4];
	TextField[] textFieldCredential = new TextField[2];
	TextField streetNumberField;
	ChoiceBox<String> choiceOfCourse;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Set the gridpane of personal data
		personalDataGridPane.setHgap(10);
		personalDataGridPane.setVgap(10);
		for (int i = 0; i < ROWPERSONALDATA; i++) {
			for (int j = 0; j < COLPERSONALDATA; j++) {
				Label l = new Label("Insert " + PERSONALDATA[ROWPERSONALDATA * i + j]);
				VBox verticalBox;
				textFieldPersonalData[ROWPERSONALDATA * i + j] = new TextField();
				textFieldPersonalData[ROWPERSONALDATA * i + j].setPromptText(PERSONALDATA[ROWPERSONALDATA * i + j]);
				if (i == 1 && j == 0) {
					streetNumberField = new TextField();
					streetNumberField.setMaxWidth(60);
					streetNumberField.setPromptText("n°");
					HBox horizzontalBox = new HBox(textFieldPersonalData[ROWPERSONALDATA * i + j],
							streetNumberField);
					verticalBox = new VBox(l, horizzontalBox);

				} else {
					verticalBox = new VBox(l, textFieldPersonalData[ROWPERSONALDATA * i + j]);
				}
				personalDataGridPane.add(verticalBox, j, i);
			}
		}

		credentialDataGridPane.setHgap(10);
		credentialDataGridPane.setVgap(10);

		// Set the gridpane of personal data
		for (int i = 0; i < NUMCREDENTIAL; i++) {
			Label l = new Label("Insert " + CREDENTIAL[i]);
			textFieldCredential[i] = new TextField();
			textFieldCredential[i].setPromptText(CREDENTIAL[i]);
			VBox verticalBox = new VBox(l, textFieldCredential[i]);
			credentialDataGridPane.add(verticalBox, i, 0);

		}

		// Set choiche box of profession
		choiceBoxProfession.getItems().addAll("Professor", "Student");
		choiceBoxProfession.getSelectionModel().select(1);
		choiceBoxProfession.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue,
						String newValue) -> changeProfession(newValue));

		// Set choiche box
		choiceBoxVehicle.getItems().addAll(TypeVehicle.BUS, TypeVehicle.CAR, TypeVehicle.SCOOTER);
		choiceBoxVehicle.getSelectionModel().select(0);
	}

	@FXML
	public void signIn() throws IOException {
		goToPage(NamePage.LOGIN);
	}

	@FXML
	public void signUp() {

		// check data inserted
		if (checkData() && AlertControl.confirmation()) {
				UserBean newUser = new UserBean();
				populeBean(newUser);
				regCtrl.createStudent(newUser);
				container.getChildren().remove(1);
				chooseCourse();
		}
	}

	public void changeProfession(String param) {
		if (param.equals("Professor")) {
			AlertControl.infoBox("I can't know you yet", "I'm sorry");
			choiceBoxProfession.getSelectionModel().select(1);
		}
		
	}
	
	private void chooseCourse() {
		title.setText("Enter your subject");
		Label selectCourse = new Label("Select your course: ");
		choiceOfCourse = new ChoiceBox<>();
		choiceOfCourse.getItems().addAll("Software engineer", "Civil engineer");
		choiceOfCourse.getSelectionModel().selectedItemProperty()
		.addListener((ObservableValue<? extends String> observable, String oldValue,
				String newValue) -> chooseSubject(newValue));
		HBox horizzontalBox = new HBox(selectCourse, choiceOfCourse);
		horizzontalBox.setLayoutY(50);
		horizzontalBox.setLayoutX(80);
		horizzontalBox.setAlignment(Pos.CENTER);
		container.getChildren().add(horizzontalBox);
		container.setLayoutY(100);
	}

	private void chooseSubject(String param) {
		try {
			CourseBean course = regCtrl.findCourse(param);
			List<Subject> subjcetOfCourse = course.getSubjectOfCourse();
			CheckBox[] allSubject = new CheckBox[subjcetOfCourse.size()];
			GridPane gp = new GridPane();
			for(int i=0;i<subjcetOfCourse.size();i++) {
				allSubject[i] = new CheckBox(subjcetOfCourse.get(i).getName());
				gp.add(allSubject[i], 0, i);
			}
			gp.setLayoutY(130);
			gp.setLayoutX(135);
			gp.setVgap(20);
	        Button b = new Button("Follow");
	        gp.add(b, 0, subjcetOfCourse.size());
	        gp.setAlignment(Pos.CENTER);
	        b.setLayoutX(180);
	        b.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) { 	
						if(completeRegistration(allSubject)) {
							AlertControl.infoBox("Welcome in MAAL\n\nRegistration completed!", "WELCOME");
							try {
								goToPage(NamePage.LOGIN);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
	            }
	        });
	        Label l = new Label();
	        gp.add(l, 0, subjcetOfCourse.size()+1);
	        container.getChildren().add(gp);
		} catch (SQLException e) {
			e.printStackTrace();
			AlertControl.infoBox("Ops..", "I'm sorry!");
		}
	}
	
	public boolean completeRegistration(CheckBox[] allSubject) {
		List<String> subjectNames = new ArrayList<>();
		int cnt = 0;
		for(int i=0;i<allSubject.length;i++) {
			if(allSubject[i].isSelected()){
				cnt++;
				subjectNames.add(allSubject[i].getText());
			}
		}
		if(cnt == 0) {
			AlertControl.infoBox("Select one subject", "Errore");
		}else {
			try {
				regCtrl.followSubject(subjectNames);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	private boolean checkData() {

		// check personal data
		for (int i = 0; i < textFieldPersonalData.length - 1; i++) {
			if (textFieldPersonalData[i].getText().isEmpty()) {
				AlertControl.infoBox("Check personal data", "Error");
				return false;
			}
		}

		// check credential
		for (int i = 0; i < textFieldCredential.length; i++) {
			if (textFieldCredential[i].getText().isEmpty()) {
				AlertControl.infoBox("Check credential", "Error");
				return false;
			}
		}
		
		return true;
	}

	private void populeBean(UserBean newUser) {
		newUser.setName(textFieldPersonalData[0].getText());
		newUser.setSurname(textFieldPersonalData[1].getText());
		newUser.setUsername(textFieldCredential[0].getText());
		newUser.setPassword(textFieldCredential[1].getText());
		newUser.setAddress(textFieldPersonalData[2].getText());
		newUser.setCity(textFieldPersonalData[2].getText());
		newUser.setNumberOfStreet(streetNumberField.getText());
		newUser.setVehicle(choiceBoxVehicle.getValue());
	}

}