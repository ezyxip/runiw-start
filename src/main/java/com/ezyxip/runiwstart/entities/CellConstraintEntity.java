package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cells_constraints")
public class CellConstraintEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ConstraintValueEntity constraints_value;

    @ManyToOne(fetch = FetchType.EAGER)
    private CellEntity cell;

    public CellConstraintEntity() {
    }

    public CellConstraintEntity(ConstraintValueEntity constraints_value_id, CellEntity cell_id) {
        this.constraints_value = constraints_value_id;
        this.cell = cell_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConstraintValueEntity getConstraints_value() {
        return constraints_value;
    }

    public void setConstraints_value(ConstraintValueEntity constraints_value_id) {
        this.constraints_value = constraints_value_id;
    }

    public CellEntity getCell() {
        return cell;
    }

    public void setCell(CellEntity cell_id) {
        this.cell = cell_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellConstraintEntity that = (CellConstraintEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getConstraints_value(), that.getConstraints_value()) && Objects.equals(getCell(), that.getCell());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConstraints_value(), getCell());
    }

    @Override
    public String toString() {
        return "CellConstraintEntity{" +
                "id=" + id +
                ", constraints_value_id=" + constraints_value +
                ", cell_id=" + cell +
                '}';
    }
}
