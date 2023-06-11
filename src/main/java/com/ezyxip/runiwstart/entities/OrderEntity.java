package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(fetch = FetchType.EAGER)
    List<CargoBookingEntity> cargoBooking;

    String customer;
    LocalDateTime date;
    byte[] details;

    public OrderEntity() {
    }

    public OrderEntity(List<CargoBookingEntity> cargoBooking, String customer, LocalDateTime date, byte[] details) {
        this.cargoBooking = cargoBooking;
        this.customer = customer;
        this.date = date;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CargoBookingEntity> getCargoBooking() {
        return cargoBooking;
    }

    public void setCargoBooking(List<CargoBookingEntity> cargoBooking) {
        this.cargoBooking = cargoBooking;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public byte[] getDetails() {
        return details;
    }

    public void setDetails(byte[] details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", date=" + date +
                ", details=" + Arrays.toString(details) +
                '}';
    }
}
