package com.bogdan.courierapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_role")
public class AppRole {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "app_role_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "possibilities")
    private String possibilities;

    public AppRole(UUID id, String name, String possibilities) {
        this.id = id;
        this.name = name;
        this.possibilities = possibilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return Objects.equals(id, appRole.id) && Objects.equals(name, appRole.name) && Objects.equals(possibilities, appRole.possibilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, possibilities);
    }

    @Override
    public String toString() {
        return "AppRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", possibilities='" + possibilities + '\'' +
                '}';
    }
}
