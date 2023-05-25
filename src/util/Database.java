package util;

import database.DatabaseConnection;
import exception.DatabaseConnectionFailed;

import java.io.IOException;
import java.sql.SQLException;

import static util.Audit.printAction;

public class Database {
    private static DatabaseConnection dbconn;

    static {
        try {
            dbconn = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            throw new DatabaseConnectionFailed("Database connection failed");
        }
    }

    private Database() {
    }

    public static void dropTables() throws SQLException {
        printAction("dropTables");
        dbconn.dropTables();
    }

    public static void createTables() throws SQLException, IOException {
        printAction("createTables");
        dbconn.createTables();
    }
}
