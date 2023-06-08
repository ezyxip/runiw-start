package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.CargotypeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CargotypeRepository extends CrudRepository<CargotypeEntity, Long> {
    @Override
    List<CargotypeEntity> findAll();
}
