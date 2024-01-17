package com.bogdan.courierapp.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
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
    @UuidGenerator
    @Column(name = "manager_id")
    private UUID id;

    @Column(name = "manager_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "app_role_id", referencedColumnName = "app_role_id")
    @JsonBackReference("managRef")
    private AppRole appRole;

    @OneToMany(mappedBy = "supportManager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("msRef")
    private List<Courier> couriers;


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
