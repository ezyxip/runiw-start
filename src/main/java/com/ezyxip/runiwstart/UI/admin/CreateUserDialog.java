package com.ezyxip.runiwstart.UI.admin;

import com.ezyxip.runiwstart.repositories.AuthorityRepository;
import com.ezyxip.runiwstart.repositories.UserRepository;
import com.ezyxip.runiwstart.entities.AuthorityEntity;
import com.ezyxip.runiwstart.entities.AuthorityEntityId;
import com.ezyxip.runiwstart.entities.UserEntity;
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
import java.util.logging.Logger;

public class CreateUserDialog extends Dialog {
    static Logger logger = Logger.getLogger(CreateUserDialog.class.getName());
    TextField username = new TextField("Имя пользователя");
    TextField password = new TextField("Пароль пользователя");
    MultiSelectComboBox<AuthorityEntity> roleBox = new MultiSelectComboBox<>("Роли");

    public CreateUserDialog(AuthorityRepository authRepository, UserRepository userRepository){
        List<AuthorityEntity> finalAuthorities = new ArrayList<>();
        finalAuthorities.add(new AuthorityEntity("", "ROLE_ADMIN"));
        finalAuthorities.add(new AuthorityEntity("", "ROLE_ANALYST"));
        finalAuthorities.add(new AuthorityEntity("", "ROLE_MANAGER"));
        finalAuthorities.add(new AuthorityEntity("", "ROLE_LOADER"));

        setHeaderTitle("Редактирование");

        Button close = new Button(new Icon(VaadinIcon.CLOSE));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener((buttonClickEvent -> {
            close();
        }));

        getHeader().add(close);

        username.getStyle().set("min-width", "15em");


        password.getStyle().set("min-width", "15em");

        logger.info(finalAuthorities.toString());
        roleBox.setItems(finalAuthorities);
        roleBox.getStyle().set("min-width", "15em");

        VerticalLayout layout = new VerticalLayout();
        layout.add(username, password, roleBox);

        add(layout);

        Button createUser = new Button("Создать");
        createUser.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        createUser.addClickListener(buttonClickEvent -> {
            UserEntity newUser = getUserModel();
            userRepository.save(newUser);
            for(AuthorityEntity i : newUser.getAuthorities()){
                authRepository.save(i);
            }
            close();
        });

        getFooter().add(createUser);

    }

    public UserEntity getUserModel(){
        UserEntity res = new UserEntity();
        res.setUsername(username.getValue());
        res.setPassword((password.getValue()));
        ArrayList<AuthorityEntity> authorities = new ArrayList<>();
        for(AuthorityEntity i : roleBox.getSelectedItems()){
            i.setId(new AuthorityEntityId(res.getUsername(), i.getId().getAuthority()));
            authorities.add(i);
        }
        res.setAuthorities(authorities);
        logger.info(res.toString());
        return res;
    }
}
