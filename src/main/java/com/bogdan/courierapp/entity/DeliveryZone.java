package com.bogdan.courierapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "delivery_zone")
public class DeliveryZone {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "delivery_zone_id")
    private UUID id;

    @OneToMany(mappedBy = "delivery_zone", fetch = FetchType.LAZY,
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
        return Objects.equals(id, that.id) && Objects.equals(restaurants, that.restaurants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurants);
    }

    @Override
    public String toString() {
        return "DeliveryZone{" +
                "id=" + id +
                ", restaurants=" + restaurants +
                '}';
    }
}
