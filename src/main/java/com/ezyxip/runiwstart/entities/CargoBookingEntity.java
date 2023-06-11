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
    OrderEntity order;

    public CargoBookingEntity() {
    }

    public CargoBookingEntity(CargounitEntity cargounit, OrderEntity order) {
        this.cargounit = cargounit;
        this.order = order;
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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CargoBookingEntity{" +
                "id=" + id +
                ", cargounit=" + cargounit +
                ", order=" + order +
                '}';
    }
}
