package rest.data;

import database.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.auth.Credentials;
import rest.data.jsonmodels.Appointments;

/**
 * @author Robin Atherton
 */
@Controller
public class AppointmentController {

    Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @NotNull
    @ResponseBody
    @RequestMapping(value="/appointments/subscribed", method = RequestMethod.POST)
    public Appointments appointments(@RequestBody Credentials credentials) throws ClassNotFoundException{
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().connect();
        Appointments appointments= databaseManager.getAppointmentsForSubscribedSubjects(credentials.getEmail());
        logger.info(credentials.getEmail() + " requested appointments");
        return appointments;
    }

}
