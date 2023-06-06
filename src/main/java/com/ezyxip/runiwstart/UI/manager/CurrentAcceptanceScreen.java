package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.AcceptanceEntity;
import com.ezyxip.runiwstart.repositories.AcceptanceRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class CurrentAcceptanceScreen extends VerticalLayout {

    static Logger logger = Logger.getLogger(CurrentAcceptanceScreen.class.getName());
    Grid<AcceptanceEntity> grid = new Grid<>(AcceptanceEntity.class, false);

    public CurrentAcceptanceScreen(AcceptanceRepository repo){

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button refreshButton = new Button(new Icon(VaadinIcon.REFRESH));
        refreshButton.addClickListener(e->grid.setItems(repo.findByIsclosedOrderByDate(false)));
        horizontalLayout.add(refreshButton);

        Button createAcceptanceBtn = new Button("Запланировать приёмку");
        createAcceptanceBtn.addClickListener(e->new CreateAcceptanceDialog(repo).open());
        horizontalLayout.add(createAcceptanceBtn);

        add(horizontalLayout);

        grid.addColumn(new ComponentRenderer<Label, AcceptanceEntity>(
                Label::new,
                (label, accept)->{
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    label.setText(format.format(accept.getDate()));
                    if(accept.getDate().equals(LocalDate.now())){
                        label.getStyle()
                                .set("color", "var(--lumo-success-color)");
                    }
                    else if(accept.getDate().isBefore(LocalDate.now())){
                        label.getStyle()
                                .set("color", "var(--lumo-error-color)");
                    }
                }
        )).setHeader("Дата").setSortable(true);
        grid.addColumn(AcceptanceEntity::getSource).setHeader("Поставщик").setSortable(true);
        grid.setAllRowsVisible(true);
        grid.setItems(repo.findByIsclosedOrderByDate(false));

        grid.setItemDetailsRenderer(new ComponentRenderer<>(()->new AcceptanceDetails(repo), AcceptanceDetails::setAccept));

        add(grid);
    }
}
