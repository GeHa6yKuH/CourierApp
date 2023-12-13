package entity;

import entity.enums.RestaurantStatus;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Restaurant {
    private UUID id;
    private String owner;
    private AppRole appRole;
    private RestaurantStatus status;
    private Date creationDate;
    private UUID deliveryZoneID;
    private List<Product> products;

    public Restaurant(UUID id, String owner, AppRole appRole, RestaurantStatus status, Date creationDate, UUID deliveryZoneID, List<Product> products) {
        this.id = id;
        this.owner = owner;
        this.appRole = appRole;
        this.status = status;
        this.creationDate = creationDate;
        this.deliveryZoneID = deliveryZoneID;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) && Objects.equals(owner, that.owner) && Objects.equals(appRole, that.appRole) && status == that.status && Objects.equals(creationDate, that.creationDate) && Objects.equals(deliveryZoneID, that.deliveryZoneID) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, appRole, status, creationDate, deliveryZoneID, products);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", appRole=" + appRole +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", deliveryZoneID=" + deliveryZoneID +
                ", products=" + products +
                '}';
    }
}
