package rest.data;

import database.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rest.data.jsonmodels.SubscriptionBody;
import rest.data.jsonmodels.Success;

import java.sql.SQLException;

@Controller
public class SubscriptionController {

    Logger logger = LoggerFactory.getLogger(SubscribedAppointmentsController.class);

    @ResponseBody
    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public Success subscribe(@RequestBody SubscriptionBody subscriptionBody) throws SQLException, ClassNotFoundException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.subscribe(subscriptionBody.getEmail(), subscriptionBody.getPassword(), subscriptionBody.getSubjectName());
        logger.info(subscriptionBody.getEmail() + " subscribed");
        return new Success();
    }

    @ResponseBody
    @RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
    public Success unsubscribe(@RequestBody SubscriptionBody subscriptionBody)
            throws SQLException, ClassNotFoundException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.unsubscribe(subscriptionBody.getEmail(), subscriptionBody.getPassword(), subscriptionBody.getSubjectName());
        logger.info(subscriptionBody.getEmail() + " unsubscribed");
        return new Success();
    }
}
