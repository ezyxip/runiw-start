package com.ezyxip.runiwstart.services.acceptance;

import com.ezyxip.runiwstart.UI.taskUI.AcceptanceTaskScreen;
import com.ezyxip.runiwstart.entities.CargounitEntity;
import com.ezyxip.runiwstart.services.AbstractAgent;
import com.ezyxip.runiwstart.services.OperationAgent;
import com.vaadin.flow.component.Component;

import java.util.List;

public class AcceptanceAgent extends AbstractAgent {
    
    public AcceptanceAgent(AcceptanceManager parentManager, String username){
        super(parentManager, username);
    }
    
    //Методы для работы агента
    public void acceptCargo(List<CargounitEntity> cargounit){
        ((AcceptanceManager)manager).acceptCargo(cargounit);
    }

    @Override
    public Component getUI(Runnable onClose) {
        return new AcceptanceTaskScreen(onClose, this, ((AcceptanceManager)manager).getAcceptanceEntity());
    }
}
