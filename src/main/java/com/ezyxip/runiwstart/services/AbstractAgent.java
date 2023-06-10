package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.services.acceptance.AcceptanceManager;
import com.vaadin.flow.component.Component;

abstract public class AbstractAgent implements OperationAgent{

    protected OperationManager manager;
    protected String username;

    public AbstractAgent(OperationManager parentManager, String username){
        this.manager = parentManager;
        this.username = username;
    }

    @Override
    public boolean complete() {
        return manager.complete(username);
    }

    @Override
    public void confirmStart() {
        manager.confirmWorkStart(username);
    }
}
