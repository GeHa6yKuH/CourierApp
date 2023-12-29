package com.bogdan.courierapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "delivery_zone")
@Entity
public class DeliveryZone {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "delivery_zone_id")
    private UUID id;

    @OneToMany(mappedBy = "deliveryZone", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<Restaurant> restaurants;

    public DeliveryZone(UUID id, List<Restaurant> restaurants) {
        this.id = id;
        this.restaurants = restaurants;
    }

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
