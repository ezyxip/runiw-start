package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.EntryEntity;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<EntryEntity, Long> {
    Iterable<EntryEntity> findAllByBookingEquals(boolean booking);
}
