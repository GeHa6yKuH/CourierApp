package com.bogdan.courierapp.entity;

import com.bogdan.courierapp.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"order\"")
@Builder
public class Order {

    @Id
    @UuidGenerator
    @Column(name = "order_id")
    private UUID id;

//    @ManyToMany(mappedBy = "orders")
//    private List<Product> productList;

    @OneToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "courier_id")
    private Courier courier;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
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
                ", courier=" + courier +
                ", restaurantId=" + restaurantId +
                ", status=" + status +
                ", placedAt=" + placedAt +
                ", deliveredAt=" + deliveredAt +
                '}';
    }
}
