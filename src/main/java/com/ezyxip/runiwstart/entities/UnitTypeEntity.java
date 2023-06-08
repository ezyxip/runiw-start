package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "unittype")
public class UnitTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public UnitTypeEntity() {
    }

    public UnitTypeEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UnitTypeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
