package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cargotype")
public class CargotypeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UnitTypeEntity unittype;

    String name;

    public CargotypeEntity() {
    }

    public CargotypeEntity(UnitTypeEntity unittype, String name) {
        this.unittype = unittype;
        this.name = name;
    }

    public UnitTypeEntity getUnittype() {
        return unittype;
    }

    public void setUnittype(UnitTypeEntity unittype) {
        this.unittype = unittype;
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
        CargotypeEntity that = (CargotypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(getUnittype(), that.getUnittype()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getUnittype(), getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
