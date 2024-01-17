package com.bogdan.courierapp.entity;

import com.bogdan.courierapp.entity.enums.Courierstatus;
import com.fasterxml.jackson.annotation.*;
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

    @Column(name = "courier_name")
    private String courierName;

    @Column(name = "registration_date")
    private Date registrationDate;

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

    @OneToMany(mappedBy = "courier", fetch = FetchType.LAZY, cascade = ALL)
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
}
