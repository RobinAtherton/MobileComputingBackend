package database.models;

import org.jetbrains.annotations.NotNull;

/**
 * @author Robin Atherton
 */
public class Subject {

    private int id;
    private @NotNull String name;
    private @NotNull String password;

    public Subject(int id, @NotNull String name, @NotNull String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }
}
