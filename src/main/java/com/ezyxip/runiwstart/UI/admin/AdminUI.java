package com.ezyxip.runiwstart.UI.admin;

import com.ezyxip.runiwstart.UI.components.ExtendTab;
import com.ezyxip.runiwstart.data.UserRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;


@Route("/spaces/admin")
@RolesAllowed("ROLE_ADMIN")
@PageTitle("Панель администратора")

public class AdminUI extends AppLayout {

    @Autowired
    UserRepository userRepository;

    AdminUI(){
        Label title = new Label("Администратор");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        addToNavbar(new DrawerToggle(), title);
        addToDrawer(getTabs());
        setContent(new Div(new Text("Ну, в общем, склад работает... а может и нет")) {{getStyle().set("margin", "1em");}});
    }

    Tabs getTabs(){
        ExtendTab generalInfo = new ExtendTab();
        generalInfo.add(new Icon(VaadinIcon.EDIT){{getStyle().set("margin", "0.5em");}});
        generalInfo.add("Общие сведения");
        generalInfo.setCallback(()-> new Label("Ну, в общем, склад работает... а может и нет"));

        ExtendTab users = new ExtendTab();
        users.add(new Icon(VaadinIcon.USERS){{getStyle().set("margin", "0.5em");}});
        users.add("Пользователи");
        users.setCallback(()-> new UsersScreen(userRepository));

        ExtendTab warehouseModel = new ExtendTab();
        warehouseModel.add(new Icon(VaadinIcon.HOME_O){{getStyle().set("margin", "0.5em");}});
        warehouseModel.add("Модель склада");
        warehouseModel.setCallback(()-> new Label("Загрузка модели склада"));

        ExtendTab warnings = new ExtendTab();
        warnings.add(new Icon(VaadinIcon.ENVELOPE_O){{getStyle().set("margin", "0.5em");}});
        warnings.add("Объявления");
        warnings.setCallback(() -> new AdsScreen());

        ExtendTab exit = new ExtendTab();
        exit.add(new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT){{getStyle().set("margin", "0.5em");}});
        exit.add("Выход");
        exit.setCallback(()->{
            UI.getCurrent().navigate("/nav");
            return new Label("Перенаправление...");
        });


        Tabs res = new Tabs(generalInfo, users, warehouseModel, warnings, exit);
        res.setOrientation(Tabs.Orientation.VERTICAL);
        res.addSelectedChangeListener(
                event -> {
                    setContent(((ExtendTab)res.getSelectedTab()).getCallback().run());
                }
        );
        return res;
    }
}
