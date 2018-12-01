package rest.data;

import database.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.auth.Credentials;
import rest.data.jsonmodels.BoolResponse;
import rest.data.jsonmodels.CreateSubjectBody;
import rest.data.jsonmodels.Subjects;
import rest.data.jsonmodels.Success;

import javax.xml.crypto.Data;
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
    public BoolResponse createSubject(@RequestBody CreateSubjectBody body) throws SQLException, ClassNotFoundException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().connect();
        boolean response = databaseManager.insertOwns(body.getEmail(), body.getPassword(), body.getSubjectName());
        final BoolResponse boolResponse = new BoolResponse();
        boolResponse.setResponse(response);
        return boolResponse;
    }


}
