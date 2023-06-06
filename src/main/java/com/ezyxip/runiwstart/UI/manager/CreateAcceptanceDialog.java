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

public class CreateAcceptanceDialog extends Dialog {
    AcceptanceRepository repo;
    public CreateAcceptanceDialog(AcceptanceRepository repo){
        this.repo = repo;
        setHeaderTitle("Создание");

        Button close = new Button((new Icon(VaadinIcon.CLOSE)));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener(e->close());
        getHeader().add(close);

        TextField source = new TextField("Поставщик");
        DatePicker date = new DatePicker("Дата поставки");
        Button submit = new Button("Создать");
        submit.addClickListener(e -> {
            AcceptanceEntity acceptanceEntity = new AcceptanceEntity(
                    source.getValue(),
                    date.getValue(),
                    false
            );
            repo.save(acceptanceEntity);
            source.clear();
            date.clear();
            close();
        });
        VerticalLayout content = new VerticalLayout();
        content.add(source, date);

        add(content);

        getFooter().add(submit);
    }
}
