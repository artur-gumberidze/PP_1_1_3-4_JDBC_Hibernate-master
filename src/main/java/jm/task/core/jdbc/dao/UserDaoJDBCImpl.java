package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.dbConnection;
import static jm.task.core.jdbc.util.Util.getDbConnection;

public class UserDaoJDBCImpl implements UserDao {
    public static final String USER_TABLE = "user";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_LASTNAME = "lastName";
    public static final String USER_AGE = "age";
    private List<User> userList;

    @Override
    public void createUsersTable() {
        String update = "CREATE TABLE `kata`.`User` (\n" +
                "            `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "            `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "    PRIMARY KEY (`id`));";
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(update)){
            preparedStatement.execute();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String del = "DROP TABLE IF EXISTS `kata`.`user`";
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(del)) {
            preparedStatement.execute();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO " + USER_TABLE + "(" +USER_NAME + "," + USER_LASTNAME +
                "," + USER_AGE + ")" +
                "VALUES(?,?,?)";
        try (PreparedStatement prSt = getDbConnection().prepareStatement(insert)){
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
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete)){
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        String getUser = "SELECT * FROM kata.user";
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(getUser)){
            ResultSet resultSet = preparedStatement.executeQuery();;
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastName"), (byte) resultSet.getInt("age")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String delete = "TRUNCATE TABLE kata.user;";
        try (PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
