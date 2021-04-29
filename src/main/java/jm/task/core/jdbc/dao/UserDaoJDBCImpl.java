package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS User " +
                            "(id INTEGER not NULL AUTO_INCREMENT, " +
                            " name VARCHAR(50) not NULL, " +
                            " lastName VARCHAR (50) not NULL, " +
                            " age INTEGER not NULL, " +
                            " PRIMARY KEY (id));");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF  EXISTS User;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO mydbtest.User (name, lastName, age)\n" +
                    "    VALUES ('" + name + "', '" + lastName + "', " + age + ");");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM mydbtest.User \n" +
                    "WHERE id=" + id + ";");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        User user = null;
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM User");
            while (rs.next()) {
                user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return userList;
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE User;");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
