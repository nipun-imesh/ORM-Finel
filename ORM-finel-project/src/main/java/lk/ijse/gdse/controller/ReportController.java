package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.dto.tm.PatientDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ReportController implements Initializable {

    @FXML private Label idLabel;
    @FXML private TextField nameField;
    @FXML private TextField contactField;
    @FXML private DatePicker regDatePicker;

    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button resetButton;

    @FXML private TableView<PatientDTO> patientTable;
    @FXML private TableColumn<PatientDTO, Integer> idColumn;
    @FXML private TableColumn<PatientDTO, String> nameColumn;
    @FXML private TableColumn<PatientDTO, String> contactColumn;
    @FXML private TableColumn<PatientDTO, LocalDate> regDateColumn;

    private ObservableList<PatientDTO> patientList = FXCollections.observableArrayList();
    private int nextId = 1011; // Start after the last ID in PatientData (1010)

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        regDateColumn.setCellValueFactory(new PropertyValueFactory<>("regDate"));

        // Load data from PatientData
        loadPatientData();

        // Set up table selection listener
        patientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idLabel.setText(String.valueOf(newSelection.getId()));
                nameField.setText(newSelection.getName());
                contactField.setText(newSelection.getContact());
                regDatePicker.setValue(newSelection.getRegDate());
            }
        });

        // Set initial values
        resetForm();
    }

    private void loadPatientData() {
        patientList.addAll(PatientData.getAllPatientsData());
        patientTable.setItems(patientList);
    }

    @FXML
    private void handleAddAction() {
        if (validateInput()) {
            PatientDTO newPatient = new PatientDTO(
                    nextId++,
                    nameField.getText().trim(),
                    contactField.getText().trim(),
                    regDatePicker.getValue()
            );

            patientList.add(newPatient);
            resetForm();
            showAlert(Alert.AlertType.INFORMATION, "Patient Added", "Patient has been added successfully.");
        }
    }

    @FXML
    private void handleUpdateAction() {
        PatientDTO selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            showAlert(Alert.AlertType.WARNING, "Select Patient", "Please select a patient to update.");
            return;
        }

        if (validateInput()) {
            selectedPatient.setName(nameField.getText().trim());
            selectedPatient.setContact(contactField.getText().trim());
            selectedPatient.setRegDate(regDatePicker.getValue());

            patientTable.refresh();
            resetForm();
            showAlert(Alert.AlertType.INFORMATION, "Patient Updated", "Patient has been updated successfully.");
        }
    }

    @FXML
    private void handleDeleteAction() {
        PatientDTO selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            showAlert(Alert.AlertType.WARNING, "Select Patient", "Please select a patient to delete.");
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Delete");
        confirmAlert.setHeaderText("Delete Patient");
        confirmAlert.setContentText("Are you sure you want to delete this patient?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            patientList.remove(selectedPatient);
            resetForm();
            showAlert(Alert.AlertType.INFORMATION, "Patient Deleted", "Patient has been deleted successfully.");
        }
    }

    @FXML
    private void handleResetAction() {
        resetForm();
    }

    private void resetForm() {
        idLabel.setText("-----");
        nameField.clear();
        contactField.clear();
        regDatePicker.setValue(null);
        patientTable.getSelectionModel().clearSelection();
    }

    private boolean validateInput() {
        StringBuilder errorMessage = new StringBuilder();

        // Validate name
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            errorMessage.append("No valid name provided.\n");
        }

        // Validate contact (format: XXX-XXXXXXX)
        String contact = contactField.getText() == null ? "" : contactField.getText().trim();
        if (contact.isEmpty()) {
            errorMessage.append("No valid contact number provided.\n");
        } else if (!Pattern.matches("\\d{3}-\\d{7}", contact)) {
            errorMessage.append("Contact number must be in the format XXX-XXXXXXX.\n");
        }

        // Validate registration date
        if (regDatePicker.getValue() == null) {
            errorMessage.append("No valid registration date selected.\n");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", errorMessage.toString());
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}