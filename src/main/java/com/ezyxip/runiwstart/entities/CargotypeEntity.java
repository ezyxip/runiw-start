package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cargotype")
public class CargotypeEntity {
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
    public String toString() {
        return "CargotypeEntity{" +
                "id=" + id +
                ", unittype=" + unittype +
                ", name='" + name + '\'' +
                '}';
    }
}
