package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.CellEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CellRepository extends CrudRepository<CellEntity, Long> {
    @Override
    List<CellEntity> findAll();
}
