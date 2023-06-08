package com.ezyxip.runiwstart;

import com.ezyxip.runiwstart.entities.*;
import com.ezyxip.runiwstart.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

@SpringBootTest
class RuniwStartApplicationTest {
    Logger logger = Logger.getLogger(RuniwStartApplicationTest.class.getName());
    @Autowired
    EntryRepository entryRepository;
    @Autowired
    CargotypeRepository cargotypeRepository;
    @Autowired
    CellRepository cellRepository;
    @Autowired
    AcceptanceRepository acceptanceRepository;
    @Autowired
    CargounitRepository cargounitRepository;

    @Test
    public void EntryJpaTest(){
        List<EntryEntity> entities = StreamSupport.stream(entryRepository.findAllByBookingEquals(true).spliterator(), false).toList();
        logger.info(entities.toString());

        List<EntryEntity> empty = StreamSupport.stream(entryRepository.findAllByBookingEquals(false).spliterator(), false).toList();
        logger.info(empty.toString());
    }

    @Test
    public void CargounitJpaTest(){
        CargotypeEntity cargotype = cargotypeRepository.findAll().get(0);
        CellEntity cell = cellRepository.findAll().get(0);
        AcceptanceEntity acceptance = acceptanceRepository.findByIsclosedOrderByDate(false).get(0);
        CargounitEntity cargounit = new CargounitEntity(cargotype, cell, acceptance, 10);
        cargounitRepository.save(cargounit);
        logger.info(cargounitRepository.findAll().toString());
    }
}