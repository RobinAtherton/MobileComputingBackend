package rest.auth;

import database.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class LoginController {

    @NotNull
    @GetMapping(value = "/login")
    public Login login(@NotNull @RequestBody Credentials credentials) throws ClassNotFoundException, SQLException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().connect();
        final String role = databaseManager.validateCredentials(credentials.getEmail(), credentials.getPassword());
        return new Login(role);
    }



}
