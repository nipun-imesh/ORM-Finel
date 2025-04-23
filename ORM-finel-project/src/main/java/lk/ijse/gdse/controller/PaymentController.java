package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.*;
import lk.ijse.gdse.dto.*;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class PaymentController implements Initializable {

    @FXML
    private Button BUTAdd;

    @FXML
    private Button BUTDelete;

    @FXML
    private Button BUTreset;

    @FXML
    private TableColumn<?, ?> COLamount;

    @FXML
    private TableColumn<?, ?> COLid;

    @FXML
    private TableColumn<?, ?> COLpatientId;

    @FXML
    private TableColumn<?, ?> COLpaymentDate;

    @FXML
    private TableColumn<?, ?> COLprogramId;

    @FXML
    private TableColumn<?, ?> COLsession;

    @FXML
    private TableColumn<?, ?> COLtherapistId;

    @FXML
    private ComboBox<String> COMSessionId;

    @FXML
    private ComboBox<String> COMpatientId;

    @FXML
    private ComboBox<String> COMprogamrId;

    @FXML
    private ComboBox<String> COMtherapist;

    @FXML
    private DatePicker DATEPayment;

    @FXML
    private Label LBAmountDue;

    @FXML
    private Label LBProgrameAmount;

    @FXML
    private Label LBpaymentId;

    @FXML
    private TableView<?> TBLpayment;

    @FXML
    private TextField TXTAmoutPaid;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    TherapiProgameBO therapiProgameBO = (TherapiProgameBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIPROGRAM);
    TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);
    TherapisassionBO therapisassionBO = (TherapisassionBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPISASSION);
    AlertController alertController = new AlertController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        COMtherapist.setDisable(true);
        COMprogamrId.setDisable(true);
        try {
            setPaymantId("");
            getPatientId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AddOnAction(ActionEvent event) {
        if(TXTAmoutPaid.getText().isEmpty() || COMpatientId.getValue() == null || COMprogamrId.getValue() == null || COMtherapist.getValue() == null || COMSessionId.getValue() == null){
            alertController.INFORMATIONALERT("WARNING", "All fields are required", Alert.AlertType.WARNING);
        }else {
            double amountDUE = Double.parseDouble(LBAmountDue.getText());
            double amountPaid = Double.parseDouble(TXTAmoutPaid.getText());

            try {
                boolean isAdded = paymentBO.addPayment(new PaymentsDTO(
                        LBpaymentId.getText(),
                        DATEPayment.getValue().toString(),
                        amountDUE,
                        amountPaid,
                        COMSessionId.getValue(),
                        COMpatientId.getValue(),
                        COMtherapist.getValue(),
                        COMprogamrId.getValue(),
                        LBProgrameAmount.getText()

                ));
                if (isAdded) {
                    alertController.INFORMATIONALERT("INFORMATION", "Added Successfully", Alert.AlertType.INFORMATION);
                } else {
                    alertController.INFORMATIONALERT("ERROR", "Add Failed", Alert.AlertType.ERROR);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void pationOnAction(ActionEvent event) {

        COMtherapist.getItems().clear();

        if (COMpatientId.getValue() == null) {
            alertController.INFORMATIONALERT("Select Patient", "Please Select Patient", INFORMATION);
        } else {
            ObservableList<String> list = FXCollections.observableArrayList();
            String patientId = COMpatientId.getValue();

            List<TherapisassionDTO> therapisassionDTOS = therapisassionBO.TherapistID(patientId);

            for (TherapisassionDTO dto : therapisassionDTOS) {
                list.add(dto.getTherapistId());
            }

            COMtherapist.setDisable(false);
            COMtherapist.setItems(list);
        }
    }


    @FXML
    void programOnAction(ActionEvent event) {
        if (COMtherapist.getValue() == null || COMprogamrId.getValue() == null) {
            alertController.INFORMATIONALERT("Missing Selection", "Please select both Therapist and Program", INFORMATION);
            return;
        }

        String therapistId = COMtherapist.getValue();
        String programId = COMprogamrId.getValue();

        try {
            List<TherapisassionDTO> infoList = therapisassionBO.getProgramPaymentInfo(programId);
                for(TherapisassionDTO dto : infoList){
                    LBProgrameAmount.setText(String.valueOf(dto.getProgramFee()));
                }

                ObservableList sessionID = FXCollections.observableArrayList();
                for(TherapisassionDTO dto : infoList){
                    sessionID.add(dto.getSessionid());
                }
                COMSessionId.setItems(sessionID);



        } catch (Exception e) {
            e.printStackTrace();
            alertController.INFORMATIONALERT("Error", "Failed to get program payment info", INFORMATION);
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) {

    }

    @FXML
    void sessionOnAction(ActionEvent event) {

    }

    @FXML
    void tableSelectOnaction(MouseEvent event) {
        if(TBLpayment.getSelectionModel().getSelectedItem() == null){
            alertController.INFORMATIONALERT("Select Payment","Please Select Payment", INFORMATION);
        }else {
            setPaymantId(TBLpayment.getSelectionModel().getSelectedItem().toString());
        }
    }

    @FXML
    void therapitOnAction(ActionEvent event) {

        COMprogamrId.getItems().clear();

        if (COMtherapist.getValue() == null) {
            alertController.INFORMATIONALERT("Select Therapist", "Please select a therapist", INFORMATION);
        } else {
            ObservableList<String> list = FXCollections.observableArrayList();
            String therapistId = COMtherapist.getValue();

            List<TherapistsDTO> therapisassionDTOS = therapisassionBO.getProgramsByTherapistId(therapistId);

            for (TherapistsDTO dto : therapisassionDTOS) {
                list.add(dto.getProgramId());
                System.out.println("Therapist ID: " + therapistId);
                System.out.println("Therapist Sessions: " + therapisassionDTOS.size());
                System.out.println(dto.getProgramId());
            }

            COMprogamrId.setDisable(false);
            COMprogamrId.setItems(list);
        }
    }


    void setPaymantId(String id){
        LBpaymentId.setText(paymentBO.getPaymentId(id));
    }


    public void getPatientId() throws Exception {

        COMtherapist.getItems().clear();
        COMprogamrId.getItems().clear();

        List<PatientsDTO> allPatients = patientBO.getAll();

        ObservableList<String> patientIds = FXCollections.observableArrayList();
        for (PatientsDTO patient : allPatients) {
            patientIds.add(patient.getId());
        }
        COMpatientId.setItems(patientIds);
    }
}
