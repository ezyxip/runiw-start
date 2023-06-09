package com.ezyxip.runiwstart.services.acceptance;


import com.ezyxip.runiwstart.entities.*;
import com.ezyxip.runiwstart.repositories.CargounitRepository;
import com.ezyxip.runiwstart.services.AgentContainer;
import com.ezyxip.runiwstart.services.AgentType;
import com.ezyxip.runiwstart.services.DataStorage;
import com.ezyxip.runiwstart.services.OperationManager;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AcceptanceManager implements OperationManager {

    static public AcceptanceManagerBuilder getBuilder(){
        return new AcceptanceManagerBuilder();
    }

    protected String id;
    protected List<UserEntity> employers;
    protected EntryEntity entry;
    protected AreaEntity area;
    AcceptanceEntity acceptanceEntity;

    public AcceptanceManager(List<UserEntity> employers, EntryEntity entry, AreaEntity area, AcceptanceEntity acceptanceEntity) {
        this();
        this.employers = employers;
        this.entry = entry;
        this.area = area;
        this.acceptanceEntity = acceptanceEntity;
    }

    protected AcceptanceManager() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public AgentContainer getAgentContainer(String username) {
        Optional<UserEntity> currentUser = employers.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        if(currentUser.isEmpty()){
            return null;
        }
        AcceptanceAgent acceptanceAgent = new AcceptanceAgent(this, username);
        return new AgentContainer(AgentType.ACCEPTANCE, acceptanceAgent, "Приёмка",
                String.format("Проведение приёмки у ворот %s, зона %s", entry.getName(), area.getName()));
    }

    @Override
    public void confirmWorkStart(String username) {

    }

    @Override
    public void complete(String username) {
        
    }

    @Override
    public void reserveResource() {
        employers.forEach(u -> {
            u.setBooking(false);
            DataStorage.userRepository.save(u);
        });
        area.setBooking(false);
        DataStorage.areaRepository.save(area);
        entry.setBooking(false);
        DataStorage.entryRepository.save(entry);
    }

    @Override
    public String getTitle() {
        return "Приёмка " + acceptanceEntity.getDate() + " : " + acceptanceEntity.getSource();
    }

    @Override
    public void stop() {
        employers.forEach(u -> {
            u.setBooking(true);
            DataStorage.userRepository.save(u);
        });
        area.setBooking(true);
        DataStorage.areaRepository.save(area);
        entry.setBooking(true);
        DataStorage.entryRepository.save(entry);
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<UserEntity> getEmployers() {
        return employers;
    }

    public void setEmployers(List<UserEntity> employers) {
        this.employers = employers;
    }

    public EntryEntity getEntry() {
        return entry;
    }

    public void setEntry(EntryEntity entry) {
        this.entry = entry;
    }

    public AreaEntity getArea() {
        return area;
    }

    public void setArea(AreaEntity area) {
        this.area = area;
    }

    public AcceptanceEntity getAcceptanceEntity() {
        return acceptanceEntity;
    }

    public void setAcceptanceEntity(AcceptanceEntity acceptanceEntity) {
        this.acceptanceEntity = acceptanceEntity;
    }

    public void acceptCargo(CargounitEntity cargounit) {
    }
    //public void acceptCargo(CargoUnitEntity)
    //public void confirmCargoMove()


    @Override
    public String toString() {
        return "AcceptanceManager{" +
                "employers=" + employers +
                ", entry=" + entry +
                ", area=" + area +
                '}';
    }
}
