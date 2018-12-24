package database;

import database.models.enums.Role;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rest.data.jsonmodels.Appointments;

import javax.xml.crypto.Data;
import java.net.ConnectException;
import java.sql.SQLException;

public class DatabaseManagerTest {

    private DatabaseManager databaseManager;

    @Before
    public void setup() throws ClassNotFoundException, SQLException, IllegalAccessException {
        databaseManager = DatabaseManager.getInstance();
        databaseManager.insertPerson("TestStudent", "test", Role.Student);
        databaseManager.deleteSubject("testL", "test", "TestSubject");
        databaseManager.insertSubject("testL", "test", "TestSubject", "1234");
        databaseManager.insertAppointment("TestSubject", "einmalig", "17.06.2018", "Mon", "8:45 - 10:15");
    }

    @Test
    public void testValidCredentials() throws SQLException, ClassNotFoundException, ConnectException {
        final String validation1 = databaseManager.validateCredentials("test", "test");
        Assert.assertEquals("Student", validation1);
    }

    @Test
    public void testValidSubjects() throws ClassNotFoundException, SQLException {
        final String validation1 = databaseManager.getSubjectName("TestSubject");
        Assert.assertEquals("TestSubject", validation1);
    }

    @After
    public void close() throws ClassNotFoundException, SQLException, IllegalAccessException {
        databaseManager = DatabaseManager.getInstance();
        databaseManager.deleteSubject("testL", "test", "TestSubject");
    }



}