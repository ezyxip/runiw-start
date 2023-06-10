package com.ezyxip.runiwstart.repositories;

import com.ezyxip.runiwstart.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
    List<MessageEntity> findAll();
}
