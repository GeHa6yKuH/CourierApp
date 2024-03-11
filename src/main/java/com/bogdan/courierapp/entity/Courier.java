package com.bogdan.courierapp.entity;

import com.bogdan.courierapp.entity.enums.Courierstatus;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

import static jakarta.persistence.CascadeType.*;


@Getter
@Setter
@Builder
@Accessors(fluent = false, chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courier")
@Entity
public class Courier /*implements UserDetails*/ {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "courier_id")
    private UUID id;

    @Column(name = "courier_name")
    private String courierName;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "delivery_zone_id", referencedColumnName = "delivery_zone_id")
    @JsonBackReference("coursRef")
    private DeliveryZone deliveryZone;

    @Column(name = "courier_status")
    @Enumerated(EnumType.STRING)
    private Courierstatus status;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "balance")
    private double balance;

    @OneToMany(mappedBy = "courier")
    @JsonManagedReference("statsRef")
    private List<Statistics> statistics;

    @ManyToOne
    @JoinColumn(name = "support_manager_id", referencedColumnName = "manager_id")
    @JsonBackReference("msRef")
    private SupportManager supportManager;

    @ManyToOne
    @JoinColumn(name = "app_role_id", referencedColumnName = "app_role_id")
    @JsonBackReference("smRef")
    private AppRole appRole;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Objects.equals(id, courier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", registrationDate='" + registrationDate + '\'' +
                ", deliveryZone=" + deliveryZone +
                ", status=" + status +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                ", statistics=" + statistics +
                ", supportManager=" + supportManager +
                ", appRole=" + appRole +
                '}';
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ROLE_" + appRole.getName()));
//    }
//
//    @Override
//    public String getUsername() {
//        return courierName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
