package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.AcceptanceEntity;
import com.ezyxip.runiwstart.repositories.AcceptanceRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class CreateAcceptanceScreen extends VerticalLayout {

    AcceptanceRepository repo;

    public CreateAcceptanceScreen(AcceptanceRepository repo){
        this.repo = repo;
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
        });

        add(source, date, submit);
    }

}
