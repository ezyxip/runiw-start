package com.ezyxip.runiwstart.UI.loader;

import com.ezyxip.runiwstart.UI.admin.AdsScreen;
import com.ezyxip.runiwstart.UI.components.ExtendTab;
import com.ezyxip.runiwstart.services.OperationManagerHolder;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/spaces/loader")
@RolesAllowed("ROLE_LOADER")
@PageTitle("Грузчик")
public class LoaderUI extends AppLayout {

    @Autowired
    private OperationManagerHolder operationManagerHolder;
    LoaderUI(){
        Label title = new Label("Грузчик");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        addToNavbar(new DrawerToggle(), title);
        addToDrawer(getTabs());
    }

    Tabs getTabs(){
        ExtendTab questList = new ExtendTab();
        questList.add(new Icon(VaadinIcon.EDIT){{getStyle().set("margin", "0.5em");}});
        questList.add("Доступные задачи");
        questList.setCallback(()-> new Label("Список заданий"));

        ExtendTab warnings = new ExtendTab();
        warnings.add(new Icon(VaadinIcon.ENVELOPE_O){{getStyle().set("margin", "0.5em");}});
        warnings.add("Объявления");
        warnings.setCallback(AdsScreen::new);

        ExtendTab exit = new ExtendTab();
        exit.add(new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT){{getStyle().set("margin", "0.5em");}});
        exit.add("Выход");
        exit.setCallback(()->{
            UI.getCurrent().navigate("/nav");
            return new Label("Перенаправление...");
        });


        Tabs res = new Tabs(questList, warnings, exit);
        res.setOrientation(Tabs.Orientation.VERTICAL);
        res.addSelectedChangeListener(
                event -> {
                    setContent(((ExtendTab)res.getSelectedTab()).getCallback().run());
                }
        );
        return res;
    }
}
