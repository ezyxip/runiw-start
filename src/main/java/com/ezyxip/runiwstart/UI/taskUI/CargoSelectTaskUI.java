package com.ezyxip.runiwstart.UI.taskUI;

import com.ezyxip.runiwstart.entities.CargoBookingEntity;
import com.ezyxip.runiwstart.entities.OrderEntity;
import com.ezyxip.runiwstart.services.cargoselect.CargoSelectAgent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CargoSelectTaskUI extends VerticalLayout {
    public CargoSelectTaskUI(CargoSelectAgent cargoSelectAgent, Runnable onClose) {
        Grid<CargoBookingEntity> grid = new Grid<>(CargoBookingEntity.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(cb -> cb.getCargounit().getCell().toString()).setHeader("Ячейка");
        grid.addColumn(cb -> cb.getCount() == cb.getCargounit().getCount() ?
                "Полностью" : String.format("%d %s", cb.getCount(), cb.getCargounit().getCargotype().getUnittype().getName()))
                .setHeader("Количество");

        grid.setItems(cargoSelectAgent.getCargoUnits());

        Button complete = new Button("Закончить");
        complete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        complete.addClickListener(e -> {
            cargoSelectAgent.complete();
            onClose.run();
        });

        add(grid, complete);
    }
}
