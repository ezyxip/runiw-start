package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.CargotypeEntity;

import java.io.Serializable;

public class NomenclaturePosition implements Serializable {
    CargotypeEntity cargoType;
    int count;

    public NomenclaturePosition(CargotypeEntity cargoType, int count) {
        this.cargoType = cargoType;
        this.count = count;
    }

    public String getFormattedCount(){
        return String.format("%d %s", count, cargoType.getUnittype().getName());
    }

    public String getCargoType() {
        return cargoType.getName();
    }

    public void setCargoType(CargotypeEntity cargoType) {
        this.cargoType = cargoType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CargotypeEntity getCargotypeEntity(){
        return cargoType;
    }

    public void changeCount(int integer){
        count += integer;
    }

    @Override
    public String toString() {
        return "NomenclaturePosition{" +
                "key='" + cargoType + '\'' +
                ", count=" + count +
                '}';
    }
}
