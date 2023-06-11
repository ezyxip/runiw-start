package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.CargotypeEntity;
import com.ezyxip.runiwstart.entities.CargounitEntity;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface CargounitRepository extends CrudRepository<CargounitEntity, Long>, Serializable {
    @Override
    List<CargounitEntity> findAll();

    List<CargounitEntity> findAllByCargotype(CargotypeEntity cargotype);
}
