package database;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseManagerTest {

    @Test
    public void connect() throws SQLException, ClassNotFoundException {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.closeDatabase();
    }
}