package entity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DeliveryZone {
    private UUID id;
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
