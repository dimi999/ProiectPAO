package service;

import database.DatabaseConnection;
import model.User;

import java.sql.SQLException;

import static util.Audit.printAction;

public class UserService {

    private DatabaseConnection dbconn;
    public UserService() throws SQLException {
        dbconn = DatabaseConnection.getInstance();
    }

    public void printUsers() throws SQLException {
        printAction("printUsers");
        dbconn.getUsers().stream().map(User::toString).forEach(System.out::println);
    }

    public void insertUser(User user) throws SQLException {
        printAction("insertUser");
        dbconn.insertUsers(user);
    }

    public void countItems(User user) {
        user.countItems();
    }

    public void updateAddress(User user, String newAddress) throws SQLException {
        printAction("updateAddress");
        dbconn.updateAddress(user.getUserId(), newAddress);
    }

    public void banUser(User user) throws SQLException {
        printAction("banUser");
        dbconn.banUser(user.getName());
    }
}
