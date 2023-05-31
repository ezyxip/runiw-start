package com.ezyxip.runiwstart.UI.components;

import com.ezyxip.runiwstart.entities.UserEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class DUModalDialog extends Dialog {
    public DUModalDialog(UserEntity user){
        setHeaderTitle("Редактирование");

        Button close = new Button(new Icon(VaadinIcon.CLOSE));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener((buttonClickEvent -> {
            close();
        }));

        getHeader().add(close);

        TextField username = new TextField("Имя пользователя");
        username.setValue(user.getUsername());

        TextField password = new TextField("Пароль пользователя");
        password.setValue(user.getPassword());

        VerticalLayout layout = new VerticalLayout();
        layout.add(username, password);

        add(layout);

        Button updateButton = new Button("Изменить");
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.addClickListener(buttonClickEvent -> {
            close();
        });

        Button deleteButton = new Button("Удалить");
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        deleteButton.addClickListener(buttonClickEvent -> {
            close();
        });

        getFooter().add(updateButton, deleteButton);


    }
}
