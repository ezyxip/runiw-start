package com.ezyxip.runiwstart;

import com.ezyxip.runiwstart.repositories.*;
import com.ezyxip.runiwstart.services.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class RuniwStartApplication {

    static Logger logger = Logger.getLogger(RuniwStartApplication.class.getName());

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(RuniwStartApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            logger.info("Начало заполнения DataStorage");
            DataStorage.cellRepository = applicationContext.getBean(CellRepository.class);
            DataStorage.userRepository = applicationContext.getBean(UserRepository.class);
            DataStorage.areaRepository = applicationContext.getBean(AreaRepository.class);
            DataStorage.entryRepository = applicationContext.getBean(EntryRepository.class);
            DataStorage.cargounitRepository = applicationContext.getBean(CargounitRepository.class);
            DataStorage.authorityRepository = applicationContext.getBean(AuthorityRepository.class);
            DataStorage.acceptanceRepository = applicationContext.getBean(AcceptanceRepository.class);
            DataStorage.managerRepository = applicationContext.getBean(ManagerRepository.class);
            DataStorage.cargotypeRepository = applicationContext.getBean(CargotypeRepository.class);
            DataStorage.messageRepository = applicationContext.getBean(MessageRepository.class);
        };
    }

}
