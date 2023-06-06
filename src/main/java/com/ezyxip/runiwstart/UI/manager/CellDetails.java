package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.CellConstraintEntity;
import com.ezyxip.runiwstart.entities.CellEntity;
import com.ezyxip.runiwstart.entities.RackConstraintEntity;
import com.ezyxip.runiwstart.entities.ZoneConstraintEntity;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;


public class CellDetails extends VerticalLayout {

    Grid<CellConstraintEntity> cellConstraintGrid = new Grid<>(CellConstraintEntity.class, false);
    Grid<RackConstraintEntity> rackConstraintGrid = new Grid<>(RackConstraintEntity.class, false);
    Grid<ZoneConstraintEntity> zoneConstraintGrid = new Grid<>(ZoneConstraintEntity.class, false);

    CellDetails(){
        Label cellConstraintTitle = new Label("Ограничения ячейки");
        cellConstraintTitle.getStyle()
                .set("color", "var(--lumo-primary-color)");
        Label rackConstraintTitle = new Label("Ограничения стеллажа");
        rackConstraintTitle.getStyle()
                .set("color", "var(--lumo-primary-color)");
        Label zoneConstraintTitle = new Label("Ограничения зоны");
        zoneConstraintTitle.getStyle()
                .set("color", "var(--lumo-primary-color)");

        cellConstraintGrid.addColumn(constraint -> constraint.getConstraints_value().getConstraints().getName())
                .setHeader("Ограничение");
        cellConstraintGrid.addColumn(constraint -> constraint.getConstraints_value().getValue())
                .setHeader("Значение");
        rackConstraintGrid.addColumn(constraint -> constraint.getConstraints_value().getConstraints().getName())
                .setHeader("Ограничение");
        rackConstraintGrid.addColumn(constraint -> constraint.getConstraints_value().getValue())
                .setHeader("Значение");
        zoneConstraintGrid.addColumn(constraint -> constraint.getConstraints_value().getConstraints().getName())
                .setHeader("Ограничение");
        zoneConstraintGrid.addColumn(constraint -> constraint.getConstraints_value().getValue())
                .setHeader("Значение");

        cellConstraintGrid.setAllRowsVisible(true);
        cellConstraintGrid.addThemeVariants(GridVariant.LUMO_COMPACT);
        rackConstraintGrid.setAllRowsVisible(true);
        rackConstraintGrid.addThemeVariants(GridVariant.LUMO_COMPACT);
        zoneConstraintGrid.setAllRowsVisible(true);
        zoneConstraintGrid.addThemeVariants(GridVariant.LUMO_COMPACT);

        add(cellConstraintTitle);
        add(cellConstraintGrid);
        add(rackConstraintTitle);
        add(rackConstraintGrid);
        add(zoneConstraintTitle);
        add(zoneConstraintGrid);

    }

    void setCell(CellEntity cell){
        List<CellConstraintEntity> cellConstraints = cell.getConstraints();
        cellConstraintGrid.setItems(cellConstraints);
        List<RackConstraintEntity> rackConstraints = cell.getRack_id().getConstraints();
        rackConstraintGrid.setItems(rackConstraints);
        List<ZoneConstraintEntity> zoneConstraints = cell.getRack_id().getZone_id().getConstraints();
        zoneConstraintGrid.setItems(zoneConstraints);
    }

}
