package database;

import database.models.Subject;
import database.models.enums.Role;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rest.data.Subjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {


    private @NotNull
    static DatabaseManager databaseManager = null;
    private @NotNull
    DataBase database;
    private @NotNull
    Connection connection;

    private DatabaseManager() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        database = DataBase.getInstance();
        connection = database.getConnection();
    }

    public static @Nullable
    DatabaseManager getInstance() throws ClassNotFoundException {
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
    public @NotNull
    String validateCredentials(@NotNull String email, @NotNull String password) throws SQLException {
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

    public @NotNull
    boolean validate(@NotNull String email, @NotNull String password) throws SQLException {
        final String sql = "SELECT Persons.PersonRole from Persons where Email = ? and PersonPassword = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    public Subject getSubject(@NotNull int subjectId) throws SQLException {
        final String sql = "SELECT Subjects from Subjects where SubjectId = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, subjectId);
        final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString(1));
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            return new Subject(id, name, password);
        }
        return null;
    }

    public Subjects getAllSubjects(@NotNull String email, @NotNull String password) throws SQLException {
        if (validate(email, password)) {
            final String sql = "SELECT * from Subjects;";
            PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            List<Subject> subjects = new ArrayList<>();
            int i = 0;
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString(1));
                String subjectName = resultSet.getString(2);
                String subjectPassword = resultSet.getString(3);
                subjects.add(new Subject(id, subjectName, subjectPassword));
            }
            final Subject[] subjectArray = new Subject[subjects.size()];
            subjects.toArray(subjectArray);
            return new Subjects(subjectArray);
        }
        throw new SQLException("User not validated");
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

    public void subscribe(final @NotNull String studentMail, final @NotNull String password, final @NotNull String subjectName)
            throws SQLException {
        final String role = validateCredentials(studentMail, password);
        if (!role.equals("student")) {
            throw new IllegalArgumentException("subscriber is not of role student");
        }
        try (Connection connection = this.database.connect()) {
            PreparedStatement statement = connection.prepareStatement("insert into Subscription values(?, ?);");
            statement.setString(1, studentMail);
            statement.setString(2, subjectName);
            statement.execute();
        }

    }

    public void unsubscribe(final @NotNull String studentMail, final @NotNull String password, final @NotNull String subjectName)
            throws SQLException {
        final String role = validateCredentials(studentMail, password);
        if (!role.equals("student")) {
            throw new IllegalArgumentException("subscriber is not of role student");
        }
        try (Connection connection = this.database.connect()) {
            PreparedStatement statement =
                    connection.prepareStatement("delete from Subscriptions where Subscriber = ? and Subject = ?");
            statement.setString(1, studentMail);
            statement.setString(2, subjectName);
            statement.execute();
        }
    }

    public @NotNull Subjects getSubscribedSubjectsForStudent(final @NotNull String studentMail) throws SQLException {
        final ArrayList<Subject> subjects = new ArrayList<>();
        final String sql =
                "SELECT Subscription.SubjectName, from (Subscriptions join Subject on Subscriptions.SubjectName = Subject.SubjectName) where Subscriber = ?;";
        try (Connection connection = connectToDatabase()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentMail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final int subjectId = resultSet.getInt(1);
                final String subjectName = resultSet.getString(2);
                final String subjectPassword = resultSet.getString(3);
                final Subject subject = new Subject(subjectId, subjectName, subjectPassword);
                subjects.add(subject);
            }
            final Subject[] subjectArray = new Subject[subjects.size()];
            subjects.toArray(subjectArray);
            return new Subjects(subjectArray);
        }
    }


    public @NotNull
    DataBase getDatabase() {
        return database;
    }

    private Connection connectToDatabase() {
        return getDatabase().connect();
    }
}
