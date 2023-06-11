package com.ezyxip.runiwstart.services.cargoselect;

import com.ezyxip.runiwstart.UI.taskUI.CargoSelectTaskUI;
import com.ezyxip.runiwstart.entities.CargoBookingEntity;
import com.ezyxip.runiwstart.entities.OrderEntity;
import com.ezyxip.runiwstart.services.AbstractAgent;
import com.ezyxip.runiwstart.services.DataStorage;
import com.ezyxip.runiwstart.services.OperationManager;
import com.vaadin.flow.component.Component;

import java.util.List;

public class CargoSelectAgent extends AbstractAgent {

    public CargoSelectAgent(OperationManager parentManager, String username) {
        super(parentManager, username);
    }

    @Override
    public Component getUI(Runnable onClose) {
        return new CargoSelectTaskUI(this, onClose);
    }

    public List<CargoBookingEntity> getCargoUnits(){
        OrderEntity order = ((CargoSelectManager)manager).getOrder();
        return DataStorage.cargoBookingRepository.findAllByOrders(order);
    }
}
