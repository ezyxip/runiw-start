package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "zone_constraints")
public class ZoneConstraintEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ConstraintValueEntity constraints_value;

    @ManyToOne(fetch = FetchType.EAGER)
    private ZoneEntity zone;

    public ZoneConstraintEntity() {
    }

    public ZoneConstraintEntity(ConstraintValueEntity constraints_value_id, ZoneEntity zone_id) {
        this.constraints_value = constraints_value_id;
        this.zone = zone_id;
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

    public ZoneEntity getZone() {
        return zone;
    }

    public void setZone(ZoneEntity zone_id) {
        this.zone = zone_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoneConstraintEntity that = (ZoneConstraintEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getConstraints_value(), that.getConstraints_value()) && Objects.equals(getZone(), that.getZone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConstraints_value(), getZone());
    }

    @Override
    public String toString() {
        return "ZoneConstraintEntity{" +
                "id=" + id +
                ", constraints_value_id=" + constraints_value +
                ", zone_id=" + zone +
                '}';
    }
}
