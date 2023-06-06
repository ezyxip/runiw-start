package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "zone")
public class ZoneEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(targetEntity = RackEntity.class, fetch = FetchType.EAGER, mappedBy = "zone_id")
    private List<RackEntity> racks;

    private String name;

    @OneToMany(targetEntity = ZoneConstraintEntity.class, fetch = FetchType.EAGER, mappedBy = "zone")
    private List<ZoneConstraintEntity> constraints;

    public ZoneEntity() {
    }

    public ZoneEntity(Long id, List<RackEntity> racks, String name) {
        this.id = id;
        this.racks = racks;
        this.name = name;
    }

    public List<ZoneConstraintEntity> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<ZoneConstraintEntity> constraints) {
        this.constraints = constraints;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<RackEntity> getRacks() {
        return racks;
    }

    public void setRacks(List<RackEntity> racks) {
        this.racks = racks;
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
        ZoneEntity that = (ZoneEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getRacks(), that.getRacks()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRacks(), getName());
    }

    @Override
    public String toString() {
        return "ZoneEntity{" +
                "id=" + id +
                ", racks=" + (racks != null) +
                ", name='" + name + '\'' +
                '}';
    }
}
