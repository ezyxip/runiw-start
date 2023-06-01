package com.ezyxip.runiwstart.UI.admin;

import com.ezyxip.runiwstart.data.AuthorityRepository;
import com.ezyxip.runiwstart.entities.AuthorityEntity;
import com.ezyxip.runiwstart.entities.UserEntity;
import com.ezyxip.runiwstart.services.RoleDict;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DUModalDialog extends Dialog {
    TextField username = new TextField("Имя пользователя");
    TextField password = new TextField("Пароль пользователя");
    MultiSelectComboBox<String> roleBox = new MultiSelectComboBox<>("Роли");
    public DUModalDialog(UserEntity user, AuthorityRepository repository){
        List<String> authorities = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(authorityEntity -> authorities.add(authorityEntity.getId().getAuthority()));
//        authorities = authorities.stream().distinct().collect(Collectors.toList());
        List<String> finalAuthorities = authorities.stream()
                .distinct()
                .map(s -> {return new RoleDict().get(s).getValue1();})
                .collect(Collectors.toList());

        setHeaderTitle("Редактирование");

        Button close = new Button(new Icon(VaadinIcon.CLOSE));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener((buttonClickEvent -> {
            close();
        }));

        getHeader().add(close);

        username.setValue(user.getUsername());
        username.getStyle().set("min-width", "15em");


        password.setValue(user.getPassword());
        password.getStyle().set("min-width", "15em");


        roleBox.setItems(finalAuthorities);
        roleBox.getStyle().set("min-width", "15em");

        for(AuthorityEntity i : user.getAuthorities()){
            roleBox.select(new RoleDict().get(i.getId().getAuthority()).getValue1());
        }

        VerticalLayout layout = new VerticalLayout();
        layout.add(username, password, roleBox);

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

    public UserEntity getUserModel(){
        return new UserEntity(){{
            setUsername(username.getValue());
            setPassword((password.getValue()));
            ArrayList<AuthorityEntity> authorities = new ArrayList<>();
            for(String i : roleBox.getSelectedItems()){
                i.
            }
        }}
    }
}
