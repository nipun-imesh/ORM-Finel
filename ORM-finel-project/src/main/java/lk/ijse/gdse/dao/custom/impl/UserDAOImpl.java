package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.config.FactoryConfiguration;
import lk.ijse.gdse.dao.custom.UserDAO;
import lk.ijse.gdse.entity.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public boolean save(Users users) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.persist(users);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Users users) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.merge(users);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Users users) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.remove(users);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Users> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        List<Users> users = session.createQuery("FROM Users").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }
    @Override
    public Users search(String username) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        Users user = session.createQuery("FROM Users u WHERE u.username = :username", Users.class)
                .setParameter("username", username)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public String getLastUserIdByRole(String rolePrefix) {
            Session session =  FactoryConfiguration.getInstance().getSession();
            session.beginTransaction();

            // Modify query to use LIKE with a string parameter
            String hql = "SELECT u.id FROM Users u WHERE u.id LIKE :prefix ORDER BY u.id DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("prefix", rolePrefix + "%");
            query.setMaxResults(1);

            String lastId = query.uniqueResult();
            session.getTransaction().commit();

            return lastId;

    }

}
