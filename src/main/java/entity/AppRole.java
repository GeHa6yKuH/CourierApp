package entity;

import java.util.Objects;

public class AppRole {
    private String name;
    private String possibilities;

    public AppRole(String name, String possibilities) {
        this.name = name;
        this.possibilities = possibilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return Objects.equals(name, appRole.name) && Objects.equals(possibilities, appRole.possibilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, possibilities);
    }

    @Override
    public String toString() {
        return "AppRole{" +
                "name='" + name + '\'' +
                ", possibilities='" + possibilities + '\'' +
                '}';
    }
}
