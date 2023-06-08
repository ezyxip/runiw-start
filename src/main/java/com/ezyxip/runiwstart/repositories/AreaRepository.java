package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.AreaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AreaRepository extends CrudRepository<AreaEntity, Long> {
    List<AreaEntity> findAllByBooking(boolean booking);
}
