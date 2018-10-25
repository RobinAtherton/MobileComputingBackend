package database.models;

import database.models.enums.AppointmentType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Robin Atherton
 */
public class Appointment {

    private int id;
    private int subject;
    private @NotNull AppointmentType type;
    private @NotNull String appointmentDate;

    public Appointment(int id, int subject, @NotNull AppointmentType type, @NotNull String appointmentDate) {
        this.id = id;
        this.subject = subject;
        this.type = type;
        this.appointmentDate = appointmentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public @NotNull AppointmentType getType() {
        return type;
    }

    public void setType(@NotNull AppointmentType type) {
        this.type = type;
    }

    public @NotNull String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(@NotNull String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
