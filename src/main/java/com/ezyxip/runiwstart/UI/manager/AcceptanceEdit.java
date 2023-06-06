package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.AcceptanceEntity;
import com.ezyxip.runiwstart.repositories.AcceptanceRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class AcceptanceEdit extends Dialog {

    public AcceptanceEdit(AcceptanceEntity entity, AcceptanceRepository repo){
        setHeaderTitle("Редактирование");

        Button close = new Button((new Icon(VaadinIcon.CLOSE)));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener(e->close());
        getHeader().add(close);

        TextField source = new TextField("Поставщик");
        DatePicker date = new DatePicker("Дата поставки");

        source.setValue(entity.getSource());
        date.setValue(entity.getDate());

        VerticalLayout content = new VerticalLayout();
        content.add(source);
        content.add(date);

        Button change = new Button("Изменить");
        change.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        change.addClickListener(e->{
           repo.delete(entity);
           AcceptanceEntity newEntity = new AcceptanceEntity(
                   source.getValue(),
                   date.getValue(),
                   false
           );
           repo.save(newEntity);
           close();
        });

        add(content);

        getFooter().add(change);
    }
}
