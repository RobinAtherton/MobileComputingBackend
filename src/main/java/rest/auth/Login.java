package rest.auth;

public class Login {

    private String role;

    public Login(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}