package com.ezyxip.runiwstart.UI.taskUI;

import com.ezyxip.runiwstart.entities.AcceptanceEntity;
import com.ezyxip.runiwstart.entities.CargotypeEntity;
import com.ezyxip.runiwstart.entities.CargounitEntity;
import com.ezyxip.runiwstart.entities.CellEntity;
import com.ezyxip.runiwstart.services.DataStorage;
import com.ezyxip.runiwstart.services.acceptance.AcceptanceAgent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;


public class AcceptanceTaskScreen extends VerticalLayout {
    Runnable onClose;
    Button complete;

    List<CargounitEntity> acceptanceCargo;

    ArrayList<CellEntity> occupiedCells = new ArrayList<>();
    public AcceptanceTaskScreen(Runnable onClose, AcceptanceAgent agent, AcceptanceEntity acceptance){
        this.onClose = onClose;
        complete = new Button("Закончить");
        complete.addClickListener(e->{
            agent.acceptCargo(acceptanceCargo);
            agent.complete();
            onClose.run();
        });

        acceptanceCargo = new ArrayList<>();

        Grid<CargounitEntity> grid = new Grid<>(CargounitEntity.class, false);
        grid.addColumn(c -> c.getCargotype().getName()).setHeader("Тип груза");
        grid.addColumn(c -> String.format("%d %s", c.getCount(), c.getCargotype().getUnittype().getName())).setHeader("Количество");
        grid.addColumn(c -> String.format(
                "%s-%s-%s",
                c.getCell().getName(),
                c.getCell().getRack_id().getName(),
                c.getCell().getRack_id().getZone_id().getName()
        )).setHeader("Адрес");

        grid.setItems(acceptanceCargo);

        grid.addItemClickListener(e -> {
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Удаление");

            Button close = new Button(new Icon(VaadinIcon.CLOSE));
            close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            close.addClickListener(ev->dialog.close());

            VerticalLayout content = new VerticalLayout();
            Label label = new Label("Удалить данный груз?");

            TextField type = new TextField("Тип");
            type.setValue(e.getItem().getCargotype().getName());
            type.setReadOnly(true);
            type.setWidth("100%");

            TextField count = new TextField("Количество");
            count.setValue(e.getItem().getCargotype().getUnittype().getName());
            count.setReadOnly(true);
            count.setWidth("100%");

            TextField address = new TextField("Адрес");
            address.setValue(String.format(
                    "%s-%s-%s",
                    e.getItem().getCell().getName(),
                    e.getItem().getCell().getRack_id().getName(),
                    e.getItem().getCell().getRack_id().getZone_id().getName()
            ));
            address.setReadOnly(true);
            address.setWidth("100%");

            content.add(label, type, count, address);

            Button delete = new Button("Удалить");
            delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            delete.addClickListener(ev->{
                acceptanceCargo.remove(e.getItem());
                occupiedCells.remove(e.getItem().getCell());
                grid.setItems(acceptanceCargo);
                dialog.close();
            });

            dialog.getHeader().add(close);
            dialog.add(content);
            dialog.getFooter().add(delete);

            dialog.open();
        });

        Button acceptCargo = new Button("Добавить единицу груза");
        acceptCargo.addClickListener(e ->{
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Добавление");

            Button close = new Button(new Icon(VaadinIcon.CLOSE));
            close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            close.addClickListener(ev->dialog.close());

            VerticalLayout content = new VerticalLayout();

            TextField count = new TextField("Количество");
            count.setWidth("100%");

            List<CargotypeEntity> types = DataStorage.cargotypeRepository.findAll();

            ComboBox<CargotypeEntity> type = new ComboBox<>("Тип");
            type.setItems(types);
            type.setWidth("100%");
            type.setValue(types.get(0));
            type.addValueChangeListener(ev-> count.setLabel("Количество (" + ev.getValue().getUnittype().getName() + ")"));

            List<CellEntity> cells = DataStorage.cellRepository.findAll().stream()
                    .filter(c -> c.getCargounit() == null)
                    .filter(c -> occupiedCells.stream().noneMatch((oc) -> oc.toString().equals(c.toString())))
                    .toList();
            ComboBox<CellEntity> address = new ComboBox<>("Ячейка");
            address.setItems(cells);
            address.setValue(cells.get(0));
            address.setWidth("100%");

            content.add(type, count, address);

            Button add = new Button("Добавить");
            add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            add.addClickListener(ev -> {
                CargounitEntity cargounit = new CargounitEntity();
                cargounit.setAcceptance(acceptance);
                cargounit.setCargotype(type.getValue());
                cargounit.setCell(address.getValue());
                cargounit.setCount(Integer.parseInt(count.getValue()));
                acceptanceCargo.add(cargounit);
                grid.setItems(acceptanceCargo);

                occupiedCells.add(address.getValue());

                dialog.close();
            });

            dialog.getHeader().add(close);
            dialog.add(content);
            dialog.getFooter().add(add);

            dialog.open();
        });

        add(acceptCargo, complete,  grid);
    }
}
