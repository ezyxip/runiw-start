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

public class DUModalDialog extends Dialog {
    static Logger logger = Logger.getLogger(DUModalDialog.class.getName());
    TextField username = new TextField("Имя пользователя");
    TextField password = new TextField("Пароль пользователя");
    MultiSelectComboBox<AuthorityEntity> roleBox = new MultiSelectComboBox<>("Роли");
    public DUModalDialog(UserEntity user, AuthorityRepository authRepository, UserRepository userRepository){
//        List<String> authorities = new ArrayList<>();
//        StreamSupport.stream(authRepository.findAll().spliterator(), false)
//                .forEach(authorityEntity -> authorities.add(authorityEntity.getId().getAuthority()));
////        authorities = authorities.stream().distinct().collect(Collectors.toList());
//        List<AuthorityEntity> finalAuthorities = new ArrayList<>();
//        authorities.stream()
//                .distinct()
//                .forEach(a -> {
//                    AuthorityEntity authority = new AuthorityEntity();
//                    authority.setId(new AuthorityEntityId("", a));
//                    finalAuthorities.add(authority);
//                });

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

        username.setValue(user.getUsername());
        username.getStyle().set("min-width", "15em");


        password.setValue(user.getPassword());
        password.getStyle().set("min-width", "15em");

        logger.info(finalAuthorities.toString());
        roleBox.setItems(finalAuthorities);
        roleBox.getStyle().set("min-width", "15em");



        for(AuthorityEntity i : user.getAuthorities()){
            for( AuthorityEntity j : finalAuthorities) {
                if(i.equals(j)) roleBox.select(j);
            }
        }

        VerticalLayout layout = new VerticalLayout();
        layout.add(username, password, roleBox);

        add(layout);

        Button updateButton = new Button("Изменить");
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.addClickListener(buttonClickEvent -> {
            UserEntity upUser = getUserModel();
            for(AuthorityEntity i : user.getAuthorities()){
                authRepository.delete(i);
            }
            userRepository.delete(user);
            userRepository.save(upUser);
            for(AuthorityEntity i : upUser.getAuthorities()){
                authRepository.save(i);
            }
            close();
        });

        Button deleteButton = new Button("Удалить");
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        deleteButton.addClickListener(buttonClickEvent -> {
            for(AuthorityEntity i : user.getAuthorities()){
                authRepository.delete(i);
            }
            userRepository.delete(user);
            close();
        });

        getFooter().add(updateButton, deleteButton);

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
