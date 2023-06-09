package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.CargounitEntity;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface CargounitRepository extends CrudRepository<CargounitEntity, Long>, Serializable {
}
