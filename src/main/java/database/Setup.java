package database;

import database.models.enums.Role;

import javax.xml.crypto.Data;
import java.sql.SQLException;

/**
 * @author Robin Atherton
 */
public class Setup {

    public static void main(String[] args) throws ClassNotFoundException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().dropTables();
        databaseManager.getDatabase().createTables();
        databaseManager.insertPerson("s-rather@haw-landshut.de", "1234", Role.Student);
        databaseManager.insertSubject("Mathe II", "1234");
        databaseManager.insertSubject("Mathe I", "123");
    }
}
