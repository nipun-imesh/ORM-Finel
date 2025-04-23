package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.TherapyProgramsDTO;

import java.util.List;

public interface TherapiProgameBO extends SuperBO {
    boolean save(TherapyProgramsDTO therapyProgramsDTO) throws Exception;
    boolean delete(String id) throws Exception;
    boolean update(TherapyProgramsDTO therapyProgramsDTO) throws Exception;
    public List<TherapyProgramsDTO> getAll() throws Exception;

   TherapyProgramsDTO getProgameNameAndFee(String value);

    String getProgameId(String id);
}
