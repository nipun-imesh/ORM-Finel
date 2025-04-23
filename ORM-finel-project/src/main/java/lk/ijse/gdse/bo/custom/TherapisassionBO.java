package lk.ijse.gdse.bo.custom;

import lk.ijse.gdse.bo.SuperBO;
import lk.ijse.gdse.dto.PaymentsDTO;
import lk.ijse.gdse.dto.TherapisassionDTO;
import lk.ijse.gdse.dto.TherapistsDTO;

import java.util.List;

public interface TherapisassionBO extends SuperBO {
    List<TherapisassionDTO> therapiSassionGetUiData() throws Exception;
    String getPatientName(String id);

    String getTherapiSassionId(String id);

    boolean savetherapisassion(TherapisassionDTO therapisassionDTO) throws Exception;

    List<TherapisassionDTO> getAll() throws Exception;

    boolean delete(String sessionId) throws Exception;

    boolean updateTherapisassion(TherapisassionDTO therapisassionDTO) throws Exception;

    List<TherapisassionDTO> TherapistID(String paymentsDTO);

    List<TherapistsDTO> getProgramsByTherapistId(String therapistId);

    List<TherapisassionDTO> getProgramPaymentInfo(String programId);
}
