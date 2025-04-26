package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.PaymentDAO;
import lk.ijse.gdse.entity.Payments;
import lk.ijse.gdse.entity.Users;
import org.hibernate.Session;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payments payments) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.persist(payments);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Payments payments) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String payments) throws Exception {
       Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.remove(payments);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Payments> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        List<Payments> payments = session.createQuery("FROM Payments ORDER BY id DESC", Payments.class)
                .list();

        session.getTransaction().commit();
        session.close();

        return payments;
    }



    @Override
    public Users search(String name) throws Exception {
        return null;
    }

    @Override
    public String getId(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT p.id FROM Payments p ORDER BY p.id DESC";
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
    public Object getAmountDueByProgramId(String programId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT p.amountDUE FROM Payments p WHERE p.programId = :programId ORDER BY p.id DESC";
        List<Double> amounts = session.createQuery(hql).setParameter("programId", programId).setMaxResults(1).list();

        session.getTransaction().commit();
        session.close();

        if (amounts.isEmpty()) {
            return null;
        } else {
            return amounts.get(0);
        }
    }


}
