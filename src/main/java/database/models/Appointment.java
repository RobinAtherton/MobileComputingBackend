package database.models;

import database.models.enums.AppointmentType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Robin Atherton
 */
public class Appointment {

    private int id;
    private String subjectName;
    private @NotNull AppointmentType type;
    private @NotNull String appointmentDate;
    private @NotNull String appointmentDuration;
    private @NotNull String appointmentDay;

    public Appointment(int id, String subjectName, @NotNull AppointmentType type, @NotNull String appointmentDate, @NotNull String appointmentDuration, @NotNull String appointmentDay) {
        this.id = id;
        this.subjectName = subjectName;
        this.type = type;
        this.appointmentDate = appointmentDate;
        this.appointmentDuration = appointmentDuration;
        this.appointmentDay = appointmentDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @NotNull
    public AppointmentType getType() {
        return type;
    }

    public void setType(@NotNull AppointmentType type) {
        this.type = type;
    }

    @NotNull
    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(@NotNull String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @NotNull
    public String getAppointmentDuration() {
        return appointmentDuration;
    }

    public void setAppointmentDuration(@NotNull String appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }

    @NotNull
    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(@NotNull String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }
}
