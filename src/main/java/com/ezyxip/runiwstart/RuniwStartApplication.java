package com.ezyxip.runiwstart;

import com.ezyxip.runiwstart.entities.OrderEntity;
import com.ezyxip.runiwstart.repositories.*;
import com.ezyxip.runiwstart.services.DataStorage;
import com.ezyxip.runiwstart.services.NomenclaturePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

@SpringBootApplication
public class RuniwStartApplication {

    static Logger logger = Logger.getLogger(RuniwStartApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(RuniwStartApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(@Autowired ApplicationContext applicationContext){
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
            DataStorage.orderRepository = applicationContext.getBean(OrderRepository.class);
            DataStorage.cargoBookingRepository = applicationContext.getBean(CargoBookingRepository.class);

            //--------------------------
//            ArrayList<NomenclaturePosition> noms = new ArrayList<>();
//            noms.add(new NomenclaturePosition(DataStorage.cargotypeRepository.findAll().get(0), 100));
//            noms.add(new NomenclaturePosition(DataStorage.cargotypeRepository.findAll().get(1), 200));
//            noms.add(new NomenclaturePosition(DataStorage.cargotypeRepository.findAll().get(2), 300));
//
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(bos);
//            oos.writeObject(noms);
//            byte[] details = bos.toByteArray();
//
//            DataStorage.orderRepository.save(new OrderEntity(null, "Заказчик", LocalDateTime.now(), details, false));
//
//            ArrayList<NomenclaturePosition> noms2 = new ArrayList<>();
//            noms2.add(new NomenclaturePosition(DataStorage.cargotypeRepository.findAll().get(0), 10));
//            noms2.add(new NomenclaturePosition(DataStorage.cargotypeRepository.findAll().get(1), 20));
//            noms2.add(new NomenclaturePosition(DataStorage.cargotypeRepository.findAll().get(2), 30));
//
//            ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
//            ObjectOutputStream oos2 = new ObjectOutputStream(bos2);
//            oos2.writeObject(noms2);
//            byte[] details2 = bos2.toByteArray();
//
//            DataStorage.orderRepository.save(new OrderEntity(null, "Заказчик2", LocalDateTime.now(), details2, false));
//
//            ArrayList<NomenclaturePosition> noms3 = new ArrayList<>();
//            noms3.add(new NomenclaturePosition(DataStorage.cargotypeRepository.findAll().get(0), 1));
//            noms3.add(new NomenclaturePosition(DataStorage.cargotypeRepository.findAll().get(1), 2));
//            noms3.add(new NomenclaturePosition(DataStorage.cargotypeRepository.findAll().get(2), 3));
//
//            ByteArrayOutputStream bos3 = new ByteArrayOutputStream();
//            ObjectOutputStream oos3 = new ObjectOutputStream(bos3);
//            oos3.writeObject(noms3);
//            byte[] details3 = bos3.toByteArray();
//
//            DataStorage.orderRepository.save(new OrderEntity(null, "Заказчик3", LocalDateTime.now(), details3, false));
        };
    }

}
