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
@Table(name = "manager")
@Entity
public class SupportManager {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "manager_id")
    private UUID id;

    @Column(name = "manager_name")
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
