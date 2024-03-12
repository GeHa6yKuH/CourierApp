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

import static jakarta.persistence.CascadeType.ALL;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delivery_zone")
@Entity
public class DeliveryZone {

    @Id
    @UuidGenerator
    @Column(name = "delivery_zone_id")
    private UUID id;

    @Column(name = "delivery_zone_name")
    private String name;

    @JsonManagedReference("restsRef")
    @OneToMany(mappedBy = "deliveryZone", fetch = FetchType.LAZY, cascade = ALL)
    private List<Restaurant> restaurants;

    @JsonManagedReference("coursRef")
    @OneToMany(mappedBy = "deliveryZone", fetch = FetchType.LAZY, cascade = ALL)
    private List<Courier> couriers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryZone that = (DeliveryZone) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeliveryZone{" +
                "id=" + id +
                ", restaurants=" + restaurants +
                '}';
    }
}
