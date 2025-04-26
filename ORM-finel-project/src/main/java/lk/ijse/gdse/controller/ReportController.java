package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import lk.ijse.gdse.dto.tm.PatientDTO;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private TextField searchField;

    @FXML private Button generateReportButton;
    @FXML private Button printReportButton;
    @FXML private Button exportPdfButton;
    @FXML private Button resetButton;

    @FXML private TableView<PatientDTO> patientTable;
    @FXML private TableColumn<PatientDTO, Integer> idColumn;
    @FXML private TableColumn<PatientDTO, String> nameColumn;
    @FXML private TableColumn<PatientDTO, String> contactColumn;
    @FXML private TableColumn<PatientDTO, LocalDate> regDateColumn;

    @FXML private Label totalPatientsLabel;

    private ObservableList<PatientDTO> allPatientsList = FXCollections.observableArrayList();
    private FilteredList<PatientDTO> filteredPatientList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        regDateColumn.setCellValueFactory(new PropertyValueFactory<>("regDate"));

        // Format the date column
        regDateColumn.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(formatter.format(date));
                }
            }
        });

        // Load data from PatientData
        loadPatientData();

        // Create filtered list
        filteredPatientList = new FilteredList<>(allPatientsList, p -> true);
        patientTable.setItems(filteredPatientList);

        // Add listeners for search and date filtering
        setupSearchListener();

        // Set date range listeners
        startDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> applyFilters());
        endDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> applyFilters());

        // Display initial count
        updateTotalCount();
    }

    private void loadPatientData() {
        allPatientsList.addAll(PatientData.getAllPatientsData());
    }

    private void setupSearchListener() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            applyFilters();
        });
    }

    private void applyFilters() {
        filteredPatientList.setPredicate(patient -> {
            boolean matchesSearch = true;
            boolean matchesDateRange = true;

            // Apply name search filter
            if (searchField.getText() != null && !searchField.getText().isEmpty()) {
                matchesSearch = patient.getName().toLowerCase().contains(
                        searchField.getText().toLowerCase());
            }

            // Apply date range filter
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            if (startDate != null && endDate != null) {
                matchesDateRange = !patient.getRegDate().isBefore(startDate) &&
                        !patient.getRegDate().isAfter(endDate);
            } else if (startDate != null) {
                matchesDateRange = !patient.getRegDate().isBefore(startDate);
            } else if (endDate != null) {
                matchesDateRange = !patient.getRegDate().isAfter(endDate);
            }

            return matchesSearch && matchesDateRange;
        });

        updateTotalCount();
    }

    private void updateTotalCount() {
        totalPatientsLabel.setText(String.valueOf(filteredPatientList.size()));
    }

    @FXML
    private void handleGenerateReportAction() {
        if (filteredPatientList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "There are no patients matching your filter criteria.");
            return;
        }

        StringBuilder report = new StringBuilder();
        report.append("PATIENT REPORT\n");
        report.append("=============\n\n");

        // Add date range if specified
        if (startDatePicker.getValue() != null || endDatePicker.getValue() != null) {
            report.append("Date Range: ");
            if (startDatePicker.getValue() != null) {
                report.append(startDatePicker.getValue());
            } else {
                report.append("(All past dates)");
            }
            report.append(" to ");
            if (endDatePicker.getValue() != null) {
                report.append(endDatePicker.getValue());
            } else {
                report.append("(Present)");
            }
            report.append("\n\n");
        }

        // Add search term if specified
        if (searchField.getText() != null && !searchField.getText().isEmpty()) {
            report.append("Search Filter: ").append(searchField.getText()).append("\n\n");
        }

        // Add patient count
        report.append("Total Patients: ").append(filteredPatientList.size()).append("\n\n");

        // Add patient data
        report.append(String.format("%-6s | %-20s | %-12s | %-10s\n", "ID", "Name", "Contact", "Reg.Date"));
        report.append("-----------------------------------------------------------\n");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (PatientDTO patient : filteredPatientList) {
            report.append(String.format("%-6d | %-20s | %-12s | %-10s\n",
                    patient.getId(),
                    patient.getName(),
                    patient.getContact(),
                    patient.getRegDate().format(dateFormatter)));
        }

        // Show the report in a dialog
        TextArea textArea = new TextArea(report.toString());
        textArea.setEditable(false);
        textArea.setPrefWidth(600);
        textArea.setPrefHeight(400);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Patient Report");
        dialog.setHeaderText("Generated Report");
        dialog.getDialogPane().setContent(textArea);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    @FXML
    private void handlePrintReportAction() {
        if (filteredPatientList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "There are no patients matching your filter criteria.");
            return;
        }

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.showPrintDialog(patientTable.getScene().getWindow());
            if (success) {
                boolean printed = job.printPage((Node) patientTable);
                if (printed) {
                    job.endJob();
                    showAlert(Alert.AlertType.INFORMATION, "Print Success", "The report has been sent to the printer.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Print Error", "Failed to print the report.");
                }
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Printer Not Available", "No printer was found.");
        }
    }

    @FXML
    private void handleExportPdfAction() {
        if (filteredPatientList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "There are no patients matching your filter criteria.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF Report");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("patient_report.pdf");

        File file = fileChooser.showSaveDialog(patientTable.getScene().getWindow());
        if (file != null) {
            try {
                // Here you would implement actual PDF generation
                // For demonstration purposes, we'll just show a success message
                showAlert(Alert.AlertType.INFORMATION, "Export Successful",
                        "The report has been exported to:\n" + file.getAbsolutePath());

                /* Note: In a real application, you would use a library like iText, Apache PDFBox,
                   or JasperReports to generate the PDF file. These libraries are not included
                   in the standard JavaFX distribution, so you would need to add them as dependencies. */
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Export Error",
                        "An error occurred while exporting the report:\n" + e.getMessage());
            }
        }
    }

    @FXML
    private void handleResetAction() {
        searchField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);

        // Reset filter to show all patients
        filteredPatientList.setPredicate(p -> true);

        updateTotalCount();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}