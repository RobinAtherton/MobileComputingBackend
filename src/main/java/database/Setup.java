package database;

import database.models.enums.Role;

import javax.xml.crypto.Data;
import java.sql.SQLException;

/**
 * @author Robin Atherton
 */
public class Setup {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().dropTables();
        databaseManager.getDatabase().createTables();
        databaseManager.insertPerson("s-rather@haw-landshut.de", "1234", Role.Student);
        databaseManager.insertPerson("test", "test", Role.Student);
        databaseManager.insertPerson("testL", "testL", Role.Lecturer);
        databaseManager.insertPerson("testA", "testA", Role.Admin);
        databaseManager.insertSubject("Mathe II", "1234");
        databaseManager.insertSubject("Mathe I", "123");
        databaseManager.insertSubject("Vertiefung Datenbanken", "1234");
        databaseManager.insertSubject("Algorithmen und Datenstrukturen", "1235");
        databaseManager.insertSubject("Numerik", "1234");
        databaseManager.insertSubject("Bildverarbeitung", "12354");
        databaseManager.insertSubject("Bildverstehen", "1234");
        databaseManager.insertSubject("Datenbanken", "1234");
        databaseManager.insertSubject("Compiler", "1234");
        databaseManager.insertSubject("Programmieren I", "1234");
        databaseManager.insertSubject("Programmieren II", "1234");
        databaseManager.insertSubject("Programmieren III", "1234");
        databaseManager.insertAppointment("Mathe I", "Once", "90", "19.11.2018", "Mon", "8:45 - 10:15");
        databaseManager.insertAppointment("Mathe I", "Once", "90", "20.11.2018", "Tue", "8:45 - 10:15");
        databaseManager.subscribe("test", "test", "Mathe I");

    }
}
