package service;

import database.DatabaseConnection;
import model.Product;
import model.Ticket;
import model.User;
import model.Vehicle;

import java.sql.SQLException;

import static util.Audit.printAction;

public class ProductService {
    DatabaseConnection dbconn;

    public ProductService() throws SQLException {
        dbconn = DatabaseConnection.getInstance();
    }

    public void insertTicket(Ticket ticket) throws SQLException {
        printAction("insertTicket");
        dbconn.insertTickets(ticket);
    }
    public void insertVehicle(Vehicle vehicle) throws SQLException {
        printAction("insertVehicle");
        dbconn.insertVehicles(vehicle);
    }

    public void updateTicketDate(Ticket ticket, String newDate) throws SQLException {
        printAction("updateTicketDate");
        dbconn.updateDate(ticket.getProductId(), newDate);
    }

    public void updateVehicleConsume(Vehicle vehicle, double newConsume) throws SQLException {
        printAction("updateVehicleConsume");
        dbconn.updateConsume(vehicle.getProductId(), newConsume);
    }

    public void deleteProductsBySeller(int userId) throws SQLException {
        printAction("deleteProductsByOwner");
        dbconn.deleteProductsBySeller(userId);
    }

    public void deleteProductsBySeller(User user) throws SQLException {
        printAction("deleteProductsByOwner");
        dbconn.deleteProductsBySeller(user.getUserId());
    }

    public void deleteOlderVehicles(int year) throws SQLException {
        printAction("deleteOlderVehicles");
        dbconn.deleteOlderVehicles(year);
    }

    public void deleteExpensiveTickets(int value) throws SQLException {
        printAction("deleteExpensiveTickets");
        dbconn.deleteExpensiveTickets(value);
    }



}
