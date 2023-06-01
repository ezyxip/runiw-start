package com.ezyxip.runiwstart.data;

import com.ezyxip.runiwstart.entities.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<AuthorityEntity, String> {
}
