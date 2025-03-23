package lk.ijse.gdse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.PatientBO;
import lk.ijse.gdse.dto.PatientsDTO;
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

    AlertController alertController = new AlertController();
    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);

    @FXML
    void addOnaction(MouseEvent event) throws Exception {
        String id = LBPId.getText();
        String name = TXTUserName.getText();
        String contact = TXTContactNumber.getText();
        String date = DATSelectBox.getValue().toString();
        System.out.println(date);

        if( name.isEmpty() || contact.isEmpty() || date.isEmpty()){
            alertController.INFORMATIONALERT("INFORMATION","Empty Fields" , Alert.AlertType.INFORMATION);
        } else {
            patientBO.save(new PatientsDTO("CO001",name,contact,date));

        }
    }

    @FXML
    void deleteOnAction(MouseEvent event) {
        if(TBLPatient.getSelectionModel().getSelectedItem() != null){
            String id = LBPId.getText();
            patientBO.delete(id);
        }
    }

    @FXML
    void upDateOnaction(MouseEvent event) {

    }

}
