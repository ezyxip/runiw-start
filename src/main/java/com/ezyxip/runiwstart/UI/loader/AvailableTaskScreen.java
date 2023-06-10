package com.ezyxip.runiwstart.UI.loader;

import com.ezyxip.runiwstart.services.AgentContainer;
import com.ezyxip.runiwstart.services.AgentType;
import com.ezyxip.runiwstart.services.OperationManagerHolder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AvailableTaskScreen extends VerticalLayout {
    AvailableTaskScreen(OperationManagerHolder operationManagerHolder, TabSheet parent){
        Grid<AgentContainer> grid = new Grid<>(AgentContainer.class, false);
        grid.addColumn(new ComponentRenderer<>(
                VerticalLayout::new,
                (layout, agentContainer) -> {
                    Label taskTitle = new Label(agentContainer.getTitle());
                    taskTitle.getStyle()
                            .set("font-size", "1.2em")
                            .set("text-width", "bold");
                    Label description = new Label(agentContainer.getInfo());
                    layout.add(taskTitle, description);
                    if(agentContainer.getType() == AgentType.CONFIRMED){
                        taskTitle.getStyle().set("color", "var(--lumo-primary-color");
                        description.getStyle().set("color", "var(--lumo-primary-color");
                    }
                }
        ));
        grid.setItems(operationManagerHolder.getAgent(SecurityContextHolder.getContext().getAuthentication().getName()));

        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.setAllRowsVisible(true);

        grid.addItemClickListener(e -> {
            if(e.getItem().getType() == AgentType.CONFIRMED){
                return;
            }
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Подтверждение");
            VerticalLayout content = new VerticalLayout();

            AgentContainer agentContainer = e.getItem();
            Label text = new Label("Принять следующую задачу?");

            TextField title = new TextField();
            title.setValue(agentContainer.getTitle());
            title.setReadOnly(true);
            title.getStyle().set("width", "100%");

            TextArea description = new TextArea();
            description.setValue(agentContainer.getInfo());
            description.addThemeVariants(TextAreaVariant.LUMO_SMALL);
            description.setReadOnly(true);
            description.getStyle().set("width", "100%");

            Button yes = new Button("Подтвердить");
            yes.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            yes.addClickListener(ev->{
                Tab tab = new Tab("Новая задача");
                parent.add(tab, e.getItem().getAgent().getUI(()->parent.remove(tab)));
                dialog.close();
            });

            Button cancel = new Button("Отмена");
            cancel.addClickListener(ev->dialog.close());

            content.add(text, title, description);

            dialog.add(content);
            dialog.getFooter().add(yes, cancel);

            dialog.open();

        });

        add(grid);
    }
}
