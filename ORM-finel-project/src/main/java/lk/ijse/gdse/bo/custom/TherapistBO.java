package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.TherapistsDTO;

import java.util.List;

public interface TherapistBO extends SuperBO {
    void save(TherapistsDTO therapistsDTO,String programId) throws Exception;
    public String getTherapistId() throws Exception;

    boolean delete(String id) throws Exception;

    List<TherapistsDTO> getAll() throws Exception;

    boolean savetherapist(TherapistsDTO therapistsDTO, String programId) throws Exception;

    boolean update(TherapistsDTO therapistsDTO, String programId) throws Exception;

    class TherapisassionBO {
    }
}
