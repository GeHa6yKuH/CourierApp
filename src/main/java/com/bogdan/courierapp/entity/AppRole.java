package com.bogdan.courierapp.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "app_role")
@Entity
public class AppRole {

    @Id
//    @GeneratedValue(generator = "UUID")
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
        return Objects.equals(id, appRole.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
