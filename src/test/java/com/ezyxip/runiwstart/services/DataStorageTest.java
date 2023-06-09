package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.EntryEntity;
import com.ezyxip.runiwstart.repositories.CellRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataStorageTest {

    Logger logger = Logger.getLogger(DataStorageTest.class.getName());

    @Test
    void dataTest(){
        logger.info(DataStorage.cellRepository.findAll().toString());
    }
}