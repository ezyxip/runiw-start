package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "entry")
public class EntryEntity implements Serializable {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryEntity entry = (EntryEntity) o;
        return isBooking() == entry.isBooking() && Objects.equals(getId(), entry.getId()) && Objects.equals(getName(), entry.getName()) && Objects.equals(getMode(), entry.getMode()) && Objects.equals(getAreas(), entry.getAreas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMode(), isBooking(), getAreas());
    }

    @Override
    public String toString() {
        return name;
    }
}
