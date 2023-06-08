package com.ezyxip.runiwstart.services.acceptance;


import com.ezyxip.runiwstart.entities.AreaEntity;
import com.ezyxip.runiwstart.entities.CargounitEntity;
import com.ezyxip.runiwstart.entities.EntryEntity;
import com.ezyxip.runiwstart.entities.UserEntity;
import com.ezyxip.runiwstart.repositories.CargounitRepository;
import com.ezyxip.runiwstart.services.AgentContainer;
import com.ezyxip.runiwstart.services.AgentType;
import com.ezyxip.runiwstart.services.OperationManager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AcceptanceManager implements OperationManager {
    protected List<UserEntity> employers;
    protected EntryEntity entry;
    protected AreaEntity area;
    protected CargounitRepository cargounitRepository;

    public AcceptanceManager(List<UserEntity> employers, EntryEntity entry, AreaEntity area, CargounitRepository cargounitRepository) {
        this.employers = employers;
        this.entry = entry;
        this.area = area;
        this.cargounitRepository = cargounitRepository;
    }

    protected AcceptanceManager() {
    }

    @Override
    public AgentContainer getAgentContainer(String username) {
        Optional<UserEntity> currentUser = employers.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        if(currentUser.isEmpty()){
            return null;
        }
        AcceptanceAgent acceptanceAgent = new AcceptanceAgent(this, username);
        AgentContainer agentContainer = new AgentContainer(AgentType.ACCEPTANCE, acceptanceAgent, "Приёмка",
                String.format("Проведение приёмки у ворот %s, зона %s", entry.getName(), area.getName()));
        return agentContainer;
    }

    @Override
    public void confirmWorkStart(String username) {

    }

    @Override
    public void complete(String username) {
        
    }

    protected List<UserEntity> getEmployers() {
        return employers;
    }

    protected void setEmployers(List<UserEntity> employers) {
        this.employers = employers;
    }

    protected EntryEntity getEntry() {
        return entry;
    }

    protected void setEntry(EntryEntity entry) {
        this.entry = entry;
    }

    protected AreaEntity getArea() {
        return area;
    }

    protected void setArea(AreaEntity area) {
        this.area = area;
    }

    protected CargounitRepository getCargounitRepository() {
        return cargounitRepository;
    }

    protected void setCargounitRepository(CargounitRepository cargounitRepository) {
        this.cargounitRepository = cargounitRepository;
    }

    public void acceptCargo(CargounitEntity cargounit) {
    }

    //public void acceptCargo(CargoUnitEntity)
    //public void confirmCargoMove()

}
