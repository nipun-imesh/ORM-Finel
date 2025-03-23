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
        return false;
    }

    @Override
    public boolean delete(Patient patient) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.remove(patient);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Patient> getAll() throws Exception {
        return List.of();
    }

    @Override
    public Users search(String name) throws Exception {
        return null;
    }
}
