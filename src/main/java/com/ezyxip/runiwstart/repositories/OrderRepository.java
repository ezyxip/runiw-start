package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByIsapproved(boolean isApproved);
}
