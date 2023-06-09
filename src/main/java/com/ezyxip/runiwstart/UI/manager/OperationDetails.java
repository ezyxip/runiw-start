package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.services.OperationManager;
import com.ezyxip.runiwstart.services.OperationManagerHolder;
import com.ezyxip.runiwstart.services.acceptance.AcceptanceManager;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.util.concurrent.atomic.AtomicReference;


public class OperationDetails extends VerticalLayout {
    OperationManagerHolder operationManagerHolder;
    OperationDetails(OperationManagerHolder operationManagerHolder){
        this.operationManagerHolder = operationManagerHolder;
    }

    public void setOperation(OperationManager operationManager){
        if(operationManager instanceof AcceptanceManager acceptanceManager){
            TextField entry = new TextField("Вход");
            entry.setReadOnly(true);
            entry.setValue(acceptanceManager.getEntry().getName());

            TextField area = new TextField("Площадка");
            area.setReadOnly(true);
            area.setValue(acceptanceManager.getArea().getName());

            TextArea employers = new TextArea("Сотрудники");
            employers.setReadOnly(true);
            AtomicReference<String> emps = new AtomicReference<>("");
            acceptanceManager.getEmployers().forEach(e -> emps.updateAndGet(v -> v + e.getUsername() + "; ") );
            employers.setValue(emps.get());

            add(entry, area, employers);
        }

        Button stop = new Button("Остановить операцию");
        stop.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        stop.addClickListener(e -> {
            Dialog confirmDialog = new Dialog();
            confirmDialog.setHeaderTitle("Остановка");
            confirmDialog.add(new Label("Произойдёт немедленная остановка " +
                    "операции, будут освобождены ресурсы. Вы уверены?"));
            Button confirm = new Button("Остановить");
            confirm.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
            confirm.addClickListener(ev->{
                operationManagerHolder.stopManager(operationManager);
                confirmDialog.close();
            });

            Button cancel = new Button("Отмена");
            cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            cancel.addClickListener(ev->confirmDialog.close());

            confirmDialog.getFooter().add(confirm, cancel);
            confirmDialog.open();
        });

        add(stop);
    }
}
