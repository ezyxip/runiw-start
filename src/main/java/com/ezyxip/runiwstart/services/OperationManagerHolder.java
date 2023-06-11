package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.AcceptanceEntity;
import com.ezyxip.runiwstart.entities.EntryEntity;
import com.ezyxip.runiwstart.entities.OperationManagerEntity;
import com.ezyxip.runiwstart.entities.OrderEntity;
import com.ezyxip.runiwstart.repositories.CargounitRepository;
import com.ezyxip.runiwstart.repositories.ManagerRepository;
import com.ezyxip.runiwstart.services.acceptance.AcceptanceManager;
import com.ezyxip.runiwstart.services.acceptance.AcceptanceManagerBuilder;
import com.ezyxip.runiwstart.services.cargoselect.CargoSelectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OperationManagerHolder {
    private AcceptanceAreaSelectionStrategy acceptanceAreaSelectionStrategy;
    private  EntrySelectionStrategy entrySelectionStrategy;
    private PersonnelSelectionStrategy personnelSelectionStrategy;
    private CargoReleaseSelectionStrategy cargoReleaseSelectionStrategy;
    private final ArrayList<OperationManager> managers;

    public OperationManagerHolder(
            @Autowired AcceptanceAreaSelectionStrategy acceptanceAreaSelectionStrategy,
            @Autowired EntrySelectionStrategy entrySelectionStrategy,
            @Autowired PersonnelSelectionStrategy personnelSelectionStrategy,
            @Autowired ManagerRepository managerRepository
            ) {
        this.acceptanceAreaSelectionStrategy = acceptanceAreaSelectionStrategy;
        this.entrySelectionStrategy = entrySelectionStrategy;
        this.personnelSelectionStrategy = personnelSelectionStrategy;

        managers = new ArrayList<>();

        List<OperationManagerEntity> operationManagerEntities = managerRepository.findAll();
        operationManagerEntities.forEach(ome -> {
            try {
                managers.add(OperationManager.deserialize(ome.getObject()));
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Не удалось десериализовать менеджер " + ome.getId());
            }
        });
    }

    /*
       Методы, занимающиеся созданием соотвествующих менеджеров операций. После все они будут храниться в холдере
    */
    public AcceptanceManager createAcceptanceManager(AcceptanceEntity acceptanceEntity) throws Exception {
        EntryEntity entry = entrySelectionStrategy.getEntry();
        return AcceptanceManager.getBuilder()
                .setEmployers(personnelSelectionStrategy.getEmployer(1, "ROLE_LOADER").get(0))
                .setArea(acceptanceAreaSelectionStrategy.getArea(entry))
                .setEntry(entry)
                .setAcceptance(acceptanceEntity)
                .build();
    }
    //public CargoMoveManager createCargoMoveManager()
    public CargoSelectManager createCargoSelectManager(OrderEntity order) throws Exception {
        CargoSelectManager manager = new CargoSelectManager();
        manager.setEmployer(personnelSelectionStrategy.getEmployer(1, "ROLE_MANAGER").get(0));
        manager.setOrder(order);
        manager.setArea(entrySelectionStrategy.getEntry().getAreas().get(0).getArea());
        return manager;
    }
    //public CargoReleaseManager createCargoReleaseManager()

    public List<AgentContainer> getAgent(String username){
        return managers.stream().map(m -> m.getAgentContainer(username)).filter(Objects::nonNull).toList();
    }

    public void initManager(OperationManager operationManager){
        try {
            OperationManagerEntity operationManagerEntity = new OperationManagerEntity(
                    operationManager.getId(),
                    OperationManager.serialize(operationManager)
            );
            managers.add(operationManager);
            operationManager.reserveResource();
            DataStorage.managerRepository.save(operationManagerEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopManager(OperationManager operationManager){
        operationManager.stop();
        DataStorage.managerRepository.deleteById(operationManager.getId());
        managers.remove(operationManager);
    }

    public CargoReleaseSelectionStrategy getCargoReleaseSelectionStrategy() {
        return cargoReleaseSelectionStrategy;
    }

    public void setCargoReleaseSelectionStrategy(CargoReleaseSelectionStrategy cargoReleaseSelectionStrategy) {
        this.cargoReleaseSelectionStrategy = cargoReleaseSelectionStrategy;
    }

    public AcceptanceAreaSelectionStrategy getAcceptanceAreaSelectionStrategy() {
        return acceptanceAreaSelectionStrategy;
    }

    public void setAcceptanceAreaSelectionStrategy(AcceptanceAreaSelectionStrategy acceptanceAreaSelectionStrategy) {
        this.acceptanceAreaSelectionStrategy = acceptanceAreaSelectionStrategy;
    }

    public EntrySelectionStrategy getEntrySelectionStrategy() {
        return entrySelectionStrategy;
    }

    public void setEntrySelectionStrategy(EntrySelectionStrategy entrySelectionStrategy) {
        this.entrySelectionStrategy = entrySelectionStrategy;
    }

    public PersonnelSelectionStrategy getPersonnelSelectionStrategy() {
        return personnelSelectionStrategy;
    }

    public void setPersonnelSelectionStrategy(PersonnelSelectionStrategy personnelSelectionStrategy) {
        this.personnelSelectionStrategy = personnelSelectionStrategy;
    }

    public List<OperationManager> getManagers() {
        return managers.stream().filter(OperationManager::isEnable).toList();
    }
}
