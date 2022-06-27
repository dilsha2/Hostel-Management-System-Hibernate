package dao.Custom.impl;

import dao.Custom.UserDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws Exception {
        return false;
    }

    @Override
    public boolean update(User user) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public User find(String uid) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, uid);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<User> userList = null;
        Query sessionQuery = session.createQuery("from User");
        userList = sessionQuery.list();
        transaction.commit();
        session.close();
        return userList;
    }
}
