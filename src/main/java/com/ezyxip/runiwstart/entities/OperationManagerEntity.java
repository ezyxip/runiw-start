package com.ezyxip.runiwstart.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Arrays;

@Entity
@Table(name = "operation_managers")
public class OperationManagerEntity {
    @Id
    String id;

    byte[] object;

    public OperationManagerEntity() {
    }

    public OperationManagerEntity(String id, byte[] object) {
        this.id = id;
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getObject() {
        return object;
    }

    public void setObject(byte[] object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "OperationManagerEntity{" +
                "id='" + id + '\'' +
                ", object=" + Arrays.toString(object) +
                '}';
    }
}
