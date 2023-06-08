package com.ezyxip.runiwstart.services.acceptance;

import com.ezyxip.runiwstart.entities.CargounitEntity;
import com.ezyxip.runiwstart.services.OperationAgent;
import com.vaadin.flow.component.Component;

public class AcceptanceAgent implements OperationAgent {
    
    protected AcceptanceManager manager;
    protected String username;
    
    public AcceptanceAgent(AcceptanceManager parentManager, String username){
        this.manager = parentManager;
        this.username = username;
    }
    
    //Методы для работы агента
    public void acceptCargo(CargounitEntity cargounit){
        manager.acceptCargo(cargounit);
    }
    
    @Override
    public void complete() {
        manager.complete(username);
    }

    @Override
    public Component getUI() {
        return null;
    }

    @Override
    public void confirmStart() {
        manager.confirmWorkStart(username);
    }
}
