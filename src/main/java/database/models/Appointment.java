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
    private @NotNull String appointmentDay;
    private @NotNull String timeSlot;
    private int ordinality;

    public Appointment(int id, String subjectName, @NotNull AppointmentType type, @NotNull String appointmentDate,
                       @NotNull String appointmentDay, @NotNull String timeSlot,
                        int ordinality) {
        this.id = id;
        this.subjectName = subjectName;
        this.type = type;
        this.appointmentDate = appointmentDate;
        this.appointmentDay = appointmentDay;
        this.timeSlot = timeSlot;
        this.ordinality = ordinality;
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
    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(@NotNull String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    @NotNull
    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(@NotNull String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getOrdinality() {
        return ordinality;
    }
}
