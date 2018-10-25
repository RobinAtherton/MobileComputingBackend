package database;

import database.models.enums.Role;

import javax.xml.crypto.Data;
import java.sql.SQLException;

/**
 * @author Robin Atherton
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.dropTables();
        databaseManager.createPersonsTable();
        databaseManager.createSubjectsTable();
        databaseManager.createAppointmentsTable();
        databaseManager.insertPerson("s-rather@haw-landshut.de", "wabern", Role.Student);
        databaseManager.insertSubject("Mathe II", "1234");
    }
}
