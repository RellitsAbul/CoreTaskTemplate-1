package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {


    public static void main(String[] args) {

        UserService us = new UserServiceImpl();

        us.createUsersTable();
        us.saveUser("Ivan", "Ivanov", (byte) 22);
        us.saveUser("Sidor", "Sidorov", (byte) 45);
        us.saveUser("Kuzma", "Kuzmin", (byte) 12);
        us.saveUser("Sanya", "Sanin", (byte) 34);
        List<User> userList = us.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
