package com.bogdan.courierapp.entity;

import com.bogdan.courierapp.entity.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "order_id")
    private UUID id;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<Product> productList;

    @Column(name = "courier_id")
    private UUID courierId;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @Column(name = "order_status")
    private OrderStatus status;

    @Column(name = "placed_at")
    private Date placedAt;

    @Column(name = "delivered_at")
    private Date deliveredAt;

    public Order(UUID id, List<Product> productList, UUID courierId, UUID restaurantId, OrderStatus status, Date placedAt, Date deliveredAt) {
        this.id = id;
        this.productList = productList;
        this.courierId = courierId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.placedAt = placedAt;
        this.deliveredAt = deliveredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(productList, order.productList) && Objects.equals(courierId, order.courierId) && Objects.equals(restaurantId, order.restaurantId) && status == order.status && Objects.equals(placedAt, order.placedAt) && Objects.equals(deliveredAt, order.deliveredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productList, courierId, restaurantId, status, placedAt, deliveredAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productList=" + productList +
                ", courierId=" + courierId +
                ", restaurantId=" + restaurantId +
                ", status=" + status +
                ", placedAt=" + placedAt +
                ", deliveredAt=" + deliveredAt +
                '}';
    }
}
