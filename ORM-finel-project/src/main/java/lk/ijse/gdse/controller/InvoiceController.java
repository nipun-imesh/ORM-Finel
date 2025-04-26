package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.InvoiceBO;
import lk.ijse.gdse.bo.custom.PatientBO;
import lk.ijse.gdse.bo.custom.PaymentBO;
import lk.ijse.gdse.bo.custom.TherapisassionBO;
import lk.ijse.gdse.dto.InvoiceDTO;
import lk.ijse.gdse.dto.PaymentsDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    @FXML
    private AnchorPane InvoicesAnchrpain;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnPrint;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblDate;

    @FXML
    private Label txtDate;

    @FXML
    private Label txtFee;

    @FXML
    private Label txtPatientId;

    @FXML
    private Label txtPatientName;

    @FXML
    private Label txtPaymentDate;

    @FXML
    private Label txtProgram;

    @FXML
    private Label txtSessionDate;

    @FXML
    private Label txtSessionId;

    @FXML
    private Label txtStatus;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    TherapisassionBO therapisassionBO = (TherapisassionBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPISASSION);
    AlertController alertController = new AlertController();
    InvoiceBO invoiceBO = (InvoiceBO) BOFactory.getInstance().getBO(BOFactory.BOType.INVOICE);




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SetInvoiceData();
    }

    @FXML
    void handlePrint(ActionEvent event) {

    }

    @FXML
    void handleSave(ActionEvent event) {
        String patientId = txtPatientId.getText();
        String patientName = txtPatientName.getText();
        String programId = txtProgram.getText();
        String sessionId = txtSessionId.getText();
        String sessionDate = txtSessionDate.getText();
        String paymentDate = txtPaymentDate.getText();
        double fee = Double.parseDouble(txtFee.getText());
        String payDate = txtDate.getText();

        if (patientId.isEmpty() || patientName.isEmpty() || programId.isEmpty() || sessionId.isEmpty() || sessionDate.isEmpty() || paymentDate.isEmpty() || fee == 0 || payDate.isEmpty()) {
            alertController.INFORMATIONALERT("WARNING", "All fields are required", Alert.AlertType.WARNING);
            return;
        }

        try {
            invoiceBO.save(new InvoiceDTO(patientId, patientName, programId, sessionId, sessionDate, paymentDate, fee, payDate));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText(null);
            alert.setContentText("Saved Successfully.\nDo you want to close this window?");


            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getScene().getStylesheets().add(getClass().getResource("/css/alert-style.css").toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    void SetInvoiceData(){
        try {
            List<PaymentsDTO> payments = paymentBO.getAll();
            if(payments.size() > 0 ){
                for(PaymentsDTO payment : payments){
                    txtDate.setText(LocalDate.now().toString());
                    txtPatientId.setText(payment.getPatientId());
                    txtPatientName.setText(patientBO.getCurrentPatientId(payment.getPatientId()));
                    txtProgram.setText(payment.getProgramId());
                    txtSessionId.setText(payment.getSessionId());
                    txtSessionDate.setText(payment.getPaymentDate());
                    txtFee.setText(payment.getProgramAmount());
                    txtPaymentDate.setText(payment.getPaymentDate());

                }
            }else {
                alertController.INFORMATIONALERT("INFORMATION", "No Data Found", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
