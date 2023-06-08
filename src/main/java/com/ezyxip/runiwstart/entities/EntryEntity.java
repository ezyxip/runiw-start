package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "entry")
public class EntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mode;
    private boolean booking;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entry")
    private List<EntryToAreaRelation> areas;

    public EntryEntity(String name, String mode, boolean booking) {
        this.name = name;
        this.mode = mode;
        this.booking = booking;
    }

    public EntryEntity() {
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isBooking() {
        return booking;
    }

    public void setBooking(boolean booking) {
        this.booking = booking;
    }

    public List<EntryToAreaRelation> getAreas() {
        return areas;
    }

    public void setAreas(List<EntryToAreaRelation> areas) {
        this.areas = areas;
    }

    @Override
    public String toString() {
        return "EntryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mode='" + mode + '\'' +
                ", booking=" + booking +
                '}';
    }
}
