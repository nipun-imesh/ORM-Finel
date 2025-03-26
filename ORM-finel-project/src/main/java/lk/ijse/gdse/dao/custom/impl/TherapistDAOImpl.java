package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.TherapistDAO;
import lk.ijse.gdse.entity.Therapist_Program_Assign;
import lk.ijse.gdse.entity.Therapists;
import lk.ijse.gdse.entity.Users;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {

    @Override
    public String getTherapistId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT p.id FROM Therapists p ORDER BY p.id DESC";
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
    public Serializable savetherapist(Therapists therapists, Session session) {
        try {
            return (Serializable) session.save(therapists);
        } catch (Exception e) {
            throw new RuntimeException("Therapist Save Error: " + e.getMessage());
        }
    }

    @Override
    public Serializable saveAssociate(Therapist_Program_Assign assign, Session session) {
        try {
            return (Serializable) session.save(assign);
        } catch (Exception e) {
            throw new RuntimeException("Assign Save Error: " + e.getMessage());
        }
    }

    @Override
    public boolean save(Therapists therapists) throws Exception {
        return false;
    }

    @Override
    public boolean update(Therapists therapists) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Therapists therapists) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.remove(therapists);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Therapists> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        List<Therapists> therapists = session.createQuery("FROM Therapists").list();
        session.getTransaction().commit();
        session.close();
        return therapists;
    }

    @Override
    public Users search(String name) throws Exception {
        return null;
    }
}
