package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "constraints_value")
public class ConstraintValueEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ConstraintEntity constraints;

    private String value;

    public ConstraintValueEntity() {
    }

    public ConstraintValueEntity(ConstraintEntity constraints_id, String value) {
        this.constraints = constraints_id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConstraintEntity getConstraints() {
        return constraints;
    }

    public void setConstraints(ConstraintEntity constraints_id) {
        this.constraints = constraints_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstraintValueEntity that = (ConstraintValueEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getConstraints(), that.getConstraints()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConstraints(), getValue());
    }

    @Override
    public String toString() {
        return "ConstraintValueEntity{" +
                "id=" + id +
                ", constraints_id=" + constraints +
                ", value='" + value + '\'' +
                '}';
    }
}
