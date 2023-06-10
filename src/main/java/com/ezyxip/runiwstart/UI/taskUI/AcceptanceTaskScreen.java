package com.ezyxip.runiwstart.UI.taskUI;

import com.ezyxip.runiwstart.entities.CargotypeEntity;
import com.ezyxip.runiwstart.services.OperationAgent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class AcceptanceTaskScreen extends VerticalLayout {
    Runnable onClose;
    Button complete;
    public AcceptanceTaskScreen(Runnable onClose, OperationAgent agent){
        this.onClose = onClose;
        complete = new Button("Закончить");
        complete.addClickListener(e->{
            agent.complete();
            onClose.run();
        });

        ComboBox<CargotypeEntity> cargotype = new ComboBox<>("Тип груза");


    }
}
