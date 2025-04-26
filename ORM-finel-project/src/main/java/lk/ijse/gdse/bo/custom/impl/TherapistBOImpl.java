package lk.ijse.gdse.bo.custom.impl;

import lk.ijse.gdse.bo.custom.TherapistBO;
import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.DAOFactory;
import lk.ijse.gdse.dao.custom.TherapiProgameDAO;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.dao.custom.Therepist_Progame_AssignDAO;
import lk.ijse.gdse.dto.TherapistsDTO;
import lk.ijse.gdse.entity.Therapist_Program_Assign;
import lk.ijse.gdse.entity.Therapists;
import lk.ijse.gdse.entity.Therapy_Program;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {

    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    TherapiProgameDAO therapiProgameDAO = (TherapiProgameDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIPROGAM);

    @Override
    public void save(TherapistsDTO therapistsDTO,String programId) throws Exception {

    }

    @Override
    public List<TherapistsDTO> getAll() throws Exception {
       List<TherapistsDTO> all = new ArrayList<>();
       List<Therapists> therapists = therapistDAO.getAll();
       for(Therapists therapist: therapists){
           all.add(new TherapistsDTO(therapist.getId(), therapist.getName(), therapist.getSpecialization(), therapist.getContact(), therapist.getStatus()));
       }
        return all;
    }

    @Override
    public boolean savetherapist(TherapistsDTO therapistsDTO, String programId) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Therapists therapists = new Therapists(
                therapistsDTO.getId(),
                therapistsDTO.getName(),
                therapistsDTO.getSpecialization(),
                therapistsDTO.getContact(),
                therapistsDTO.getStatus()
        );

        try {
            session.beginTransaction();

            // Save therapist first
            Serializable therapistId = therapistDAO.savetherapist(therapists, session);

            if (therapistId != null) {
                // If programId is provided, save association
                if (programId != null) {
                    Therapy_Program therapyProgram = session.get(Therapy_Program.class, programId);

                    if (therapyProgram != null) {
                        Therapist_Program_Assign assign = new Therapist_Program_Assign(therapists, therapyProgram);
                        therapistDAO.saveAssociate(assign, session);
                    }
                }

                session.getTransaction().commit();
                return true;
            } else {
                session.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(TherapistsDTO therapistsDTO, String programId) throws Exception {
      return therapistDAO.update(new Therapists(therapistsDTO.getId(), therapistsDTO.getName(), therapistsDTO.getSpecialization(), therapistsDTO.getContact(), therapistsDTO.getStatus()));
    }


    @Override
    public String getTherapistId() throws Exception {
        String lastId = therapistDAO.getTherapistId();
        if (lastId == null) {
            return "TH001";
        } else {
            int num = Integer.parseInt(lastId.substring(2));
            num++;
            return String.format("TH%03d", num);
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        return therapistDAO.delete(id);
    }
}
