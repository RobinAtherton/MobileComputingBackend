package database.models;

import org.jetbrains.annotations.NotNull;

/**
 * @author Robin Atherton
 */
public class Subject {

    private int id;
    private @NotNull String name;
    private @NotNull String password;
    private boolean subscribed;

    public Subject(int id, @NotNull String name, @NotNull String password, boolean subscribed) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.subscribed = subscribed;
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

    public boolean isSubscribed() {
        return subscribed;
    }
}
