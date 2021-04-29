package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, " +
                    "age TINYINT NOT NULL)";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS user";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = (List<User>) session.createQuery("FROM User").list();
        }
        return user;


    }

    @Override
    public void cleanUsersTable() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM User";
            session.createQuery(sql).executeUpdate();
            transaction.commit();
        }
    }
}
