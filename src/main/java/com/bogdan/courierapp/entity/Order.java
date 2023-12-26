package com.bogdan.courierapp.entity;

import com.bogdan.courierapp.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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

    @OneToOne
    private Courier courier;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @Column(name = "order_status")
    private OrderStatus status;

    @Column(name = "placed_at")
    private Date placedAt;

    @Column(name = "delivered_at")
    private Date deliveredAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productList=" + productList +
                ", courier=" + courier +
                ", restaurantId=" + restaurantId +
                ", status=" + status +
                ", placedAt=" + placedAt +
                ", deliveredAt=" + deliveredAt +
                '}';
    }
}
