package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Therapist_Program_Assign;
import lk.ijse.gdse.entity.Therapists;
import org.hibernate.Session;

import java.io.Serializable;

public interface TherapistDAO extends CrudDAO<Therapists> {
    String getTherapistId() throws Exception;

    Serializable savetherapist(Therapists therapists, Session session);

    Serializable saveAssociate(Therapist_Program_Assign therapyProgram, Session session);
}
