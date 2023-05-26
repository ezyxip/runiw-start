package com.ezyxip.runiwstart.UI;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.theme.lumo.LumoUtility;


public class RoleCard extends Button {
    public RoleCard(String text){
        setText(text);
//        getStyle().set("min-width", "10em");
//        getStyle().set("min-height", "4em");
        getStyle().set("margin", "1em");
        addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }
}
