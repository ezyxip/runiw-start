package com.ezyxip.runiwstart.UI;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.logging.Logger;

@Route("")
@PageTitle("Runiw WMS")
@AnonymousAllowed
public class IndexPage extends AppLayout {

    Logger logger = Logger.getLogger(IndexPage.class.getName());

    VerticalLayout content = new VerticalLayout();
    public IndexPage() {
        Span span = new Span("WMS");
        span.getStyle().set("font-size", "1.8em")
                .set("font-weight", "bold")
                .set("margin-left", "2em")
                .set("font-size", "var(--lumo-font-size-l)");
        addToNavbar(span);

        content.add(new H1("Добро пожаловать"));
        content.add(new Span("Вы будете перенаправлены в рабочее пространство"));
        Button button = new Button("Продолжить");
        button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                UI.getCurrent().getPage().setLocation("/nav");
            }
        });
        content.add(button);
        content.setAlignItems(VerticalLayout.Alignment.CENTER);

        setContent(content);
    }


}
