package database;

import database.models.enums.Role;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager databaseManager = null;
    private DataBase database;
    private Connection connection;

    private DatabaseManager() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        database = DataBase.getInstance();
        connection = database.getConnection();
    }

    public static DatabaseManager getInstance() throws ClassNotFoundException {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    /**
     * Returns the persons Role if a user with given email and password is found.
     * Else return 'invalid credentials'
     *
     * @param email
     * @param password
     * @return
     */
    public @NotNull String validateCredentials(@NotNull String email, @NotNull String password) throws SQLException {
        final String sql = "SELECT Persons.PersonRole from Persons where Email = ? and PersonPassword = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return "invalid credentials";
        }
    }

    public void insertPerson(@NotNull String email, @NotNull String password, @NotNull Role role) {
        String sql = "INSERT INTO Persons(Email, PersonPassword, PersonRole) VALUES(?,?,?)";
        try (Connection connection = this.database.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, role.toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSubject(@NotNull String name, @NotNull String password) {
        String sql = "INSERT INTO Subjects(SubjectName, SubjectPassword) VALUES(?, ?)";
        try (Connection connection = this.database.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAppointment(int subjectKey, @NotNull String appointmentType, @NotNull String appointmentTime, @NotNull String appointmentDate) {
        String sql = "INSERT INTO Appointments(SubjectKey, AppointmentType, AppointmentTime, AppointmentDate) VALUES (?, ?, ?, ?)";
        try (Connection connection = this.database.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, subjectKey);
            statement.setString(2, appointmentType);
            statement.setString(3, appointmentTime);
            statement.setString(4, appointmentDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataBase getDatabase() {
        return database;
    }
}
