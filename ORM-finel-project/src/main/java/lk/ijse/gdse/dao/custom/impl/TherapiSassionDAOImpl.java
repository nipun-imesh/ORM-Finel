package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.TherapiassionDAO;
import lk.ijse.gdse.dto.PaymentsDTO;
import lk.ijse.gdse.entity.Payments;
import lk.ijse.gdse.entity.Therapists;
import lk.ijse.gdse.entity.TherapySessions;
import lk.ijse.gdse.entity.Users;
import org.hibernate.Session;

import java.util.List;

public class TherapiSassionDAOImpl implements TherapiassionDAO {
    @Override
    public boolean save(TherapySessions therapySessions) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.persist(therapySessions);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(TherapySessions therapySessions) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.merge(therapySessions);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(TherapySessions therapySessions) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.remove(therapySessions);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<TherapySessions> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        List<TherapySessions> therapySessions = session.createQuery("FROM TherapySessions").list();
        session.getTransaction().commit();
        session.close();
        return therapySessions;
    }

    @Override
    public Users search(String name) throws Exception {
        return null;
    }


    @Override
    public List<TherapySessions> getTherapistId(String patientId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT t FROM TherapySessions t WHERE t.patientId = :patientId";
        List<TherapySessions> list = session.createQuery(hql)
                .setParameter("patientId", patientId)
                .list();

        session.getTransaction().commit();
        session.close();
        return list;
    }


    @Override
    public List<TherapySessions> getTherapuUiData() {

            throw new RuntimeException("Failed to fetch therapy data");
        }

    @Override
    public String getPatientName(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        // Query to check if a patient exists with the provided ID and fetch the name
        String patientName = (String) session.createQuery("SELECT p.name FROM Patient p WHERE p.id = :id")
                .setParameter("id", id)
                .uniqueResult();

        session.getTransaction().commit();
        session.close();

        return patientName;
    }

    @Override
    public String getId(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT p.id FROM TherapySessions p ORDER BY p.id DESC";
        List<String> ids = session.createQuery(hql).setMaxResults(1).list();

        session.getTransaction().commit();
        session.close();

        if (ids.isEmpty()) {
            return null;
        } else {
            return ids.get(0);
        }
    }

    @Override
    public List<String> getProgramsByTherapistId(String therapistId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT ts.programId FROM TherapySessions ts WHERE ts.therapistId = :therapistId";
        List<String> programIds = session.createQuery(hql, String.class)
                .setParameter("therapistId", therapistId)
                .list();

        session.getTransaction().commit();
        session.close();
        System.out.println(programIds + " <-- Program IDs for therapist: " + therapistId);
        return programIds;
    }

    @Override
    public List<TherapySessions> getProgramPaymentInfo( String programId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "FROM TherapySessions WHERE programId = :programId";

        List<TherapySessions> payments = session.createQuery(hql, TherapySessions.class)
                .setParameter("programId", programId)
                .list();

        session.getTransaction().commit();
        session.close();
        return payments;
    }
}



