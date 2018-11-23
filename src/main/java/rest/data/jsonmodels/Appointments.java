package rest.data.jsonmodels;

import database.models.Appointment;

public class Appointments {

    private final Appointment[] appointments;

    public Appointments(Appointment[] appointments) {
        this.appointments = appointments;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }
}
