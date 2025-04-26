package lk.ijse.gdse.controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertController {

    public void INFORMATIONALERT(String title,String message ,Alert.AlertType type){

        Alert alert = new Alert(type );
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getScene().getStylesheets().add(getClass().getResource("/css/alert-style.css").toExternalForm());

        alert.show();
    }

    public void CONFIRMATIONALERT(String information, String savedSuccessfully, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(information);
        alert.setHeaderText(null);
        alert.setContentText(savedSuccessfully);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getScene().getStylesheets().add(getClass().getResource("/css/alert-style.css").toExternalForm());

        alert.showAndWait();
    }

}
