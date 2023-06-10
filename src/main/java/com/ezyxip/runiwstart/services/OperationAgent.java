package com.ezyxip.runiwstart.services;

import com.vaadin.flow.component.Component;

public interface OperationAgent {
    boolean complete();
    Component getUI(Runnable onClose);
    void  confirmStart();
}
