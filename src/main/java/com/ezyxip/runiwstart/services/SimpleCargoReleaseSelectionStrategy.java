package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.CargotypeEntity;
import com.ezyxip.runiwstart.entities.CargounitEntity;
import com.ezyxip.runiwstart.repositories.CargounitRepository;

import java.util.List;

public class SimpleCargoReleaseSelectionStrategy implements CargoReleaseSelectionStrategy{

    CargounitRepository cargounitRepository;

    public SimpleCargoReleaseSelectionStrategy(CargounitRepository cargounitRepository){
        this.cargounitRepository = cargounitRepository;
    }

    @Override
    public List<CargounitEntity> getCargo(CargotypeEntity cargotype, int count) {
        return null;
    }
}
