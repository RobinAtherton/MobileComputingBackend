package database.models;

import database.models.enums.Role;
import org.jetbrains.annotations.NotNull;

/**
 * @author Robin Atherton
 */

public class Person {

    private @NotNull String email;
    private @NotNull String password;
    private @NotNull
    Role role;

    public Person(@NotNull String email, @NotNull String password, @NotNull Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public @NotNull Role getRole() {
        return role;
    }

    public void setRole(@NotNull Role role) {
        this.role = role;
    }
}
