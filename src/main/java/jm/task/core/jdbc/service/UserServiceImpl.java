package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Const;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    Util u = new Util();
    @Override
    public void createUsersTable() {
        try {
            String update = "CREATE TABLE `kata`.`User` (\n" +
                    "            `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "            `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "    PRIMARY KEY (`id`));";
            PreparedStatement preparedStatement = u.getDbConnection().prepareStatement(update);
            preparedStatement.execute();
        } catch (SQLSyntaxErrorException e) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            String del = "DROP TABLE `kata`.`user`;";
            PreparedStatement preparedStatement = u.getDbConnection().prepareStatement(del);
            preparedStatement.execute();
        } catch (SQLSyntaxErrorException e) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + "," + Const.USER_LASTNAME +
                "," + Const.USER_AGE + ")" +
                "VALUES(?,?,?)";

        PreparedStatement prSt = null;
        try {
            prSt = u.getDbConnection().prepareStatement(insert);
            prSt.setString(1,name);
            prSt.setString(2,lastName);
            prSt.setInt(3,age);
            prSt.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    @Override
    public void removeUserById(long id) {
        String delete = "DELETE FROM `kata`.`user` WHERE (`id` = '" + id + "');";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = u.getDbConnection().prepareStatement(delete);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try {
            List<User> userList = new ArrayList<>();
            ResultSet resultSet = null;
            String getUser = "SELECT * FROM kata.user;";
            PreparedStatement preparedStatement = null;
            preparedStatement = u.getDbConnection().prepareStatement(getUser);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastName"), (byte) resultSet.getInt("age")));
            }
            return userList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    @Override
    public void cleanUsersTable() {
        String delete = "TRUNCATE TABLE kata.user;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = u.getDbConnection().prepareStatement(delete);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
