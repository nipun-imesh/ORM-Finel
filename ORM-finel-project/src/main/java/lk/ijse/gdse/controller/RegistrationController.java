package lk.ijse.gdse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dto.UsersDTO;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private TextField TXTuderName;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane registerMainAnchorpane;

     UserBO registrationBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);
     AlertController alertController = new AlertController();

    @FXML
    void Registration(MouseEvent event) throws Exception {
        String userName = TXTuderName.getText();
        String passwords = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if(!passwords.equals(confirmPassword)){
            new Alert(Alert.AlertType.INFORMATION, "Password does not match").show();
        }else if(userName.isEmpty() || passwords.isEmpty() || confirmPassword.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION, "Empty Fields").show();
        }else {
                String password = null;

                password = BCrypt.hashpw(passwords, BCrypt.gensalt());
                registrationBO.save(new UsersDTO( "AD001",userName, password, "Admin"));
        }
        alertController.INFORMATIONALERT("INFORMATION", "save successfully", Alert.AlertType.CONFIRMATION);
        loginAction(event);
    }

    @FXML
    void loginAction(MouseEvent event) throws IOException {
        registerMainAnchorpane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
        registerMainAnchorpane.getChildren().add(load);
    }

}
