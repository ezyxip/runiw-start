package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.services.NomenclaturePosition;
import com.ezyxip.runiwstart.services.WarehouseMonitor;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ManagerGeneralInfo extends VerticalLayout {
    public ManagerGeneralInfo(){
        Label label1 = new Label("Остаток на складе");
        WarehouseMonitor warehouseMonitor = new WarehouseMonitor();
        Grid<NomenclaturePosition> grid = new Grid<>(NomenclaturePosition.class, false);
        grid.addColumn(NomenclaturePosition::getCargoType).setHeader("Тип груза");
        grid.addColumn(NomenclaturePosition::getFormattedCount).setHeader("Количество");

        grid.setAllRowsVisible(true);

        grid.setItems(warehouseMonitor.getStocks());

        add(label1, grid);

        Label label2 = new Label("Зарезервированный под заказы груз");
        Grid<NomenclaturePosition> grid2 = new Grid<>(NomenclaturePosition.class, false);
        grid2.addColumn(NomenclaturePosition::getCargoType).setHeader("Тип груза");
        grid2.addColumn(NomenclaturePosition::getFormattedCount).setHeader("Количество");

        grid2.setAllRowsVisible(true);

        grid2.setItems(warehouseMonitor.getReservedCargo());

        add(label2, grid2);
    }
}
