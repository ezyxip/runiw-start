package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.*;
import com.ezyxip.runiwstart.services.DataStorage;
import com.ezyxip.runiwstart.services.OperationManagerHolder;
import com.ezyxip.runiwstart.services.acceptance.AcceptanceManager;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AcceptanceManagerBuilderDialog extends Dialog{

    AcceptanceManagerBuilderDialog(OperationManagerHolder operationManagerHolder, AcceptanceEntity acceptanceEntity){
        try {
            AcceptanceManager acceptanceManager = operationManagerHolder.createAcceptanceManager(acceptanceEntity);

            setHeaderTitle("Утверждение приёмки");
            Button close = new Button(new Icon(VaadinIcon.CLOSE));
            close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            close.addClickListener(e->close());
            getHeader().add(close);

            VerticalLayout content = new VerticalLayout();

            ComboBox<EntryEntity> entry = new ComboBox<>("Ворота:");
            ComboBox<AreaEntity> area = new ComboBox<>("Зона приёмки");
            MultiSelectComboBox<UserEntity> user = new MultiSelectComboBox<>("Рабочие");

            entry.setItems(DataStorage.entryRepository.findAllByModeAndBooking("in", true));
            entry.setValue(acceptanceManager.getEntry());
            entry.addAttachListener(e->{
                if(getFlag()) area.setItems(entry.getValue().getAreas().stream().map(EntryToAreaRelation::getArea).toList());
            });

            area.setItems(entry.getValue().getAreas().stream().map(EntryToAreaRelation::getArea).toList());

            List<UserEntity> users = DataStorage.userRepository.findAllByBooking(true).stream().toList();
            users = users.stream().filter(u -> {
                return DataStorage.authorityRepository.findAllById_Username(u.getUsername()).stream().anyMatch(a-> a.getAuthority().equals("ROLE_LOADER"));
            }).toList();

            user.setItems(users);
            user.setValue(acceptanceManager.getEmployers());


            content.add(entry, area, user);

            area.setValue(acceptanceManager.getArea());
            add(content);

            Button confirm = new Button("Подтвердить");
            confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            confirm.addClickListener(e->{
                acceptanceManager.setEmployers(user.getSelectedItems().stream().toList());
                acceptanceManager.setArea(area.getValue());
                acceptanceManager.setEntry(entry.getValue());

                operationManagerHolder.initManager(acceptanceManager);

                acceptanceEntity.setIsClosed(true);
                DataStorage.acceptanceRepository.save(acceptanceEntity);

                close();
            });

            Button cancel = new Button("Отмена");
            cancel.addClickListener(e->close());

            getFooter().add(confirm, cancel);

        } catch (Exception e) {
            setHeaderTitle("Ошибка");
            add(new Label("Произошла ошибка: " + e.toString()));
        }
    }

    boolean flag = false;
    boolean getFlag(){
        boolean res = flag;
        flag = true;
        return res;
    }
}
