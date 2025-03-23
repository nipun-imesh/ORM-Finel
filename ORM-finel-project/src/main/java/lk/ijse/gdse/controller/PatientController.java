package lk.ijse.gdse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.dto.tm.PAtientTM;

public class PatientController {

    @FXML
    private DatePicker DATSelectBox;

    @FXML
    private Label LBPId;

    @FXML
    private TableColumn<PAtientTM, String> TBCContact;

    @FXML
    private TableColumn<PAtientTM, String> TBCName;

    @FXML
    private TableColumn<PAtientTM, String> TBCRegDate;

    @FXML
    private TableColumn<PAtientTM, String> TBCid;

    @FXML
    private TableView<PAtientTM> TBLPatient;

    @FXML
    private TextField TXTContactNumber;

    @FXML
    private TextField TXTUserName;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;


    @FXML
    void addOnaction(MouseEvent event) {
        String id = LBPId.getText();
        String name = TXTUserName.getText();
        String contact = TXTContactNumber.getText();
        String date = DATSelectBox.getValue().toString();

        if(id.isEmpty() || name.isEmpty() || contact.isEmpty() || date.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION, "Empty Fields").show();
        } else {

        }
    }

    @FXML
    void deleteOnAction(MouseEvent event) {

    }

    @FXML
    void upDateOnaction(MouseEvent event) {

    }

}
