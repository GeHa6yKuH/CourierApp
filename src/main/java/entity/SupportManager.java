package entity;

import java.util.Objects;
import java.util.UUID;

public class SupportManager {
    private UUID id;
    private String name;

    public SupportManager(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportManager that = (SupportManager) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SupportManager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
