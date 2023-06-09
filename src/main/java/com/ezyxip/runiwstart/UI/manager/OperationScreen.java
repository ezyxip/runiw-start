package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.services.OperationManager;
import com.ezyxip.runiwstart.services.OperationManagerHolder;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class OperationScreen extends VerticalLayout {
    OperationScreen(OperationManagerHolder operationManagerHolder){
        Grid<OperationManager> grid = new Grid<>(OperationManager.class, false);
        grid.addColumn(OperationManager::getTitle);

        grid.setItems(operationManagerHolder.getManagers());

        grid.setAllRowsVisible(true);
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.setItemDetailsRenderer(new ComponentRenderer<>(()->new OperationDetails(operationManagerHolder), OperationDetails::setOperation));
        add(grid);
    }
}
