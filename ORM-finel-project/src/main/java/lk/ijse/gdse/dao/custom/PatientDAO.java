package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dto.TherapisassionDTO;
import lk.ijse.gdse.entity.Patient;

import java.util.List;


public interface PatientDAO extends CrudDAO<Patient> {
    String getPatientId() throws Exception;

    String getCurrentPatientId(String patientId);
}
