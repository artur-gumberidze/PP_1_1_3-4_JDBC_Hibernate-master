package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util extends Configs {
    Connection dbConnection;

    public Connection getDbConnection () {
        try {
            String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return dbConnection;

    }



}
