package rest.data.jsonmodels;

/**
 * @author Robin Atherton
 */
public class SubjectCreatedResponse {

    private boolean ownsCreated;
    private boolean subjectCreated;

    public SubjectCreatedResponse() {

    }

    public boolean isOwnsCreated() {
        return ownsCreated;
    }

    public void setOwnsCreated(boolean ownsCreated) {
        this.ownsCreated = ownsCreated;
    }

    public boolean isSubjectCreated() {
        return subjectCreated;
    }

    public void setSubjectCreated(boolean subjectCreated) {
        this.subjectCreated = subjectCreated;
    }
}
