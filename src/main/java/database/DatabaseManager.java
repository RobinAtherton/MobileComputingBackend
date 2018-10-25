package database;

import database.models.Person;
import database.models.enums.Role;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {

    private static final @NotNull String DATABASE_URL = "jdbc:postgresql:MobileComputing";
    private static final @NotNull String USERNAME = "postgres";
    private static final @NotNull String PASSWORD = "1234";

    private final @NotNull Connection database;

    public DatabaseManager() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        database = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void closeDatabase() throws SQLException {
        database.close();
    }

    public void dropTables() {
        dropAppointmentsTable();
        dropPersonsTable();
        dropSubjectsTable();
    }

    private void dropSubjectsTable() {
        String sql = "DROP TABLE Subjects";
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropPersonsTable() {
        String sql = "DROP TABLE Persons";
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropAppointmentsTable() {
        String sql = "DROP TABLE Appointments";
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPersonsTable() {
        String sql = "CREATE TABLE Persons(" +
                "  Email varchar(255)," +
                "  PersonPassword varchar(255)," +
                "  PersonRole varchar(255)," +
                "  Primary Key(Email)" +
                ")";
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSubjectsTable() {
        String sql = "" +
                "CREATE TABLE Subjects(" +
                "  SubjectId serial," +
                "  SubjectName varchar(255)," +
                "  SubjectPassword varchar(255)," +
                "  Primary Key(SubjectId)" +
                ")";
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAppointmentsTable() {
        String sql = "CREATE TABLE Appointments(" +
                "  AppointmentId int," +
                "  SubjectKey int," +
                "  AppointmentType int," +
                "  AppointmentTime varchar(255)," +
                "  AppointmentDate varchar(255)," +
                "  Primary Key(AppointmentId)," +
                "  Foreign Key(SubjectKey) references Subjects(SubjectId)" +
                ")";
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPerson(@NotNull String email, @NotNull String password, @NotNull Role role) {
        String sql = "INSERT INTO Persons(Email, PersonPassword, PersonRole) VALUES(?,?,?)";
        try (Connection connection = this.connect()) {
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
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
