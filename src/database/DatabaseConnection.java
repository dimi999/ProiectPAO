package database;

import model.Bid;
import model.Ticket;
import model.User;
import model.Vehicle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private final Connection connection;

    private DatabaseConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/proiectpao?allowMultiQueries=true";
        String username = "root";
        String password = "";
        connection = DriverManager.getConnection(url, username, password);
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public ArrayList <Bid> getBids() throws SQLException{
        String query = "SELECT * FROM bids";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        ArrayList<Bid> bids = new ArrayList();
        Bid dummy;
        while (rs.next()) {
            dummy = new Bid(rs.getInt(2), rs.getInt(3), rs.getInt(4));
            dummy.setBidId(rs.getInt(1));
            bids.add(dummy);
        }
        return bids;
    }

    public ArrayList <User> getUsers() throws SQLException{
        String query = "SELECT * FROM users";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        ArrayList<User> users = new ArrayList();
        User dummy;
        while (rs.next()) {
            dummy = new User(rs.getString(2), rs.getString(3), rs.getString(4));
            dummy.setUserId(rs.getInt(1));
            users.add(dummy);
        }
        return users;
    }

    public void createTables() throws SQLException, IOException {
        Path path = Path.of("dbcreation.txt");
        String query = Files.readString(path);

        Statement statement = connection.createStatement();
        statement.execute(query);
    }
    public void dropTables() throws SQLException {
        String query = "DROP TABLE bids";
        Statement statement = connection.createStatement();
        statement.execute(query);

        query = "DROP TABLE users";
        statement.execute(query);

        query = "DROP TABLE vehicles";
        statement.execute(query);

        query = "DROP TABLE tickets";
        statement.execute(query);

        query = "DROP TABLE products";
        statement.execute(query);
    }

    public void insertBid(Bid bid) throws SQLException {
        String query = String.format("INSERT INTO bids VALUES( %d, %d, %d, %d)", bid.getBidId(), bid.getAmount(), bid.getBidderId(), bid.getProductId());
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void insertUsers(User user) throws SQLException {
        String query = String.format("INSERT INTO users VALUES( %d, '%s', '%s', '%s')", user.getUserId(), user.getName(), user.getEmail(), user.getAddress());
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void insertTickets(Ticket ticket) throws SQLException {
        String query = String.format("INSERT INTO products VALUES( %d, %d, %d, %b);\n", ticket.getProductId(), ticket.getMinPrice(),
                ticket.getSeller().getUserId(), ticket.isSold()); ;
        Statement statement = connection.createStatement();
        statement.execute(query);

        query = String.format("INSERT INTO tickets VALUES( %d, '%s', '%s', %d);", ticket.getProductId(), ticket.getEventName(),
                        ticket.getEventDate(), ticket.getBoughtPrice());
        statement.execute(query);
    }

    public void insertVehicles(Vehicle vehicle) throws SQLException {
        String query = String.format("INSERT INTO products VALUES( %d, %d, %d, %b)", vehicle.getProductId(), vehicle.getMinPrice(),
                vehicle.getSeller().getUserId(), vehicle.isSold());
        Statement statement = connection.createStatement();
        statement.execute(query);

        query = String.format("INSERT INTO vehicles VALUES( %d, '%s', '%s', %d, %d, %f)", vehicle.getProductId(), vehicle.getBrand(), vehicle.getModel(),
                                                                                    vehicle.getKm(), vehicle.getYear(), vehicle.getConsume());
        statement.execute(query);
    }

    public void updateSold(int productId, boolean value) throws SQLException {
        String query = String.format("UPDATE products SET sold = %b WHERE productId = %d", value, productId);
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void updateAmount(int bidId, int value) throws SQLException {
        String query = String.format("UPDATE bids SET amount = %d WHERE productId = %d", value, bidId);
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void updateDate(int productId, String value) throws SQLException {
        String query = String.format("UPDATE tickets SET eventDate = '%s' WHERE productId = %d", value, productId);
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void updateAddress(int userId, String value) throws SQLException {
        String query = String.format("UPDATE users SET address = '%s' WHERE userId = %d", value, userId);
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void updateConsume(int productId, double value) throws SQLException {
        String query = String.format("UPDATE vehicles SET consume = %f WHERE productId = %d", value, productId);
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void deleteSmallerAmount(int value) throws SQLException {
        String query = String.format("DELETE FROM bids WHERE amount < %d", value);
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void deleteProductsBySeller(int userId) throws  SQLException {
        ArrayList <Integer> ids = getProductsBySeller(userId);

        for (int crtId : ids) {
            String query = String.format("DELETE FROM vehicles WHERE productId = %d", crtId);
            Statement statement = connection.createStatement();
            statement.execute(query);

            query = String.format("DELETE FROM tickets WHERE productId = %d", crtId);
            statement.execute(query);

            query = String.format("DELETE FROM products WHERE productId = %d", crtId);
            statement.execute(query);
        }
    }

    public ArrayList<Integer> getProductsBySeller(int userId) throws SQLException {
        String query = String.format("SELECT productId FROM products WHERE sellerId = %d", userId);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        ArrayList<Integer> res = new ArrayList();
        while (rs.next()) {
            res.add(rs.getInt(1));
        }

        return res;
    }

    public void banUser(String name) throws SQLException {
        String query = String.format("DELETE FROM users WHERE name = '%s'", name);
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public ArrayList<Integer> getOlderVehicles(int year) throws SQLException {
        String query = String.format("SELECT productId FROM vehicles WHERE year < %d", year);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        ArrayList<Integer> res = new ArrayList();
        while (rs.next()) {
            res.add(rs.getInt(1));
        }

        return res;
    }
    public void deleteOlderVehicles(int year) throws SQLException {
        ArrayList <Integer> ids = getOlderVehicles(year);

        for (int crtId : ids) {
            String query = String.format("DELETE FROM vehicles WHERE productId = %d", crtId);
            Statement statement = connection.createStatement();
            statement.execute(query);

            query = String.format("DELETE FROM products WHERE productId = %d", crtId);
            statement.execute(query);
        }
    }

    public ArrayList<Integer> getExpensiveTickets(int value) throws SQLException {
        String query = String.format("SELECT productId FROM tickets WHERE boughtPrice > %d", value);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        ArrayList<Integer> res = new ArrayList();
        while (rs.next()) {
            res.add(rs.getInt(1));
        }

        return res;
    }

    public void deleteExpensiveTickets(int value) throws  SQLException {
        ArrayList <Integer> ids = getExpensiveTickets(value);

        for (int crtId : ids) {
            String query = String.format("DELETE FROM tickets WHERE productId = %d", crtId);
            Statement statement = connection.createStatement();
            statement.execute(query);

            query = String.format("DELETE FROM products WHERE productId = %d", crtId);
            statement.execute(query);
        }
    }
}
