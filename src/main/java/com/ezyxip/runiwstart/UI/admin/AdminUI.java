package com.ezyxip.runiwstart.UI.admin;

import com.ezyxip.runiwstart.UI.admin.ads.AdsScreen;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;


@Route("/spaces/admin")
@RolesAllowed("ROLE_ADMIN")
@PageTitle("Панель администратора")
public class AdminUI extends AppLayout {

    AdminUI(){
        Label title = new Label("Администратор");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        addToNavbar(new DrawerToggle(), title);
        addToDrawer(getTabs());
        setContent(new Div(new Text("Ну, в общем, склад работает... а может и нет")) {{getStyle().set("margin", "1em");}});
    }

    Tabs getTabs(){
        Tab generalInfo = new Tab();
        generalInfo.add(new Icon(VaadinIcon.EDIT){{getStyle().set("margin", "0.5em");}});
        generalInfo.add("Общие сведения");

        Tab warnings = new Tab();
        warnings.add(new Icon(VaadinIcon.ENVELOPE_O){{getStyle().set("margin", "0.5em");}});
        warnings.add("Объявления");

        Tab exit = new Tab();
        exit.add(new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT){{getStyle().set("margin", "0.5em");}});
        exit.add("Выход");


        Tabs res = new Tabs(generalInfo, warnings, exit);
        res.setOrientation(Tabs.Orientation.VERTICAL);
        res.addSelectedChangeListener(
                event -> {
                    if(event.getSelectedTab() == warnings){
                        setContent(new AdsScreen());
                    }else if(event.getSelectedTab() == generalInfo) {
                        setContent(new Label("Ну, в общем, склад работает... а может и нет"));
                    }else if(event.getSelectedTab() == exit) {
                        UI.getCurrent().navigate("/nav");
                    }
                }
        );
        return res;
    }
}
