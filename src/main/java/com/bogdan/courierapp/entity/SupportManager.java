package com.bogdan.courierapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
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
@Builder
public class SupportManager {

    @Id
    @UuidGenerator
    @Column(name = "manager_id")
    private UUID id;

    @Column(name = "manager_name")
    private String name;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "app_role_id", referencedColumnName = "app_role_id")
    @JsonBackReference("manageRef")
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
