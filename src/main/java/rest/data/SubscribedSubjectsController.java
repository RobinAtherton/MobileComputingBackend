package rest.data;

import database.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.auth.Credentials;

import java.sql.SQLException;

@Controller
public class SubscribedSubjectsController {

    @NotNull
    @ResponseBody
    @RequestMapping(value="/subscriptions", method = RequestMethod.POST)
    public Subjects subjects(@RequestBody Credentials credentials) throws ClassNotFoundException, SQLException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().connect();
        Subjects subjects = databaseManager.getSubscribedSubjectsForStudent(credentials.getEmail());
        return subjects;
    }
}
