package com.bogdan.courierapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager")
@Entity
public class SupportManager {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "manager_id")
    private UUID id;

    @Column(name = "manager_name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_role_id", referencedColumnName = "app_role_id")
    private AppRole appRole;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportManager that = (SupportManager) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SupportManager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", appRole=" + appRole +
                '}';
    }
}
