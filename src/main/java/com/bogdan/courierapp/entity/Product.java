package com.bogdan.courierapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "product_id")
    private UUID id;

    @Column(name = "product_price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "description")
    private String description;

    public Product(UUID id, double price, Restaurant restaurant, String description) {
        this.id = id;
        this.price = price;
        this.restaurant = restaurant;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Objects.equals(id, product.id) && Objects.equals(restaurant, product.restaurant) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, restaurant, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", restaurant=" + restaurant +
                ", description='" + description + '\'' +
                '}';
    }
}
