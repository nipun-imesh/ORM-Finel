package lk.ijse.gdse.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.PatientBO;
import lk.ijse.gdse.bo.custom.TherapiProgameBO;
import lk.ijse.gdse.bo.custom.TherapisassionBO;
import lk.ijse.gdse.bo.custom.TherapistBO;
import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dto.PatientsDTO;
import lk.ijse.gdse.dto.TherapisassionDTO;
import lk.ijse.gdse.dto.TherapistsDTO;
import lk.ijse.gdse.dto.TherapyProgramsDTO;
import lk.ijse.gdse.dto.tm.TherapiSassionTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TherapisassionController implements Initializable {


    @FXML
    private Button BUTAdd;

    @FXML
    private Button BUTDelete;

    @FXML
    private Button BUTReset;

    @FXML
    private Button BUTUpdate;

    @FXML
    private TableColumn<TherapiSassionTM, String> COLPatientID;

    @FXML
    private TableColumn<TherapiSassionTM, String> COLProgramID;

    @FXML
    private TableColumn<TherapiSassionTM, String> COLSessionDate;

    @FXML
    private TableColumn<TherapiSassionTM, String> COLSessionID;

    @FXML
    private TableColumn<TherapiSassionTM, String> COLTherapistID;

    @FXML
    private ComboBox<String> COMPatientId;

    @FXML
    private ComboBox<String> COMProgameID;

    @FXML
    private ComboBox<String> COMTherapistId;

    @FXML
    private DatePicker DATsessionDate;

    @FXML
    private Label LBPatientId;

    @FXML
    private Label LBProgameFee;

    @FXML
    private Label LBProgameName;

    @FXML
    private Label LBSessionID;

    @FXML
    private TableView<TherapiSassionTM> TBLSession;

    @FXML
    private AnchorPane anchrpansession;

    TherapisassionBO therapisassionBO = (TherapisassionBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPISASSION);
    TherapiProgameBO therapiProgameBO = (TherapiProgameBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIPROGRAM);
    TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);
    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    AlertController alertController = new AlertController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        COLSessionID.setCellValueFactory(new PropertyValueFactory<>("sessionid"));
        COLPatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        COLTherapistID.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        COLProgramID.setCellValueFactory(new PropertyValueFactory<>("programId"));
        COLSessionDate.setCellValueFactory(new PropertyValueFactory<>("sessinDate"));

        try {
            setProgramId();
            setTherapistId();
            setPatientId();
            setTherapiSassionUId("");
            getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addOnAction(MouseEvent event)  {
            String sessionId = LBSessionID.getText();
            String patientId = COMPatientId.getValue();
            String patientName = LBPatientId.getText();
            String programId = COMProgameID.getValue();
            String programFee = LBProgameFee.getText();
            String therapistId = COMTherapistId.getValue();
            String sessionDate = DATsessionDate.getValue().toString();
            String programName = LBProgameName.getText();


            if (sessionId.isEmpty() || patientId.isEmpty() || programId.isEmpty() || therapistId.isEmpty() || sessionDate.isEmpty() || patientName.isEmpty() || programFee.isEmpty() || programName.isEmpty()) {
                alertController.INFORMATIONALERT("WARNING", "All fields are required", Alert.AlertType.WARNING);
                return;
            }

        boolean isAdded = false;
        try {
            isAdded = therapisassionBO.savetherapisassion(new TherapisassionDTO(sessionId, patientId, programId, therapistId, sessionDate, patientName, programName, Double.parseDouble(programFee)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (isAdded) {
                alertController.INFORMATIONALERT("INFORMATION", "Added Successfully", Alert.AlertType.INFORMATION);
            } else {
                alertController.INFORMATIONALERT("ERROR", "Add Failed", Alert.AlertType.ERROR);
            }

        try {
            getAll();
            setTherapiSassionUId("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void getAll() throws Exception {
        List<TherapisassionDTO> all = therapisassionBO.getAll();
        ObservableList<TherapiSassionTM> therapiSassionTMS = FXCollections.observableArrayList();
        for (TherapisassionDTO therapySessions : all) {
            therapiSassionTMS.add(new TherapiSassionTM(
                    therapySessions.getSessionid(),
                    therapySessions.getPatientId(),
                    therapySessions.getTherapistId(),
                    therapySessions.getProgramId(),
                    therapySessions.getSessinDate()));
            System.out.println(therapySessions.getSessionid()+"  nipun");
        }

        TBLSession.setItems(therapiSassionTMS);

    }

    @FXML
    void deleteOnAction(MouseEvent event) {
        if(TBLSession.getSelectionModel().isEmpty()){
            alertController.INFORMATIONALERT("WARNING", "Select a row", Alert.AlertType.WARNING);
        }else {
            String sessionId = LBSessionID.getText();
            boolean isDeleted = false;
            try {
                isDeleted = therapisassionBO.delete(sessionId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (isDeleted) {
                alertController.INFORMATIONALERT("INFORMATION", "Deleted Successfully", Alert.AlertType.INFORMATION);
            } else {
                alertController.INFORMATIONALERT("ERROR", "Delete Failed", Alert.AlertType.ERROR);
            }
            try {
                getAll();
                setTherapiSassionUId("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    @FXML
    void reSetOnAction(MouseEvent event) {
        LBSessionID.setText(therapisassionBO.getTherapiSassionId(""));
        COMPatientId.setValue(null);
        COMProgameID.setValue(null);
        COMTherapistId.setValue(null);
        DATsessionDate.setValue(null);
        LBPatientId.setText("");
        LBProgameName.setText("");
        LBProgameFee.setText("");

    }

    @FXML
    void sessionTableClickOnaction(MouseEvent event) {
        try {
            TherapiSassionTM therapiSassionTM = TBLSession.getSelectionModel().getSelectedItem();
            if (TBLSession.getSelectionModel().getSelectedItem() != null) {
                LBSessionID.setText(therapiSassionTM.getSessionid());
                COMPatientId.setValue(therapiSassionTM.getPatientId());
                COMProgameID.setValue(therapiSassionTM.getProgramId());
                COMTherapistId.setValue(therapiSassionTM.getTherapistId());

                String dateStr = therapiSassionTM.getSessinDate();
                if (dateStr != null && !dateStr.trim().isEmpty()) {
                    DATsessionDate.setValue(LocalDate.parse(dateStr));
                } else {

                    DATsessionDate.setValue(null);
                    System.out.println("Date is null or empty for session: " + therapiSassionTM.getSessionid());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void upDateOnAction(MouseEvent event) {
       if(LBSessionID.getText().isEmpty() || COMPatientId.getValue() == null || COMProgameID.getValue() == null || COMTherapistId.getValue() == null || DATsessionDate.getValue() == null){
           alertController.INFORMATIONALERT("WARNING", "All fields are required", Alert.AlertType.WARNING);
       }else {
           boolean isUpdated = false;
           try {
               isUpdated = therapisassionBO.updateTherapisassion(new TherapisassionDTO(
                       LBSessionID.getText(),
                       COMPatientId.getValue().toString(),
                       COMProgameID.getValue().toString(),
                       COMTherapistId.getValue().toString(),
                       DATsessionDate.getValue().toString()
               ));
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
           if (isUpdated) {
               alertController.INFORMATIONALERT("INFORMATION", "Updated Successfully", Alert.AlertType.INFORMATION);
           } else {
               alertController.INFORMATIONALERT("ERROR", "Update Failed", Alert.AlertType.ERROR);
           }
           try {
               getAll();
               setTherapiSassionUId("");
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }
    }

    void getUiAllData() throws Exception {
        therapisassionBO.therapiSassionGetUiData();

    }

    public void setProgramId() throws Exception {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<TherapyProgramsDTO> therapyProgramsDTOS = therapiProgameBO.getAll();
        for (TherapyProgramsDTO therapyProgramsDTO : therapyProgramsDTOS) {
            observableList.add(therapyProgramsDTO.getId());
        }
        COMProgameID.setItems(observableList);
    }

    public void setTherapistId() throws Exception {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<TherapistsDTO> therapyProgramsDTOS = therapistBO.getAll();

        for (TherapistsDTO therapistsDTO : therapyProgramsDTOS) {
            observableList.add(therapistsDTO.getId());
        }
        COMTherapistId.setItems(observableList);
    }

    public void setPatientId() throws Exception {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<PatientsDTO> therapyProgramsDTOS = patientBO.getAll();

        for (PatientsDTO therapistsDTO : therapyProgramsDTOS) {
            observableList.add(therapistsDTO.getId());
        }
        COMPatientId.setItems(observableList);
    }

    @FXML
    void PationIsSelectedOnAtion(ActionEvent event) {
        if( COMPatientId.getValue() != null){
            try {
                String id = COMPatientId.getValue();
                String name = therapisassionBO.getPatientName(id);

                LBPatientId.setText(name);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void ProgameIsSelectedOnAction(ActionEvent event) {
        if (COMProgameID.getValue() != null) {
            try {
                TherapyProgramsDTO therapyProgramsDTO  = therapiProgameBO.getProgameNameAndFee(COMProgameID.getValue());

                if (therapyProgramsDTO != null ) {

                        LBProgameName.setText(therapyProgramsDTO.getName());
                        LBProgameFee.setText(String.valueOf(therapyProgramsDTO.getFee()));

                } else {
                    LBProgameName.setText( "");
                    LBProgameFee.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void TherapistIsSelectedOnAction(MouseEvent event) {
        if(COMTherapistId.getValue() != null){
            try {
                getUiAllData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    void setTherapiSassionUId(String id)  {
        LBSessionID.setText(therapisassionBO.getTherapiSassionId(id));
    }
}
