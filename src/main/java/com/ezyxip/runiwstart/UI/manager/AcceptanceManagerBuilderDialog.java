package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.AreaEntity;
import com.ezyxip.runiwstart.entities.EntryEntity;
import com.ezyxip.runiwstart.entities.UserEntity;
import com.ezyxip.runiwstart.services.DataStorage;
import com.ezyxip.runiwstart.services.OperationManagerHolder;
import com.ezyxip.runiwstart.services.acceptance.AcceptanceManager;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;

public class AcceptanceManagerBuilderDialog extends Dialog{

    AcceptanceManagerBuilderDialog(OperationManagerHolder operationManagerHolder){
        try {
            AcceptanceManager acceptanceManager = operationManagerHolder.createAcceptanceManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setHeaderTitle("Утверждение приёмки");
        VerticalLayout content = new VerticalLayout();

        ComboBox<EntryEntity> entry = new ComboBox<>("Ворота:");
        ComboBox<AreaEntity> area = new ComboBox<>("Зона приёмки");
        ComboBox<UserEntity> user = new ComboBox<>("Рабочие");

        content.add(entry, area, user);

        add(content);

        Button confirm = new Button("Подтвердить");
        Button cancel = new Button("Отмена");
        cancel.addClickListener(e->close());

        getFooter().add(confirm, cancel);
    }
}
