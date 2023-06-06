package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.AcceptanceEntity;
import com.ezyxip.runiwstart.repositories.AcceptanceRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class AcceptanceDetails extends FlexLayout {

    AcceptanceEntity acceptance;

    public AcceptanceDetails(AcceptanceRepository repo){
        Button delete = new Button("Удалить");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        delete.setMinWidth("30%");
        delete.addClickListener(e->{
            Dialog deleteDialog = new Dialog();
            deleteDialog.setHeaderTitle("Удаление");
            deleteDialog.add(new Label("Вы точно хотите удалить запланированную приёмку?"));
            Button deleteYes = new Button("Удалить");
            deleteYes.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteYes.addClickListener(ev->{
                repo.delete(acceptance);
                deleteDialog.close();
            });

            deleteDialog.getFooter().add(deleteYes);

            Button cancel = new Button("Отмена");
            cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            cancel.addClickListener(ev -> deleteDialog.close());

            deleteDialog.getFooter().add(cancel);

            deleteDialog.open();

        });
        Button edit = new Button("Редактировать");
        edit.setMinWidth("30%");
        edit.addClickListener(e->new AcceptanceEdit(acceptance, repo).open());

        Button exec = new Button("Провести сейчас");
        exec.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        exec.setMinWidth("30%");

        setFlexWrap(FlexWrap.WRAP);
        setJustifyContentMode(JustifyContentMode.EVENLY);

        setFlexGrow(0, delete, edit, exec);

        add(delete, edit, exec);
    }

    public void setAccept(AcceptanceEntity entity){
        acceptance = entity;
    }
}
