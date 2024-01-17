package com.bogdan.courierapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistics")
@Entity
public class Statistics {
    @Id
    @UuidGenerator
    @Column(name = "statistics_id")
    private UUID id;

    @ManyToOne
    @JsonBackReference("statsRef")
    @JoinColumn(name = "courier_id", referencedColumnName = "courier_id")
    private Courier courier;

    @Column(name = "completed_deliveries")
    private int completedDeliveries;

    @Column(name = "earned_money")
    private double earnedMoney;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", courier=" + courier +
                ", completedDeliveries=" + completedDeliveries +
                ", earnedMoney=" + earnedMoney +
                '}';
    }
}
