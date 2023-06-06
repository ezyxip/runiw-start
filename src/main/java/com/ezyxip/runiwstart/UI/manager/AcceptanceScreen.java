package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.UI.components.ExtendTab;
import com.ezyxip.runiwstart.repositories.AcceptanceRepository;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.component.tabs.Tabs;

public class AcceptanceScreen extends VerticalLayout {

    AcceptanceRepository repo;
    AcceptanceScreen(AcceptanceRepository repo){
        this.repo = repo;
        add(getTabs());
    }

    TabSheet getTabs(){
        ExtendTab currentAcceptances = new ExtendTab();
        currentAcceptances.add("Текущие");

        ExtendTab acceptanceArchive = new ExtendTab();
        acceptanceArchive.add("Архив");

        TabSheet res = new TabSheet();
        res.getStyle().set("width", "100%");
        res.add(currentAcceptances, new CurrentAcceptanceScreen(repo));
        res.add(acceptanceArchive, new Label("Здесь будет архив приёмок"));

        return res;
    }
}
