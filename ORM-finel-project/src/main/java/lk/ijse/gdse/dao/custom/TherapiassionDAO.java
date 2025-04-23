package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.dto.PaymentsDTO;
import lk.ijse.gdse.entity.Payments;
import lk.ijse.gdse.entity.Therapists;
import lk.ijse.gdse.entity.TherapySessions;

import java.util.List;

public interface TherapiassionDAO extends CrudDAO<TherapySessions> {
    List<TherapySessions> getTherapistId(String therapistId);
    List<TherapySessions> getTherapuUiData();

    String getPatientName(String id);

    String getId(String id);

     List<String> getProgramsByTherapistId(String therapistId);

    List<TherapySessions> getProgramPaymentInfo(String programId);
}
