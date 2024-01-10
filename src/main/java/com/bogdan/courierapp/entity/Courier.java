package com.bogdan.courierapp.entity;

import com.bogdan.courierapp.entity.enums.Courierstatus;
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
@Table(name = "courier")
@Entity
public class Courier {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "courier_id")
    private UUID id;

    @Column(name = "registration_date")
    private String registrationDate;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "delivery_zone_id", referencedColumnName = "delivery_zone_id")
    private DeliveryZone deliveryZone;

    @Column(name = "courier_status")
    @Enumerated(EnumType.STRING)
    private Courierstatus status;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "balance")
    private double balance;

    @OneToMany(mappedBy = "courier", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<Statistics> statistics;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "support_manager_id", referencedColumnName = "manager_id")
    private SupportManager supportManager;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_role_id", referencedColumnName = "app_role_id")
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
}
