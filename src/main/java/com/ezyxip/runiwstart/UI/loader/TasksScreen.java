package com.ezyxip.runiwstart.UI.loader;

import com.ezyxip.runiwstart.services.AgentContainer;
import com.ezyxip.runiwstart.services.OperationManagerHolder;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.springframework.security.core.context.SecurityContextHolder;

public class TasksScreen extends TabSheet {
    public TasksScreen(OperationManagerHolder operationManagerHolder){
        Tab availableTasks = new Tab("Доступные");
        this.getStyle().set("width", "100%");
        add(availableTasks, new AvailableTaskScreen(operationManagerHolder, this));
    }
}
