package rest.data;

import database.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.auth.Credentials;
import rest.data.jsonmodels.SubjectCreatedResponse;
import rest.data.jsonmodels.CreateSubjectBody;
import rest.data.jsonmodels.Subjects;

import java.sql.SQLException;

@Controller
public class SubjectController {

    @NotNull
    @ResponseBody
    @RequestMapping(value="/subjects/get", method = RequestMethod.POST)
    public Subjects subjects(@RequestBody Credentials credentials) throws ClassNotFoundException, SQLException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().connect();
        Subjects subjects = databaseManager.getAllSubjectsForStudent(credentials.getEmail(), credentials.getPassword());
        return subjects;
    }

    @NotNull
    @ResponseBody
    @RequestMapping(value="/subjects/create", method = RequestMethod.POST)
    public SubjectCreatedResponse createSubject(@RequestBody CreateSubjectBody body) throws SQLException, ClassNotFoundException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().connect();
        boolean subjectCreated = databaseManager.insertSubject(body.getEmail(), body.getPassword(), body.getSubjectName(), body.getSubjectPassword());
        boolean ownsCreated = databaseManager.insertOwns(body.getEmail(), body.getPassword(), body.getSubjectName());
        final SubjectCreatedResponse response = new SubjectCreatedResponse();
        response.setOwnsCreated(ownsCreated);
        response.setSubjectCreated(subjectCreated);
        return response;
    }


}
