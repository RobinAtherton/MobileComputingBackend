package database;

import database.models.Appointment;
import database.models.Subject;
import database.models.enums.AppointmentType;
import database.models.enums.Role;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rest.data.jsonmodels.Appointments;
import rest.data.jsonmodels.Subjects;

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

    public void insertAppointment(@NotNull String subjectName, @NotNull String appointmentType, @NotNull String appointmentDate, @NotNull String appointmentDay, @NotNull String timeSlot) {
        String sql = "INSERT INTO Appointments(SubjectId, AppointmentType, AppointmentDate, AppointmentDay, TimeSlot, Ordinality) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = this.database.connect()) {
            int subjectId = getSubjectIdForName(subjectName, connection);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, subjectId);
            statement.setString(2, appointmentType);
            statement.setString(3, appointmentDate);
            statement.setString(4, appointmentDay);
            statement.setString(5, timeSlot);
            statement.setInt(6, assertOrdinality(timeSlot));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int assertOrdinality(@NotNull final String timeSlot) {
        if (timeSlot.equals("8:45 - 10:15")) {
            return 0;
        } else if (timeSlot.equals("10:30 - 12:00")){
            return 1;
        } else if (timeSlot.equals("12:50 - 14:20")) {
            return 2;
        } else if (timeSlot.equals("14:30 - 16:00")) {
            return 3;
        } else if (timeSlot.equals("16:10 - 17:40")) {
            return 4;
        }
        return -1;
    }

    public void subscribe(final @NotNull String studentMail, final @NotNull String password, final String subjectName)
            throws SQLException {
        final String role = validateCredentials(studentMail, password);
        if (!role.equals("Student")) {
            throw new IllegalArgumentException("subscriber is not of role Student");
        }
        try (Connection connection = this.database.connect()) {
            int subjectId = getSubjectIdForName(subjectName, connection);
            PreparedStatement statement = connection.prepareStatement("insert into Subscriptions values(?, ?, ?);");
            statement.setInt(1, subjectId);
            statement.setString(2, studentMail);
            statement.setString(3, subjectName);
            statement.execute();
        }

    }

    private int getSubjectIdForName(String subjectName, Connection connection) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("select SubjectId from Subjects where SubjectName = ?;");
        sql.setString(1, subjectName);
        ResultSet resultSet = sql.executeQuery();
        resultSet.next();
        return resultSet.getInt("SubjectId");
    }

    public void unsubscribe(final @NotNull String studentMail, final @NotNull String password, final String subjectName)
            throws SQLException {
        final String role = validateCredentials(studentMail, password);
        if (!role.equals("Student")) {
            throw new IllegalArgumentException("subscriber is not of role student");
        }
        try (Connection connection = this.database.connect()) {
            int subjectId = getSubjectIdForName(subjectName, connection);
            PreparedStatement statement =
                    connection.prepareStatement("delete from Subscriptions where Subscriber = ? and SubscriptionId = ?");
            statement.setString(1, studentMail);
            statement.setInt(2, subjectId);
            statement.execute();
        }
    }

    public @NotNull
    Subjects getSubscribedSubjectsForStudent(final @NotNull String studentMail) throws SQLException {
        final ArrayList<Subject> subjects = new ArrayList<>();
        final String sql =
                "SELECT Subjects.SubjectId, Subjects.SubjectName, Subjects.SubjectPassword from (Subscriptions join Subjects on Subscriptions.SubscriptionId = Subjects.SubjectId) where Subscriptions.Subscriber = ?;";
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

    public @NotNull Appointments getAppointmentsForSubscribedSubjects(final @NotNull String studentMail) {
        final ArrayList<Appointment> appointments = new ArrayList<>();
        final String sql = "SELECT Appointments.AppointmentId, Subjects.SubjectName, Appointments.AppointmentType, Appointments.AppointmentDate,Appointments.AppointmentDay, Appointments.TimeSlot, Appointments.Ordinality \n" +
                "FROM Appointments \n" +
                "JOIN Subjects on Subjects.SubjectId = Appointments.SubjectId \n" +
                "JOIN Subscriptions on Subscriptions.SubscriptionId = Appointments.SubjectId \n" +
                "WHERE Subscriptions.Subscriber = ?";
        try (Connection connection = this.database.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentMail);
            ResultSet resultSet = statement.executeQuery();
            extractAppointments(appointments, resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        final Appointment[] appointmentArray = new Appointment[appointments.size()];
        appointments.toArray(appointmentArray);
        return new Appointments(appointmentArray);
    }


    private void extractAppointments(ArrayList<Appointment> appointments, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int appointmentId = resultSet.getInt(1);
            String subjectName = resultSet.getString(2);
            AppointmentType appointmentType;
            appointmentType = convertAppointmentType(resultSet);
            String appointmentDate = resultSet.getString(4);
            String appointmentDay = resultSet.getString(5);
            String timeSlot = resultSet.getString(6);
            int ordinality = resultSet.getInt(7);
            Appointment appointment = new Appointment(appointmentId, subjectName, appointmentType, appointmentDate, appointmentDay, timeSlot, ordinality);
            appointments.add(appointment);
        }
    }


    @NotNull
    private AppointmentType convertAppointmentType(ResultSet resultSet) throws SQLException {
        AppointmentType appointmentType;
        if (resultSet.getString(3).equals("Once")) {
            appointmentType = AppointmentType.Once;
        } else if (resultSet.getString(3).equals("Weekly")) {
            appointmentType = AppointmentType.Weekly;
        } else if (resultSet.getString(3).equals("Biweekly")) {
            appointmentType = AppointmentType.Biweekly;
        } else {
            appointmentType = AppointmentType.Once;
        }
        return appointmentType;
    }


    public @NotNull
    DataBase getDatabase() {
        return database;
    }

    private Connection connectToDatabase() {
        return getDatabase().connect();
    }
}
