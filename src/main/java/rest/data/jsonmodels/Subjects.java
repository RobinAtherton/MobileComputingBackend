package rest.data.jsonmodels;

import database.models.Subject;

public class Subjects {

    private final Subject[] subjects;

    public Subjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public Subject[] getSubjects() {
        return subjects;
    }
}
