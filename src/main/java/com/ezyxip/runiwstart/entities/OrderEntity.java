package com.ezyxip.runiwstart.entities;

import com.ezyxip.runiwstart.services.NomenclaturePosition;
import jakarta.persistence.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orders")
    List<CargoBookingEntity> cargoBooking;

    String customer;
    LocalDateTime date;
    byte[] details;
    boolean isapproved;

    public OrderEntity() {
    }

    public OrderEntity(List<CargoBookingEntity> cargoBooking, String customer, LocalDateTime date, byte[] details, boolean isapproved) {
        this.cargoBooking = cargoBooking;
        this.customer = customer;
        this.date = date;
        this.details = details;
        this.isapproved = isapproved;
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

    public boolean isIsapproved() {
        return isapproved;
    }

    public void setIsapproved(boolean isapproved) {
        this.isapproved = isapproved;
    }

    public List<NomenclaturePosition> getNomenclature() throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(getDetails());
        ObjectInputStream ois = new ObjectInputStream(bis);
        List<NomenclaturePosition> noms = (List<NomenclaturePosition>) ois.readObject();
        return noms;
    }

    public void setNomenclature(List<NomenclaturePosition> noms) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(noms);
        details = bos.toByteArray();
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", date=" + date +
                ", isapproved=" + isapproved +
                '}';
    }
}
