package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.InvoceDAO;
import lk.ijse.gdse.entity.Invoice;
import lk.ijse.gdse.entity.Users;
import org.hibernate.Session;

import java.util.List;

public class InvoiceDAOImpl implements InvoceDAO {
    @Override
    public boolean save(Invoice invoice) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.persist(invoice);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Invoice invoice) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String t) throws Exception {
        return false;
    }

    @Override
    public List<Invoice> getAll() throws Exception {
        return List.of();
    }

    @Override
    public Users search(String name) throws Exception {
        return null;
    }
}
