package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.TherapiProgameBO;
import lk.ijse.gdse.bo.custom.TherapistBO;
import lk.ijse.gdse.dto.TherapistsDTO;
import lk.ijse.gdse.dto.TherapyProgramsDTO;
import lk.ijse.gdse.dto.tm.TherapistsTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {

    @FXML
    private Button BUTAdd;

    @FXML
    private Button BUTdelete;

    @FXML
    private Button BUTsearch;

    @FXML
    private Button BUTupdate;

    @FXML
    private Button BUTviewAll;

    @FXML
    private ComboBox<String> COMprogameId;

    @FXML
    private Label LBid;

    @FXML
    private TableColumn<TherapistsTM, String> TBCcontact;

    @FXML
    private TableColumn<TherapistsTM, String> TBCid;

    @FXML
    private TableColumn<TherapistsTM, String> TBCname;

    @FXML
    private TableColumn<TherapistsTM, String> TBCstatus;

    @FXML
    private TableColumn<TherapistsTM, String> TBCspecialization;

    @FXML
    private TableView<TherapistsTM> TBLtherapi;

    @FXML
    private TextField TXTcontact;

    @FXML
    private TextField TXTname;

    @FXML
    private TextField TXTspecializaed;

    @FXML
    private TextField searchField;

    AlertController alertController = new AlertController();
    TherapistBO therapistsBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);
    TherapiProgameBO therapiProgameBO = (TherapiProgameBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIPROGRAM);
    MainDashBordContoller mainDashBordContoller = new MainDashBordContoller();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TBCid.setCellValueFactory(new PropertyValueFactory<>("id"));
        TBCname.setCellValueFactory(new PropertyValueFactory<>("name"));
        TBCcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        TBCspecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        TBCstatus.setCellValueFactory(new PropertyValueFactory<>("status"));


        try {
            getAll();
            setProgramId();
            setTherapiId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addOnAction(MouseEvent event) throws Exception {
        String id = LBid.getText();
        String name = TXTname.getText();
        String specialization = TXTspecializaed.getText();
        String contact = TXTcontact.getText();
        String programId = (COMprogameId.getValue() != null) ? COMprogameId.getValue().toString() : null;

        if (id.isEmpty() || name.isEmpty() || specialization.isEmpty() || contact.isEmpty()) {
            alertController.INFORMATIONALERT("WARNING", "All fields are required", Alert.AlertType.WARNING);
            return;
        }

        TherapistsDTO therapistsDTO = new TherapistsDTO(
                id,
                name,
                specialization,
                contact,
                programId,
                (programId != null) ? "Not Available" : "Available"
        );

        boolean isAdded = therapistsBO.savetherapist(therapistsDTO, programId);
        if (isAdded) {
            alertController.INFORMATIONALERT("INFORMATION", "Added Successfully", Alert.AlertType.INFORMATION);
        } else {
            alertController.INFORMATIONALERT("ERROR", "Add Failed", Alert.AlertType.ERROR);
        }

        getAll();
    }

    @FXML
    void deleteOnAction(MouseEvent event) throws Exception {
        if(TBLtherapi.getSelectionModel().getSelectedItem() != null){
            String id = LBid.getText();
            boolean isDeleted = therapistsBO.delete(id);
            if(!isDeleted){
                alertController.INFORMATIONALERT("INFORMATION","Delete Failed" , Alert.AlertType.INFORMATION);
            }
        }
        alertController.INFORMATIONALERT("INFORMATION","Delete Successfully" , Alert.AlertType.INFORMATION);
        getAll();
    }

    @FXML
    void searchOnaction(MouseEvent event) {

    }

    @FXML
    void tableClickOnAction(MouseEvent event) throws Exception {

        if(TBLtherapi.getSelectionModel().getSelectedItem() != null){

            TherapistsTM selectedItem = TBLtherapi.getSelectionModel().getSelectedItem();

            LBid.setText(selectedItem.getId());
            TXTname.setText(selectedItem.getName());
            TXTspecializaed.setText(selectedItem.getSpecialization());
            TXTcontact.setText(selectedItem.getContact());
            COMprogameId.setValue(therapiProgameBO.getAll( ).get(0).getId());
        }
    }

    @FXML
    void upDAteOnaction(MouseEvent event) throws Exception {
        String id = LBid.getText();
        String name = TXTname.getText();
        String specialization = TXTspecializaed.getText();
        String contact = TXTcontact.getText();
        String programId = COMprogameId.getValue().toString();

        TherapistsDTO therapistsDTO = new TherapistsDTO(id,name,specialization,contact,programId,"Not Available");
        boolean isUpdated = therapistsBO.update(therapistsDTO, programId);
        if(!isUpdated){
            alertController.INFORMATIONALERT("INFORMATION","Update Failed" , Alert.AlertType.INFORMATION);
        }
        alertController.INFORMATIONALERT("INFORMATION","Update Successfully" , Alert.AlertType.INFORMATION);
        getAll();

    }

    @FXML
    void viewAllOnAction(MouseEvent event) throws Exception {
        clear();
        getAll();
    }

    public void setProgramId() throws Exception {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<TherapyProgramsDTO> therapyProgramsDTOS = therapiProgameBO.getAll();
        for (TherapyProgramsDTO therapyProgramsDTO : therapyProgramsDTOS) {
            observableList.add(therapyProgramsDTO.getId());
        }
        COMprogameId.setItems(observableList);
    }

    public void setTherapiId() throws Exception {
       String Thearpiid =  therapistsBO.getTherapistId();
       LBid.setText(Thearpiid);
    }

    public void getAll() throws Exception {
        List<TherapistsDTO> therapistsDTOS = therapistsBO.getAll();
        ObservableList<TherapistsTM> therapistsTMS = FXCollections.observableArrayList();
        for (TherapistsDTO therapistsDTO : therapistsDTOS) {
            therapistsTMS.add(new TherapistsTM(
                    therapistsDTO.getId(),
                    therapistsDTO.getName(),
                    therapistsDTO.getSpecialization(),
                    therapistsDTO.getContact(),
                    therapistsDTO.getStatus()
            ));}
        TBLtherapi.setItems(therapistsTMS);
    }

    public void clear() throws Exception {
        TXTname.clear();
        TXTspecializaed.clear();
        TXTcontact.clear();
        COMprogameId.getSelectionModel().clearSelection();
        COMprogameId.setPromptText("Program ID");
    }

}
