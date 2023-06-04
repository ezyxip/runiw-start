package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rack")
public class RackEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(targetEntity = CellEntity.class, fetch = FetchType.EAGER, mappedBy = "rack_id")
    private List<CellEntity> cells;

    @ManyToOne(targetEntity = ZoneEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    private ZoneEntity zone_id;

    private String name;

    public RackEntity() {
    }

    public RackEntity(Long id, List<CellEntity> cells, ZoneEntity zone, String name) {
        this.id = id;
        this.cells = cells;
        this.zone_id = zone;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<CellEntity> getCells() {
        return cells;
    }

    public void setCells(List<CellEntity> cells) {
        this.cells = cells;
    }

    public ZoneEntity getZone_id() {
        return zone_id;
    }

    public void setZone_id(ZoneEntity zone) {
        this.zone_id = zone;
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
        RackEntity that = (RackEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCells(), that.getCells()) && Objects.equals(getZone_id(), that.getZone_id()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCells(), getZone_id(), getName());
    }

    @Override
    public String toString() {
        return "RackEntity{" +
                "id=" + id +
                ", cells=" + (cells != null) +
                ", zone_id=" + (zone_id != null)+
                ", name='" + name + '\'' +
                '}';
    }
}
