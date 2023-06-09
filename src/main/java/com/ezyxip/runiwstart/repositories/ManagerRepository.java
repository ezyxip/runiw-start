package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.OperationManagerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManagerRepository extends CrudRepository<OperationManagerEntity, String> {
    @Override
    List<OperationManagerEntity> findAll();
}
