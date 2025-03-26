package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.TherapiProgameDAO;
import lk.ijse.gdse.entity.Therapy_Program;
import lk.ijse.gdse.entity.Users;
import org.hibernate.Session;

import java.util.List;

public class TherapiPragameDAOImpl implements TherapiProgameDAO {

    @Override
    public boolean save(Therapy_Program therapyProgram) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.persist(therapyProgram);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Therapy_Program therapyProgram) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.merge(therapyProgram);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Therapy_Program therapyProgram) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.remove(therapyProgram);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Therapy_Program> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        List<Therapy_Program> therapyPrograms = session.createQuery("FROM Therapy_Program", Therapy_Program.class).list();
        session.getTransaction().commit();
        session.close();
        return therapyPrograms;
    }

    @Override
    public Users search(String name) throws Exception {
        return null;
    }

    @Override
    public Therapy_Program get(String programId) {
        return null;
    }
}
