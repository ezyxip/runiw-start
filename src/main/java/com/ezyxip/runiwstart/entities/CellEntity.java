package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cells")
public class CellEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = RackEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "rack_id", referencedColumnName = "id", nullable=false)
    private RackEntity rack_id;

    @Column
    private String name;

    @OneToMany(targetEntity = CellConstraintEntity.class, fetch = FetchType.EAGER, mappedBy = "cell")
    private List<CellConstraintEntity> constraints;
    public CellEntity() {
    }

    public CellEntity(Long id, RackEntity rack, String name) {
        this.id = id;
        this.rack_id = rack;
        this.name = name;
    }

    public List<CellConstraintEntity> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<CellConstraintEntity> constraints) {
        this.constraints = constraints;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public RackEntity getRack_id() {
        return rack_id;
    }

    public void setRack_id(RackEntity rack) {
        this.rack_id = rack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellEntity that = (CellEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getRack_id(), that.getRack_id()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRack_id(), getName());
    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s", getName(), getRack_id().getName(), getRack_id().getZone_id().getName());
    }
}
