package com.bogdan.courierapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "statistics")
@Entity
public class Statistics {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "statistics_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id", referencedColumnName = "courier_id")
    private Courier courier;

    private Date period;

    @Column(name = "completed_deliveries")
    private int completedDeliveries;

    @Column(name = "earned_money")
    private double earnedMoney;

    public Statistics(UUID id, Courier courier, Date period, int completedDeliveries, double earnedMoney) {
        this.id = id;
        this.courier = courier;
        this.period = period;
        this.completedDeliveries = completedDeliveries;
        this.earnedMoney = earnedMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return completedDeliveries == that.completedDeliveries && Double.compare(earnedMoney, that.earnedMoney) == 0 && Objects.equals(id, that.id) && Objects.equals(courier, that.courier) && Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courier, period, completedDeliveries, earnedMoney);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", courier=" + courier +
                ", period=" + period +
                ", completedDeliveries=" + completedDeliveries +
                ", earnedMoney=" + earnedMoney +
                '}';
    }
}
