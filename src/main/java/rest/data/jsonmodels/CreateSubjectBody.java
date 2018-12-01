package rest.data.jsonmodels;

/**
 * @author Robin Atherton
 */
public class CreateSubjectBody {

    private String email;
    private String password;
    private String subjectName;

    public CreateSubjectBody() {

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
