package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.PatientsDTO;

public interface PatientBO extends SuperBO {
    boolean save(PatientsDTO patientsDTO) throws Exception;
    boolean delete(String id) throws Exception;
}
