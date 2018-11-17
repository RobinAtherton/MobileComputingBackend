package database;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {

    private static final @NotNull String DATABASE_URL = "jdbc:postgresql:MobileComputing";
    private static final @NotNull String USERNAME = "postgres";
    private static final @NotNull String PASSWORD = "1234";

    private static DataBase dataBase = null;
    private static Connection connection;

    private DataBase() {
        connection = connect();
    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public Connection getConnection() {
        return connection;
    }

    public void dropTables() {
        dropAppointmentsTable();
        dropPersonsTable();
        dropSubjectsTable();
        dropSubscriptionsTable();
    }

    public void createTables() {
        createPersonsTable();
        createSubjectsTable();
        createAppointmentsTable();
        createSubscriptionTable();
        createUniqueSubscriptionConstraint();
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void close() throws SQLException {
        getConnection().close();
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

    private void dropSubscriptionsTable() {
        String sql = "DROP TABLE Subscriptions";
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createPersonsTable() {
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

    private void createSubjectsTable() {
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

    private void createAppointmentsTable() {
        String sql = "CREATE TABLE Appointments(" +
                "  AppointmentId serial," +
                "  SubjectKey int," +
                "  AppointmentType varchar(255)," +
                "  AppointmentTime varchar(255)," +
                "  AppointmentDate varchar(255)," +
                "  AppointmentDay varchar(255), " +
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

    private void createSubscriptionTable() {
        String sql = "CREATE TABLE Subscriptions(" +
                " Subscriber varchar(255)," +
                " SubjectName varchar(255)," +
                " foreign key (Subscriber) references Persons(Email)," +
                " foreign key (SubjectName) references Subjects(SubjectName)";
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUniqueSubscriptionConstraint() {
        String sql = "ALTER TABLE Subscriptions\n" +
                "  ADD CONSTRAINT unique_subscribes unique(Subscriber, SubjectName);";
        try (Connection connection = this.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
