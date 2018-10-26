package rest.auth;

import database.DatabaseManager;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class LoginController {

    @PostMapping(value = "/login")
    public Login login(@RequestBody Credentials credentials) throws ClassNotFoundException, SQLException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().connect();
        final String role = databaseManager.validateCredentials(credentials.getEmail(), credentials.getPassword());
        return new Login(role);
    }

}
