package lk.ijse.gdse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dto.UsersDTO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class LoginController {

    static UsersDTO activeUser;

    public static String roles;
    @FXML
    private AnchorPane MainAnchorPane;

    @FXML
    private Button BUTloging;

    @FXML
    private PasswordField TXTpassword;

    @FXML
    private TextField TXTuserName;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);
    MainDashBordContoller mainDashBordContoller = new MainDashBordContoller();
    AlertController alertController = new AlertController();

    public static UsersDTO getuser() {
        return activeUser;
    }

    @FXML
    void LogingOnAction(MouseEvent event) throws Exception {

        String userName = TXTuserName.getText();
        String password = TXTpassword.getText();

        if (password.isEmpty() || userName.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Empty Fields").show();
            return;
        }

        UsersDTO usersDto = new UsersDTO(userName, password);

        activeUser = userBO.searchRole(usersDto);

        if (activeUser != null) {
            Stage currentStage = (Stage) MainAnchorPane.getScene().getWindow();
            currentStage.setResizable(false);
            MainDashBordContoller controller = fadeOutAndLoadNewStage(currentStage);
        }
        else {
            new Alert(Alert.AlertType.CONFIRMATION,"logging fail").show();
        }
    }

    private MainDashBordContoller fadeOutAndLoadNewStage(Stage currentStage) {
        currentStage.close();

        try {
            Stage newStage = new Stage();
            newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainDashBord.fxml"))));
            newStage.getIcons().add(new Image(getClass().getResourceAsStream("/imege/logo-removebg-preview.png")));
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading the main UI").show();
        }
        return null;
    }


    @FXML
    void AdminRegistration(MouseEvent event) throws Exception {

        List<UsersDTO> users = userBO.getAll();

        if(users.size() > 0){
            alertController.INFORMATIONALERT("INFORMATION","ADMIN ALREADY EXIST" , Alert.AlertType.INFORMATION);
        }else {
            MainAnchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/registrationPage.fxml"));
            MainAnchorPane.getChildren().add(load);
        }
    }

    public void navigateTo(String path) {

        try {
            MainAnchorPane.getChildren().clear();
            Parent newContent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            MainAnchorPane.getChildren().add(newContent);

        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, "Error loading the page: " + path).show();
        }
    }
}
