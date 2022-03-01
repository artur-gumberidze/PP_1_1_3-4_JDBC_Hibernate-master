package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl u = new UserDaoHibernateImpl();
        u.createUsersTable();
        u.saveUser("Lena","Kuka", (byte) 18);
        u.saveUser("Dima","Sterling", (byte) 19);
        u.saveUser("Katya","Domn", (byte) 20);
        u.saveUser("Vladykin","Vladykin", (byte) 21);
        List<User> a = u.getAllUsers();
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
        u.cleanUsersTable();
        u.dropUsersTable();



    }
}
