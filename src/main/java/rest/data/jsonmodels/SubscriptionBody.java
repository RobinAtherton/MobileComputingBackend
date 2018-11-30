package rest.data.jsonmodels;

public class SubscriptionBody {

    private String email;
    private String password;
    private String subjectName;

    public SubscriptionBody() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSubjectName() {
        return subjectName;
    }

}
