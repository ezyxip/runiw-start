package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.EntryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EntryRepository extends CrudRepository<EntryEntity, Long> {
    List<EntryEntity> findAllByBookingEquals(boolean booking);
    List<EntryEntity> findAllByModeAndBooking(String mode, boolean booking);
}
