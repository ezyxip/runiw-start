package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cargo_booking")
public class CargoBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    CargounitEntity cargounit;

    @ManyToOne(fetch = FetchType.EAGER)
    OrderEntity orders;

    int count;

    public CargoBookingEntity() {
    }

    public CargoBookingEntity(CargounitEntity cargounit, OrderEntity orders, int count) {
        this.cargounit = cargounit;
        this.orders = orders;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CargounitEntity getCargounit() {
        return cargounit;
    }

    public void setCargounit(CargounitEntity cargounit) {
        this.cargounit = cargounit;
    }

    public OrderEntity getOrders() {
        return orders;
    }

    public void setOrders(OrderEntity order) {
        this.orders = order;
    }

    @Override
    public String toString() {
        return "CargoBookingEntity{" +
                "id=" + id +
                ", cargounit=" + cargounit +
                ", order=" + orders +
                '}';
    }
}
