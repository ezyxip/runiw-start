package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.AcceptanceEntity;
import com.ezyxip.runiwstart.services.DataStorage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.format.DateTimeFormatter;

public class AcceptanceArchive extends VerticalLayout {
    public AcceptanceArchive(){
        Grid<AcceptanceEntity> acceptanceEntityGrid = new Grid<>(AcceptanceEntity.class, false);
        acceptanceEntityGrid.setItems(DataStorage.acceptanceRepository.findByIsclosedOrderByDate(true));
        acceptanceEntityGrid.addColumn(a -> a.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).setHeader("Дата").setSortable(true);
        acceptanceEntityGrid.addColumn(AcceptanceEntity::getSource).setHeader("Поставщик").setSortable(true);

        acceptanceEntityGrid.setAllRowsVisible(true);

        Button refreshButton = new Button(new Icon(VaadinIcon.REFRESH));
        refreshButton.addClickListener(e->acceptanceEntityGrid.setItems(DataStorage.acceptanceRepository.findByIsclosedOrderByDate(true)));

        add(refreshButton, acceptanceEntityGrid);
    }
}
