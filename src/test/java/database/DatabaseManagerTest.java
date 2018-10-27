package database;

import org.junit.Assert;
import org.junit.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class DatabaseManagerTest {

    @Test
    public void testValidCredentials() throws SQLException, ClassNotFoundException, ConnectException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        final String validation1 = databaseManager.validateCredentials("test", "test");
        Assert.assertEquals("Student", validation1);
        databaseManager.getDatabase().close();
    }

}