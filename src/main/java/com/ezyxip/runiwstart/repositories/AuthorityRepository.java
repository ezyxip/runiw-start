package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorityRepository extends CrudRepository<AuthorityEntity, String> {
    List<AuthorityEntity> findAllById_Username(String username);
}
