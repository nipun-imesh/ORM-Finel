package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.PatientDAO;
import lk.ijse.gdse.entity.Patient;
import lk.ijse.gdse.entity.Users;
import org.hibernate.Session;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public boolean save(Patient patient) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.persist(patient);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Patient patient) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.merge(patient);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String patient) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.remove(patient);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Patient> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        List<Patient> patients = session.createQuery("FROM Patient").list();
        session.getTransaction().commit();
        session.close();
        return patients;
    }

    @Override
    public Users search(String name) throws Exception {
        return null;
    }


    @Override
    public String getPatientId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT p.id FROM Patient p ORDER BY p.id DESC";
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
    public String getCurrentPatientId(String patientId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT p.name FROM Patient p WHERE p.id = :id";
        String name = (String) session.createQuery(hql)
                .setParameter("id", patientId)
                .uniqueResult();

        session.getTransaction().commit();
        session.close();

        return name;
    }


}
