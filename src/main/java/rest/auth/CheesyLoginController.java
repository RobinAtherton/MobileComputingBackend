package rest.auth;

import database.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Robin Atherton
 */
@RestController
public class CheesyLoginController {

    @NotNull
    @RequestMapping(value = "/cheesyLogin", params = {"email", "password"}, method = GET)
    @ResponseBody
    public Login login(@NotNull @RequestParam("email") String email, @NotNull @RequestParam("password") String password) throws ClassNotFoundException, SQLException {
        final DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.getDatabase().connect();
        final String role = databaseManager.validateCredentials(email, password);
        return new Login(role);
    }
}
