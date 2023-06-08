package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.EntryEntity;
import com.ezyxip.runiwstart.repositories.CargounitRepository;
import com.ezyxip.runiwstart.services.acceptance.AcceptanceManager;
import com.ezyxip.runiwstart.services.acceptance.AcceptanceManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OperationManagerHolder {
    private AcceptanceAreaSelectionStrategy acceptanceAreaSelectionStrategy;
    private  EntrySelectionStrategy entrySelectionStrategy;
    private PersonnelSelectionStrategy personnelSelectionStrategy;
    private CargoReleaseSelectionStrategy cargoReleaseSelectionStrategy;

    private final CargounitRepository cargounitRepository;
    private final ArrayList<OperationManager> managers;

    public OperationManagerHolder(
            @Autowired AcceptanceAreaSelectionStrategy acceptanceAreaSelectionStrategy,
            @Autowired EntrySelectionStrategy entrySelectionStrategy,
            @Autowired PersonnelSelectionStrategy personnelSelectionStrategy,
            @Autowired CargounitRepository cargounitRepository
            ) {
        this.acceptanceAreaSelectionStrategy = acceptanceAreaSelectionStrategy;
        this.entrySelectionStrategy = entrySelectionStrategy;
        this.personnelSelectionStrategy = personnelSelectionStrategy;
        this.cargounitRepository = cargounitRepository;

        managers = new ArrayList<>();
    }

    /*
       Методы, занимающиеся созданием соотвествующих менеджеров операций. После все они будут храниться в холдере
    */
    public AcceptanceManager createAcceptanceManager() throws Exception {
        EntryEntity entry = entrySelectionStrategy.getEntry();
        AcceptanceManager acceptanceManager = AcceptanceManager.getBuilder()
                .setEmployers(personnelSelectionStrategy.getEmployer(1, "ROLE_LOADER").get(0))
                .setArea(acceptanceAreaSelectionStrategy.getArea(entry))
                .setEntry(entry)
                .setRepository(cargounitRepository)
                .build();
        managers.add(acceptanceManager);
        return acceptanceManager;
    }
    //public CargoMoveManager createCargoMoveManager()
    //public CargoSelectionManager createCargoSelectionManager()
    //public CargoReleaseManager createCargoReleaseManager()

    //public AgentContainer getAgent()


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
}
