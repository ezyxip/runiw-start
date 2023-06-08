package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "area")
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean booking;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "area")
    private List<EntryToAreaRelation> entries;

    public AreaEntity() {
    }

    public AreaEntity(String name, boolean booking) {
        this.name = name;
        this.booking = booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBooking() {
        return booking;
    }

    public void setBooking(boolean booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "AreaEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", booking=" + booking +
                '}';
    }
}
