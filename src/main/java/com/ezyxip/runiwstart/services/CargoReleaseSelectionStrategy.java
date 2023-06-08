package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.CargotypeEntity;
import com.ezyxip.runiwstart.entities.CargounitEntity;

import java.util.List;

public interface CargoReleaseSelectionStrategy {
    List<CargounitEntity> getCargo(CargotypeEntity cargotype, int count);
}
