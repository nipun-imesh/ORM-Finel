package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.PatientsDTO;
import lk.ijse.gdse.dto.UsersDTO;

import java.util.List;

public interface PatientBO extends SuperBO {
    boolean save(PatientsDTO patientsDTO) throws Exception;
    boolean delete(String id) throws Exception;
    boolean update(PatientsDTO patientsDTO) throws Exception;
    List<PatientsDTO> getAll() throws Exception;
    String getPatientId() throws Exception;

}
