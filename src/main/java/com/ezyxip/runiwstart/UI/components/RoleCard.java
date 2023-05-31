package com.ezyxip.runiwstart.UI.components;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.theme.lumo.LumoUtility;


public class RoleCard extends Button {
    public RoleCard(String text, ComponentEventListener<ClickEvent<Button>> click){
        setText(text);
        addClickListener(click);
        getStyle().set("margin", "1em");
        addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }
}
