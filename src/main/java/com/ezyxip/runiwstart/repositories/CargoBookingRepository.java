package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.CargoBookingEntity;
import com.ezyxip.runiwstart.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CargoBookingRepository extends CrudRepository<CargoBookingEntity, Long> {
    List<CargoBookingEntity> findAllByOrders(OrderEntity order);
}
