package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    List<UserEntity> findAllByBooking(boolean booking);
}
