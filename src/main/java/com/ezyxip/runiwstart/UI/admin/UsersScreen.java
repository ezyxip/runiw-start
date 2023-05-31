package com.ezyxip.runiwstart.UI.admin;

import com.ezyxip.runiwstart.UI.components.DUModalDialog;
import com.ezyxip.runiwstart.data.UserRepository;
import com.ezyxip.runiwstart.entities.UserEntity;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableBiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class UsersScreen extends AppLayout {


    public UsersScreen(UserRepository userRepository){
        Iterable<UserEntity> iterable = userRepository.findAll();
        List<UserEntity> users = new ArrayList<>();
        for(UserEntity i : iterable){
            users.add(i);
        }
        Grid<UserEntity> grid = new Grid<>();
        grid.addColumn(UserEntity::getUsername).setHeader("Имя пользователя");
        grid.addColumn(new ComponentRenderer<Span, UserEntity>(
                Span::new,
                (span, user) ->{
                    span.setText(user.isEnabled()? "online" : "offline");
                    span.getStyle().set("background-color", user.isEnabled()?"var(--lumo-primary-color)" : "var(--lumo-error-color)")
                            .set("padding", "10px")
                            .set("color", "white")
                            .set("border-radius", "5px");

                }
        )).setHeader("Статус");
        grid.addColumn(new ComponentRenderer<Button, UserEntity>(
                Button::new,
                (btn, user) ->{
                    btn.setIcon(new Icon(VaadinIcon.EDIT));
                    btn.addClickListener((event ->{
                        new DUModalDialog(user).open();
                    }));
                }
        ));
        grid.setAllRowsVisible(true);
        grid.setItems(users);
        setContent(grid);

    }
}
