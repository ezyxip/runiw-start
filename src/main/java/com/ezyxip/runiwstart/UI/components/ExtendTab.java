package com.ezyxip.runiwstart.UI.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.tabs.Tab;

public class ExtendTab extends Tab  {

    private EventCallback callback = ()->{return new Label("Ничего не произошло");};

    public ExtendTab() {
        super();
    }

    public ExtendTab(String label) {
        super(label);
    }

    public ExtendTab(Component... components) {
        super(components);
    }

    public void setCallback(EventCallback callback) {
        this.callback = callback;
    }

    public EventCallback getCallback() {
        return callback;
    }
}
