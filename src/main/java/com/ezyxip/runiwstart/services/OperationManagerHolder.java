package com.ezyxip.runiwstart.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OperationManagerHolder {
    private AcceptanceAreaSelectionStrategy acceptanceAreaSelectionStrategy;
    private  EntrySelectionStrategy entrySelectionStrategy;
    private PersonnelSelectionStrategy personnelSelectionStrategy;
    private CargoReleaseSelectionStrategy cargoReleaseSelectionStrategy;
    private ArrayList<OperationManager> managers;



    public OperationManagerHolder(AcceptanceAreaSelectionStrategy acceptanceAreaSelectionStrategy, EntrySelectionStrategy entrySelectionStrategy, PersonnelSelectionStrategy personnelSelectionStrategy) {
        this.acceptanceAreaSelectionStrategy = acceptanceAreaSelectionStrategy;
        this.entrySelectionStrategy = entrySelectionStrategy;
        this.personnelSelectionStrategy = personnelSelectionStrategy;
    }

    public OperationManagerHolder() {
    }

    /*
        Методы, занимающиеся созданием соотвествующих менеджеров операций. После все они будут храниться в холдере
    */
    //public AcceptanceManager createAcceptanceManager()
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
