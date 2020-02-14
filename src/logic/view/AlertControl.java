package logic.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertControl {
	
	private AlertControl() {
		 throw new IllegalStateException("Utility class");
	 }

 	public static void infoBox(String infoMessage, String titleBar)
    {
        infoBox(infoMessage, titleBar, null);
    }


    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
    
    public static boolean confirmation() {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
    	alert.setHeaderText("");
    	alert.setTitle("Confirmation");
    	alert.showAndWait();

    	return alert.getResult() == ButtonType.YES;
    }
	
}
