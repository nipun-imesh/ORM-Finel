package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.TherapiProgameBO;
import lk.ijse.gdse.dto.PatientsDTO;
import lk.ijse.gdse.dto.TherapyProgramsDTO;
import lk.ijse.gdse.dto.tm.PAtientTM;
import lk.ijse.gdse.dto.tm.TherapiProgameTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapiProgrameController  implements Initializable {

    @FXML
    private Button BUTAdd;

    @FXML
    private Button BUTdelect;

    @FXML
    private Button BUTupDate;

    @FXML
    private Button BUTviewAll;

    @FXML
    private Label LBid;

    @FXML
    private TableView<TherapiProgameTM> TBLprogame;

    @FXML
    private TextArea TXTdescription;

    @FXML
    private TextField TXTduration;

    @FXML
    private TextField TXTfees;

    @FXML
    private TextField TXTprogameName;

    @FXML
    private TableColumn<TherapiProgameTM, String> durationColumn;

    @FXML
    private TableColumn<TherapiProgameTM, Integer> feeColumn;

    @FXML
    private TableColumn<TherapiProgameTM, String> idColumn;

    @FXML
    private TableColumn<TherapiProgameTM, String> nameColumn;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    TherapiProgameBO therapiProgameBO = (TherapiProgameBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIPROGRAM);
    AlertController alertController = new AlertController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee"));

        try {
            viewAllOnAction(null);
            setTheraoiProgrameId("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addOnAction(MouseEvent event) throws Exception {

        String id = LBid.getText();
        String name = TXTprogameName.getText();
        String duration = TXTduration.getText();
        int fees = Integer.parseInt(TXTfees.getText());


        if(id.isEmpty() || name.isEmpty() || duration.isEmpty() || TXTfees.getText().isEmpty()){
            new Alert(Alert.AlertType.INFORMATION, "Empty Fields").show();
        }else {
            boolean isSaved = therapiProgameBO.save(new TherapyProgramsDTO(id,name,duration,fees));
            if(!isSaved){
                alertController.INFORMATIONALERT("INFORMATION", "Save Failed", Alert.AlertType.INFORMATION);
            }else {
                alertController.INFORMATIONALERT("INFORMATION", "Save Successfully", Alert.AlertType.INFORMATION);
            }
        }

        viewAllOnAction(null);
    }

    @FXML
    void deleteOnAction(MouseEvent event) throws Exception {
        if(TBLprogame.getSelectionModel().getSelectedItem() != null){
            String id = LBid.getText();
            boolean isDeleted = therapiProgameBO.delete(id);
            if(!isDeleted){
                alertController.INFORMATIONALERT("INFORMATION", "Delete Failed", Alert.AlertType.INFORMATION);
            }else {
                alertController.INFORMATIONALERT("INFORMATION", "Delete Successfully", Alert.AlertType.INFORMATION);
            }
        }else {
            alertController.INFORMATIONALERT("INFORMATION", "Please Select a Row", Alert.AlertType.INFORMATION);
        }

    }

    @FXML
    void searchONaction(MouseEvent event) {

    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        if(TBLprogame.getSelectionModel().getSelectedItem() != null){
            TherapiProgameTM selectedItem = TBLprogame.getSelectionModel().getSelectedItem();

            LBid.setText(selectedItem.getId());
            TXTprogameName.setText(selectedItem.getName());
            TXTduration.setText(selectedItem.getDuration());
            TXTfees.setText(String.valueOf(selectedItem.getFee()));
        }else {
            alertController.INFORMATIONALERT("INFORMATION", "Please Select a Row", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void upDAteOnAction(MouseEvent event) throws Exception {
        if(TBLprogame.getSelectionModel().getSelectedItem() != null){
            String id = LBid.getText();
            String name = TXTprogameName.getText();
            String duration = TXTduration.getText();
            int fees = Integer.parseInt(TXTfees.getText());

           boolean isUpdated = therapiProgameBO.update(new TherapyProgramsDTO(id,name,duration,fees));

            if(!isUpdated){
                alertController.INFORMATIONALERT("INFORMATION", "Update Failed", Alert.AlertType.INFORMATION);
            }else {
                alertController.INFORMATIONALERT("INFORMATION", "Update Successfully", Alert.AlertType.INFORMATION);
            }
        }else {
            alertController.INFORMATIONALERT("INFORMATION", "Please Select a Row", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void viewAllOnAction(MouseEvent event) throws Exception {
        ArrayList<TherapyProgramsDTO> customerDTOS = (ArrayList<TherapyProgramsDTO>) therapiProgameBO.getAll();
        ObservableList<TherapiProgameTM> customerTMS = FXCollections.observableArrayList();

        for ( TherapyProgramsDTO programsDTO : customerDTOS) {
            customerTMS.add(new TherapiProgameTM(
                    programsDTO.getId(),
                    programsDTO.getName(),
                    programsDTO.getDuration(),
                    (int) programsDTO.getFee()
            ));
        }
        TBLprogame.setItems(customerTMS);
    }

    void setTheraoiProgrameId( String id) {
        LBid.setText(therapiProgameBO.getProgameId(id));
    }

}
