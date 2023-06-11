package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.UI.components.ExtendTab;
import com.ezyxip.runiwstart.UI.components.MessengerScreen;
import com.ezyxip.runiwstart.repositories.AcceptanceRepository;
import com.ezyxip.runiwstart.repositories.CellRepository;
import com.ezyxip.runiwstart.services.OperationManagerHolder;
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

import java.util.logging.Logger;

@Route("/spaces/manager")
@RolesAllowed("ROLE_MANAGER")
@PageTitle("Панель менеджера")
public class ManagerUI extends AppLayout {
    static Logger logger = Logger.getLogger(ManagerUI.class.getName());

    CellRepository repository;
    AcceptanceRepository acceptanceRepository;

    OperationManagerHolder operationManagerHolder;

    ManagerUI(
            @Autowired CellRepository repository,
            @Autowired AcceptanceRepository acceptanceRepository,
            @Autowired OperationManagerHolder operationManagerHolder
            ){
        this.repository = repository;
        this.acceptanceRepository = acceptanceRepository;
        this.operationManagerHolder = operationManagerHolder;

        Label title = new Label("Менеджер");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        addToNavbar(new DrawerToggle(), title);
        addToDrawer(getTabs());
        setContent(new ManagerGeneralInfo());
    }

    Tabs getTabs(){
        ExtendTab generalInfo = new ExtendTab();
        generalInfo.add(new Icon(VaadinIcon.EDIT){{getStyle().set("margin", "0.5em");}});
        generalInfo.add("Общие сведения");
        generalInfo.setCallback(ManagerGeneralInfo::new);

        ExtendTab users = new ExtendTab();
        users.add(new Icon(VaadinIcon.COMPILE){{getStyle().set("margin", "0.5em");}});
        users.add("Структура склада");
        users.setCallback(()-> new CellsScreen(repository));

        ExtendTab warehouseModel = new ExtendTab();
        warehouseModel.add(new Icon(VaadinIcon.CUBES){{getStyle().set("margin", "0.5em");}});
        warehouseModel.add("Груз");
        warehouseModel.setCallback(()-> new CargoViewerScreen());

        ExtendTab operations = new ExtendTab();
        operations.add(new Icon(VaadinIcon.BULLETS){{getStyle().set("margin", "0.5em");}});
        operations.add("Операции");
        operations.setCallback(() -> new OperationScreen(operationManagerHolder));

        ExtendTab acceptance = new ExtendTab();
        acceptance.add(new Icon(VaadinIcon.SIGN_IN){{getStyle().set("margin", "0.5em");}});
        acceptance.add("Приёмка");
        acceptance.setCallback(() -> new AcceptanceScreen(acceptanceRepository, operationManagerHolder));

        ExtendTab order = new ExtendTab();
        order.add(new Icon(VaadinIcon.PAPERCLIP){{getStyle().set("margin", "0.5em");}});
        order.add("Заказы");
        order.setCallback(() -> new OrderScreen());

        ExtendTab warnings = new ExtendTab();
        warnings.add(new Icon(VaadinIcon.ENVELOPE_O){{getStyle().set("margin", "0.5em");}});
        warnings.add("Объявления");
        warnings.setCallback(MessengerScreen::new);

        ExtendTab exit = new ExtendTab();
        exit.add(new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT){{getStyle().set("margin", "0.5em");}});
        exit.add("Выход");
        exit.setCallback(()->{
            UI.getCurrent().navigate("/nav");
            return new Label("Перенаправление...");
        });


        Tabs res = new Tabs(generalInfo, users, warehouseModel, operations, acceptance, order, warnings, exit);
        res.setOrientation(Tabs.Orientation.VERTICAL);
        res.addSelectedChangeListener(
                event -> {
                    setContent(((ExtendTab)res.getSelectedTab()).getCallback().run());
                }
        );
        return res;
    }
}
