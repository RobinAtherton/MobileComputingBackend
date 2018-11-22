package rest.data;

import database.DatabaseManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@Controller
public class SubscriptionController {

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void subscribe(@RequestBody SubscriptionBody subscriptionBody) throws SQLException, ClassNotFoundException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.subscribe(subscriptionBody.getEmail(), subscriptionBody.getPassword(), subscriptionBody.getSubjectName());
    }

    @RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void unsubscribe(@RequestBody SubscriptionBody subscriptionBody)
            throws SQLException, ClassNotFoundException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.unsubscribe(subscriptionBody.getEmail(), subscriptionBody.getPassword(), subscriptionBody.getSubjectName());
    }
}
