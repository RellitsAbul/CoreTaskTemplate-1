package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.cleanUsersTable();
    }
}
