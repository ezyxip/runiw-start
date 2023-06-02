package com.ezyxip.runiwstart.UI.admin;

import com.ezyxip.runiwstart.data.AuthorityRepository;
import com.ezyxip.runiwstart.data.UserRepository;
import com.ezyxip.runiwstart.entities.AuthorityEntity;
import com.ezyxip.runiwstart.entities.UserEntity;
import com.ezyxip.runiwstart.services.RoleDict;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UsersScreen extends AppLayout {

    static Logger logger = Logger.getLogger(UsersScreen.class.getName());

    public UsersScreen(UserRepository userRepository, AuthorityRepository authorityRepository){
        Iterable<UserEntity> userIterable = userRepository.findAll();
        Iterable<AuthorityEntity> authorityIterable = authorityRepository.findAll();
        List<UserEntity> users = new ArrayList<>();
        for(UserEntity i : userIterable){
            i.setAuthorities(
                    StreamSupport.stream(authorityIterable.spliterator(), false)
                            .filter( authorityEntity -> i.getUsername().equals(authorityEntity.getId().getUsername()))
                            .collect(Collectors.toList())
            );
            users.add(i);
        }
        Grid<UserEntity> grid = new Grid<>();
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.addColumn(UserEntity::getUsername).setHeader("Имя пользователя");
        grid.addColumn(new ComponentRenderer<Span, UserEntity>(
                Span::new,
                (span, user) -> {
                    AtomicReference<String> rolestr = new AtomicReference<>("");
                     StreamSupport.stream(authorityIterable.spliterator(), false)
                            .filter(authorityEntity -> authorityEntity.getId().getUsername().equals(user.getUsername()))
                            .forEach(authorityEntity -> {
                                rolestr.updateAndGet(v -> v + new RoleDict().get(authorityEntity.getId().getAuthority()).getValue1() + ";\n");
                            });
                     span.setText(rolestr.get());
                }
        )).setHeader("Роли");
        grid.addColumn(new ComponentRenderer<Button, UserEntity>(
                Button::new,
                (btn, user) ->{
                    btn.setIcon(new Icon(VaadinIcon.EDIT));
                    btn.addClickListener((event ->{
                        new DUModalDialog(user, authorityRepository, userRepository).open();
                    }));

                }
        ));
        grid.setAllRowsVisible(true);
        grid.setItems(users);

        Button createUser = new Button("Создать нового пользователя");
        createUser.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        createUser.addClickListener(buttonClickEvent -> {
            new CreateUserDialog(authorityRepository, userRepository).open();
        });

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(grid, createUser);
        setContent(verticalLayout);

    }
}
