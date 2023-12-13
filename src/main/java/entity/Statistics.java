package entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Statistics {
    private UUID id;
    private UUID courierId;
    private Date period;
    private int completedDeliveries;
    private double earnedMoney;

    public Statistics(UUID id, UUID courierId, Date period, int completedDeliveries, double earnedMoney) {
        this.id = id;
        this.courierId = courierId;
        this.period = period;
        this.completedDeliveries = completedDeliveries;
        this.earnedMoney = earnedMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return completedDeliveries == that.completedDeliveries && Double.compare(earnedMoney, that.earnedMoney) == 0 && Objects.equals(id, that.id) && Objects.equals(courierId, that.courierId) && Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courierId, period, completedDeliveries, earnedMoney);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", courierId=" + courierId +
                ", period=" + period +
                ", completedDeliveries=" + completedDeliveries +
                ", earnedMoney=" + earnedMoney +
                '}';
    }
}
