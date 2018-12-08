package rest.data.jsonmodels;

import org.jetbrains.annotations.NotNull;

/**
 * @author Robin Atherton
 */
public class AppointmentBody {

    private String email;
    private String password;
    private String subjectName;
    private String appointmentType;
    private String appointmentDate;
    private String appointmentDay;
    private String timeSlot;

    public AppointmentBody() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @NotNull
    public String getSubjectName() {
        return subjectName;
    }

    @NotNull
    public String getAppointmentType() {
        return appointmentType;
    }

    @NotNull
    public String getAppointmentDate() {
        return appointmentDate;
    }

    @NotNull
    public String getAppointmentDay() {
        return appointmentDay;
    }
    @NotNull
    public String getTimeSlot() {
        return timeSlot;
    }
}
