package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.CargounitEntity;
import com.ezyxip.runiwstart.services.DataStorage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class CargoViewerScreen extends VerticalLayout {
    public CargoViewerScreen(){
        Grid<CargounitEntity> grid = new Grid<>(CargounitEntity.class, false);
        grid.setAllRowsVisible(true);

        grid.addColumn(c -> c.getCell().toString()).setHeader("Адрес ячейки");
        grid.addColumn(c -> c.getCargotype().getName()).setHeader("Груз");
        grid.addColumn(c -> String.format("%d %s", c.getCount(), c.getCargotype().getUnittype().getName())).setHeader("Количество");
        grid.addColumn(c -> c.getAcceptance().getSource()).setHeader("Поставщик");

        grid.setItems(DataStorage.cargounitRepository.findAll());

        add(grid);
    }
}
