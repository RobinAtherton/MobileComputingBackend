package rest.data.jsonmodels;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Robin Atherton
 */
public class SubjectBody {

    private String email;
    private String password;
    private String subjectName;
    private String subjectPassword;

    public SubjectBody() {

    }
    public String getEmail() {
        return email;
    }

    public String getSubjectPassword() {
        return subjectPassword;
    }
    public String getSubjectName() {
        return subjectName;
    }

    public String getPassword() {
        return password;
    }
}
