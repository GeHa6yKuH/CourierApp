package com.bogdan.courierapp.entity;

import com.bogdan.courierapp.entity.enums.RestaurantStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "restaurant")
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "restaurant_id")
    private UUID id;

    @Column(name = "owner")
    private String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_role_id", referencedColumnName = "app_role_id")
    private AppRole appRole;

    @Column(name = "restaurant_status")
    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_zone_id", referencedColumnName = "delivery_zone_id")
    private DeliveryZone deliveryZone;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<Product> products;

    public Restaurant(UUID id, String owner, AppRole appRole, RestaurantStatus status, Date creationDate, DeliveryZone deliveryZone, List<Product> products) {
        this.id = id;
        this.owner = owner;
        this.appRole = appRole;
        this.status = status;
        this.creationDate = creationDate;
        this.deliveryZone = deliveryZone;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", appRole=" + appRole +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", deliveryZone=" + deliveryZone +
                ", products=" + products +
                '}';
    }
}
