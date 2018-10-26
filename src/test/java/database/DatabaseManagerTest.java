package database;

import org.junit.Assert;
import org.junit.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class DatabaseManagerTest {

    @Test
    public void testValidCredentials() throws SQLException, ClassNotFoundException, ConnectException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        final String validation1 = databaseManager.validateCredentials("s-rather@haw-landshut.de", "1234");
        Assert.assertEquals("Student", validation1);
        databaseManager.getDatabase().close();
    }

}