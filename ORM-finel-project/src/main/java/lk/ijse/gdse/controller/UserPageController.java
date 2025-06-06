package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dto.UsersDTO;
import lk.ijse.gdse.dto.tm.UsetTM;
import lk.ijse.gdse.entity.Users;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {

    @FXML
    private Button BUTAdd;

    @FXML
    private Button BUTDelete;

    @FXML
    private Button BUTSearch;

    @FXML
    private Button BUTUpdate;

    @FXML
    private Button BUTViewAll;

    @FXML
    private ComboBox<String> COMBRole;

    @FXML
    private TableColumn<UsetTM, String> TBCId;

    @FXML
    private TableColumn<UsetTM, String> TBCName;

    @FXML
    private TableColumn<UsetTM, String> TBCRole;

    @FXML
    private TableView<UsetTM> TBLUse;

    @FXML
    private PasswordField TXTPassword;

    @FXML
    private Label TXTID;

    @FXML
    private TextField TXTSearchName;

    @FXML
    private TextField TXTUsename;

    AlertController alertController = new AlertController();
    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TBCId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TBCName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        TBCRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        try {
            viewAllOnAction(null);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        COMBRole.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue != null) {
                    String generatedId = userBO.generateNewUserIdByRole(newValue);
                    setId(generatedId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    void addOnAction(MouseEvent event)  {
        if( TXTUsename.getText().isEmpty() || TXTPassword.getText().isEmpty() || COMBRole.getValue().isEmpty()){
            alertController.INFORMATIONALERT("INFORMATION","Empty Fields" , Alert.AlertType.INFORMATION);
        } else {

            String id = TXTID.getText();
            String userName = TXTUsename.getText();
            String passwords = TXTPassword.getText();
            String role = COMBRole.getValue();

            if (userName.isEmpty() || passwords.isEmpty() || role.isEmpty() || id.isEmpty())  {
                alertController.INFORMATIONALERT("INFORMATION", "Empty Fields", Alert.AlertType.INFORMATION);
            } else {
                String password = null;

                password = BCrypt.hashpw(passwords, BCrypt.gensalt());
                try {
                    userBO.save(new UsersDTO( id,userName, password, role));
                } catch (Exception e) {
                    alertController.INFORMATIONALERT("INFORMATION", " save failed", Alert.AlertType.ERROR);
                    throw new RuntimeException(e);
                }
            }
            alertController.INFORMATIONALERT("INFORMATION", "save successfully", Alert.AlertType.INFORMATION);
        }
        try {
            viewAllOnAction(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteOnAction(MouseEvent event) {
        try {
            if( TBLUse.getSelectionModel().getSelectedItem() != null){
                TXTID.getText();
                userBO.delete(TXTID.getText());
            }else {
                BUTDelete.setDisable(true);
            }
        } catch (Exception e) {
            alertController.INFORMATIONALERT("INFORMATION", "delete failed", Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
        TBLUse.refresh();
        alertController.INFORMATIONALERT("INFORMATION", "delete successfully", Alert.AlertType.INFORMATION);
    }

    @FXML
    void searchOnAction(MouseEvent event) {

    }


    @FXML
    void updateOnAction(MouseEvent event) {
            if( TBLUse.getSelectionModel().getSelectedItem() != null){
                BUTUpdate.setDisable(true);
            }else {
                BUTUpdate.setDisable(false);
            }

            String id = TXTID.getText();
            String userName = TXTUsename.getText();
            String passwords = TXTPassword.getText();
            String role = COMBRole.getValue();

            if (userName.isEmpty() || id.isEmpty() || role.isEmpty()) {
                alertController.INFORMATIONALERT("INFORMATION", "Empty Fields", Alert.AlertType.INFORMATION);
            } else {
                String password = null;
                password = BCrypt.hashpw( passwords, BCrypt.gensalt());
                try {
                    userBO.update(new UsersDTO( id,userName, password, role));
                } catch (Exception e) {
                    alertController.INFORMATIONALERT("INFORMATION", "update failed", Alert.AlertType.ERROR);
                    throw new RuntimeException(e);
                }
                alertController.INFORMATIONALERT("INFORMATION", "update successfully", Alert.AlertType.INFORMATION);
            }
    }

    @FXML
    void viewAllOnAction(MouseEvent event) throws Exception {
        ArrayList<UsersDTO> customerDTOS = (ArrayList<UsersDTO>) userBO.getAll();
        ObservableList<UsetTM> customerTMS = FXCollections.observableArrayList();

        for ( UsersDTO customerDTO : customerDTOS) {
            customerTMS.add(new UsetTM(
                    customerDTO.getId(),
                    customerDTO.getUserName(),
                    customerDTO.getPassword(),
                    customerDTO.getRole()
                    ));

        }
        TBLUse.setItems(customerTMS);
        TBLUse.refresh();
    }

    @FXML
    void clickTableOnAction(MouseEvent event) {
        if (TBLUse.getSelectionModel().getSelectedItem() != null) {
            UsetTM selectedItem = TBLUse.getSelectionModel().getSelectedItem();

            TXTUsename.setText(selectedItem.getUserName());
            COMBRole.setValue(selectedItem.getRole());
            TXTID.setText(selectedItem.getId());

            TXTPassword.clear();

            BUTUpdate.setDisable(false);

        } else {
            BUTUpdate.setDisable(true);
            alertController.INFORMATIONALERT("INFORMATION", "Select User", Alert.AlertType.INFORMATION);
        }
    }

    private void setId(String id) {
        TXTID.setText(id);
    }
}
