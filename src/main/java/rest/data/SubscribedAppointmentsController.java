package rest.data;

import database.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.auth.Credentials;
import rest.data.jsonmodels.Appointments;
import rest.data.jsonmodels.Subjects;

import java.sql.SQLException;

/**
 * @author Robin Atherton
 */
@Controller
public class SubscribedAppointmentsController {

    @NotNull
    @ResponseBody
    @RequestMapping(value="/subscribedAppointments", method = RequestMethod.POST)
    public Appointments appointments(@RequestBody Credentials credentials) throws ClassNotFoundException{
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().connect();
        Appointments appointments= databaseManager.getAppointmentsForSubscribedSubjects(credentials.getEmail());
        return appointments;
    }
}
