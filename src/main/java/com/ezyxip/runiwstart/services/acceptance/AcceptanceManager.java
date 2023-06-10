package com.ezyxip.runiwstart.services.acceptance;


import com.ezyxip.runiwstart.entities.*;
import com.ezyxip.runiwstart.repositories.CargounitRepository;
import com.ezyxip.runiwstart.services.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.*;

public class AcceptanceManager implements OperationManager {

    static public AcceptanceManagerBuilder getBuilder(){
        return new AcceptanceManagerBuilder();
    }

    private String id;
    private List<UserEntity> employers;
    private EntryEntity entry;
    private AreaEntity area;
    private AcceptanceEntity acceptanceEntity;
    private HashMap<String, AgentType> checks;
    private boolean enable = true;


    public AcceptanceManager(List<UserEntity> employers, EntryEntity entry, AreaEntity area, AcceptanceEntity acceptanceEntity) {
        this();
        this.employers = employers;
        this.entry = entry;
        this.area = area;
        this.acceptanceEntity = acceptanceEntity;
        this.checks = new HashMap<>();
    }

    protected AcceptanceManager() {
        id = UUID.randomUUID().toString();
        this.checks = new HashMap<>();
    }

    @Override
    public AgentContainer getAgentContainer(String username) {
        Optional<UserEntity> currentUser = employers.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        if(currentUser.isEmpty()){
            return null;
        }
        AcceptanceAgent acceptanceAgent = new AcceptanceAgent(this, username);
        return new AgentContainer(checks.get(username), acceptanceAgent, "Приёмка",
                String.format("Проведение приёмки у ворот %s, зона %s", entry.getName(), area.getName()));

    }

    @Override
    public void confirmWorkStart(String username) {
        checks.remove(username);
        checks.put(username, AgentType.CONFIRMED);
        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить менеджер приёмки после соглашения к работе сотрудника: " + username);
        }
    }

    @Override
    public boolean complete(String username) {
        Optional<UserEntity> user = employers.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        if(user.isEmpty()) {
            throw new RuntimeException("Попытка завершения задачи работником, которому она не давалась");
        }
         employers = employers.stream().filter(u -> !Objects.equals(u.getUsername(), user.get().getUsername())).toList();
        if(employers.isEmpty()) {
            enable = false;
            stop();
        } else{
            try {
                save();
            } catch (IOException e) {
                throw new RuntimeException("Не удалось сохранить менеджер приёмки после завершения работы сотрудником: " + username);
            }
        }
        return true;
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

        for(UserEntity i : employers){
            checks.put(i.getUsername(), AgentType.UNCONFIRMED);
        }

        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить менеджер приёмки после резервирования ресурсов");
        }
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

        DataStorage.managerRepository.deleteById(getId());

        enable = false;
        employers = new ArrayList<>();

    }

    @Override
    public void save() throws IOException {
        OperationManagerEntity operationManagerEntity = new OperationManagerEntity(
                this.getId(),
                OperationManager.serialize(this)
        );
        DataStorage.managerRepository.save(operationManagerEntity);
    }

    @Override
    public boolean isEnable() {
        return enable;
    }

    public void acceptCargo(CargounitEntity cargounit) {
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

    @Override
    public String toString() {
        return "AcceptanceManager{" +
                "employers=" + employers +
                ", entry=" + entry +
                ", area=" + area +
                '}';
    }
}
