package com.bogdan.courierapp.entity;

import com.bogdan.courierapp.entity.enums.RestaurantStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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
@Builder
@AllArgsConstructor
public class Restaurant {
    @Id
    @UuidGenerator
    @Column(name = "restaurant_id")
    private UUID id;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "owner")
    private String owner;

    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = REFRESH)
    @JoinColumn(name = "app_role_id", referencedColumnName = "app_role_id")
    @JsonBackReference("arRef")
    private AppRole appRole;

    @Column(name = "restaurant_status")
    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne(cascade = REFRESH)
    @JsonBackReference("restsRef")
    @JoinColumn(name = "delivery_zone_id", referencedColumnName = "delivery_zone_id")
    private DeliveryZone deliveryZone;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = PERSIST)
    @JsonManagedReference("resRef")
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
                ", restaurantName='" + restaurantName + '\'' +
                ", owner='" + owner + '\'' +
                ", appRole=" + appRole +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", deliveryZone=" + deliveryZone +
                ", products=" + products +
                '}';
    }

    //    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
