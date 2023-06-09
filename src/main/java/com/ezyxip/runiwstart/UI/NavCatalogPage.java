package com.ezyxip.runiwstart.UI;

import com.ezyxip.runiwstart.UI.components.RoleCard;
import com.ezyxip.runiwstart.services.RoleDict;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.logging.Logger;

@Route("/nav")
@PageTitle("Навигация")
@PermitAll
public class NavCatalogPage extends AppLayout {
    protected String userName;

    protected Logger logger = Logger.getLogger(this.getClass().getName());



    NavCatalogPage(@Autowired RoleDict roleDict){
        userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Avatar avatar = new Avatar(userName);
        avatar.getStyle().set("margin-left", "1.5em");
        addToNavbar(avatar);

        Span userNameTitle = new Span(userName);
        userNameTitle.getStyle().set("margin-left", "1.5em");
        addToNavbar(userNameTitle);

        Button exitButton = new Button("Выход",
                (a)-> {
                    SecurityContextHolder.clearContext();
                    VaadinSession.getCurrent().getSession().invalidate();
                    UI.getCurrent().getPage().setLocation("/");
                });
        exitButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        exitButton.getStyle().set("margin-left", "1.5em");
        addToNavbar(exitButton);

        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(FlexComponent.Alignment.CENTER);
        content.add(new Label("Выберите доступное рабочее пространство для перехода."));

        Div roleContainer = new Div();

        Collection<GrantedAuthority> roles= (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(GrantedAuthority i : roles){
            roleContainer.add(new RoleCard(
                    roleDict.get(i.getAuthority()).getValue1(),
                    e -> UI.getCurrent().getPage().setLocation(roleDict.get(i.getAuthority()).getValue2())
            ));


        }

        roleContainer.getStyle()
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("grid-gap", "1em")
                .set("flex-wrap", "wrap")
                .set("padding", "1em");

        content.add(roleContainer);

        setContent(content);

    }

}
