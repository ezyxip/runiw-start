package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "entry_to_area")
public class EntryToAreaRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private EntryEntity entry;

    @ManyToOne(fetch = FetchType.EAGER)
    private AreaEntity area;

    public EntryToAreaRelation() {
    }

    public EntryToAreaRelation(EntryEntity entry_id, AreaEntity area_id) {
        this.entry = entry_id;
        this.area = area_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntryEntity getEntry() {
        return entry;
    }

    public void setEntry(EntryEntity entry_id) {
        this.entry = entry_id;
    }

    public AreaEntity getArea() {
        return area;
    }

    public void setArea(AreaEntity area_id) {
        this.area = area_id;
    }

    @Override
    public String toString() {
        return "EntryToAreaRelation{" +
                "id=" + id +
                '}';
    }
}
