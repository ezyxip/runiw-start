package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rack_constraints")
public class RackConstraintEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ConstraintValueEntity constraints_value;

    @ManyToOne(fetch = FetchType.EAGER)
    private RackEntity rack;

    public RackConstraintEntity() {
    }

    public RackConstraintEntity(ConstraintValueEntity constraints_value_id, RackEntity rack_id) {
        this.constraints_value = constraints_value_id;
        this.rack = rack_id;
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

    public RackEntity getRack() {
        return rack;
    }

    public void setRack(RackEntity rack_id) {
        this.rack = rack_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RackConstraintEntity that = (RackConstraintEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getConstraints_value(), that.getConstraints_value()) && Objects.equals(getRack(), that.getRack());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConstraints_value(), getRack());
    }

    @Override
    public String toString() {
        return "RackConstraintEntity{" +
                "id=" + id +
                ", constraints_value_id=" + constraints_value +
                ", rack_id=" + rack +
                '}';
    }
}
