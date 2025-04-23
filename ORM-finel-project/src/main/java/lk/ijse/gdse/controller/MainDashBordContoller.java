package lk.ijse.gdse.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.gdse.dto.UsersDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainDashBordContoller {


    @FXML
    private AnchorPane ANKLoadPage;

    @FXML
    private Label LBpatientCount;

    @FXML
    private Label LBrole;

    @FXML
    private Label LBprogramCount;

    @FXML
    private Label LBsessionCount;

    @FXML
    private Label LBtherapistCount;

    @FXML
    private Button Logout;

    @FXML
    private Button BUTPatientManagement;

    @FXML
    private Button BUTPaymentnvoiceManagement;

    @FXML
    private Button BUTReportingAnalytics;

    @FXML
    private Button BUTTherapistManagement;

    @FXML
    private Button BUTTherapyProgramManagement;

    @FXML
    private Button BUTTherapySessionScheduling;

    @FXML
    private Button BUTUserManagement;

    @FXML
    private StackPane contentArea;

    UsersDTO usersDTO = LoginController.getuser();


    public void initialize(){

        LBrole.setText(usersDTO.getRole());
        LBrole.setStyle(LBrole.getStyle() + ";-fx-text-fill: blue;-fx-font-weight: bold");
        checkRoll();
    }

    private void checkRoll() {

        if (usersDTO.getRole().equals("Admin") ) {

        } else if (usersDTO.getRole() .equals("Reception")) {

            BUTUserManagement.setVisible(false);
            BUTTherapistManagement.setVisible(false);
            BUTTherapyProgramManagement.setVisible(false);
        }
    }

    @FXML
    void LogPatientManagement(MouseEvent event) throws IOException {
        ANKLoadPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/patiensPage.fxml"));
        ANKLoadPage.getChildren().add(load);
    }

    @FXML
    void LogPaymentManagement(MouseEvent event) throws IOException {
        ANKLoadPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/paymentPage.fxml"));
        ANKLoadPage.getChildren().add(load);
    }

    @FXML
    void LogReportingAnalytics(MouseEvent event) {

    }

    @FXML
    void LogTherapistManagement(MouseEvent event) throws IOException {
        ANKLoadPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/theropyManagePage.fxml"));
        ANKLoadPage.getChildren().add(load);
    }

    @FXML
    void LogTherapyProgramManagement(MouseEvent event) throws IOException {
        ANKLoadPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/therapiProgamePage.fxml"));
        ANKLoadPage.getChildren().add(load);
    }

    @FXML
    void LogTherapySessionScheduling(MouseEvent event) throws IOException {
        ANKLoadPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Therapisassion.fxml"));
        ANKLoadPage.getChildren().add(load);
    }

    @FXML
    void LogUserManagement(MouseEvent event) throws IOException {
        ANKLoadPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/UserPage.fxml"));
        ANKLoadPage.getChildren().add(load);

    }

    @FXML
    void LogoutAction(MouseEvent event) {
        Stage currentStage = (Stage) ANKLoadPage.getScene().getWindow();

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), currentStage.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(e -> {
            currentStage.close();
            try {
                Stage newStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
                Scene scene = new Scene(root);
                newStage.setScene(scene);
                newStage.setTitle("ABC Exam Management System");
                newStage.getIcons().add(new Image(getClass().getResourceAsStream("/imege/logo-removebg-preview.png")));

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), root);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

                newStage.show();
                fadeIn.play();
            } catch (IOException ex) {
                new Alert(Alert.AlertType.ERROR, "Error loading the login page").show();
            }
        });

        fadeOut.play();
    }
}
