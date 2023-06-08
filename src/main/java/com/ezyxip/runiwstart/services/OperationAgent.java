package com.ezyxip.runiwstart.services;

import com.vaadin.flow.component.Component;

public interface OperationAgent {
    void complete();
    Component getUI();
    void confirmStart();
}
