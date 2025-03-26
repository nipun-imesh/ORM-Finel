package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.PatientsDTO;
import lk.ijse.gdse.dto.TherapyProgramsDTO;

import java.util.List;

public interface TherapiProgameBO extends SuperBO {
    void save(TherapyProgramsDTO therapyProgramsDTO) throws Exception;
    void delete(String id) throws Exception;
    void update(TherapyProgramsDTO therapyProgramsDTO) throws Exception;
    public List<TherapyProgramsDTO> getAll() throws Exception;
}
