package rest.auth;

import org.jetbrains.annotations.NotNull;

public class Login {

    private final @NotNull String role;
    private final @NotNull String email;
    private final @NotNull String password;

    public Login(final @NotNull String role, final @NotNull Credentials credentials) {
        this.role = role;
        this.email = credentials.getEmail();
        this.password = credentials.getPassword();
    }

    public @NotNull String getRole() {
        return role;
    }


    public @NotNull String getEmail() {
        return email;
    }

    public @NotNull String getPassword() {
        return password;
    }
}