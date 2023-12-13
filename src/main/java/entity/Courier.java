package entity;

import entity.enums.Courierstatus;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Courier {

    private UUID id;

    private Date date;

    private DeliveryZone deliveryZone;

    private Courierstatus status;

    private String phoneNumber;

    private double balance;

    private Statistics statistics;

    private UUID supportManagerID;

    private AppRole appRole;

    public Courier(UUID id, Date date, DeliveryZone deliveryZone, Courierstatus status, String phoneNumber, double balance, Statistics statistics, UUID supportManagerID, AppRole appRole) {
        this.id = id;
        this.date = date;
        this.deliveryZone = deliveryZone;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.statistics = statistics;
        this.supportManagerID = supportManagerID;
        this.appRole = appRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Double.compare(balance, courier.balance) == 0 && Objects.equals(id, courier.id) && Objects.equals(date, courier.date) && Objects.equals(deliveryZone, courier.deliveryZone) && status == courier.status && Objects.equals(phoneNumber, courier.phoneNumber) && Objects.equals(statistics, courier.statistics) && Objects.equals(supportManagerID, courier.supportManagerID) && Objects.equals(appRole, courier.appRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, deliveryZone, status, phoneNumber, balance, statistics, supportManagerID, appRole);
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", date=" + date +
                ", deliveryZone=" + deliveryZone +
                ", status=" + status +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                ", statistics=" + statistics +
                ", supportManagerID=" + supportManagerID +
                ", appRole=" + appRole +
                '}';
    }
}
