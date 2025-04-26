package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse.dto.tm.PatientDTO;

import java.time.LocalDate;

public class PatientData {
    public static ObservableList<PatientDTO> getAllPatientsData() {
        ObservableList<PatientDTO> data = FXCollections.observableArrayList();

        // Static pre-defined patient data
        data.add(new PatientDTO(1001, "John Smith", "071-1234567", LocalDate.now().minusDays(2)));
        data.add(new PatientDTO(1002, "Maria Garcia", "072-9876543", LocalDate.now()));
        data.add(new PatientDTO(1003, "David Lee", "073-5551234", LocalDate.now().minusDays(10)));
        data.add(new PatientDTO(1004, "Sarah Johnson", "074-4445556", LocalDate.now().minusDays(15)));
        data.add(new PatientDTO(1005, "Michael Brown", "075-7778889", LocalDate.now().minusDays(45)));
        data.add(new PatientDTO(1006, "Lisa Chen", "076-1112223", LocalDate.now().minusDays(1)));
        data.add(new PatientDTO(1007, "James Wilson", "077-2223334", LocalDate.now().minusDays(3)));
        data.add(new PatientDTO(1008, "Emma Davis", "078-6667778", LocalDate.now().minusDays(5)));
        data.add(new PatientDTO(1009, "Robert Taylor", "079-1237894", LocalDate.now().minusDays(60)));
        data.add(new PatientDTO(1010, "Jennifer Martinez", "070-9871234", LocalDate.now().minusDays(7)));

        return data;
    }
}