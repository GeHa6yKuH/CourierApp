package com.bogdan.courierapp.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_role")
@Entity
public class AppRole {

    @Id
    @UuidGenerator
    @Column(name = "app_role_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "possibilities")
    private String possibilities;

    @OneToMany(mappedBy = "appRole", fetch = FetchType.LAZY)
    @JsonManagedReference("smRef")
    private List<Courier> couriers;

    @OneToMany(mappedBy = "appRole", fetch = FetchType.LAZY)
    @JsonManagedReference("arRef")
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "appRole", fetch = FetchType.LAZY)
    @JsonManagedReference("managRef")
    private List<SupportManager> managers;

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
                ", couriers=" + couriers +
                ", restaurants=" + restaurants +
                ", managers=" + managers +
                '}';
    }
}
