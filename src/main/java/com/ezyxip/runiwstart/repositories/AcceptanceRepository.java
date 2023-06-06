package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.AcceptanceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AcceptanceRepository extends CrudRepository<AcceptanceEntity, Long> {
    List<AcceptanceEntity> findByIsclosedOrderByDate(boolean b);
}
