package entity;

import entity.enums.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private UUID id;
    private List<Product> productList;
    private UUID courierId;
    private UUID restaurantId;
    private OrderStatus status;
    private Date placedAt;
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
