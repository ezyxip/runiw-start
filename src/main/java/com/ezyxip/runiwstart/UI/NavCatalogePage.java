package com.ezyxip.runiwstart.UI;

import com.ezyxip.runiwstart.services.RoleDict;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

@Route("nav")
@PageTitle("Навигация")
@PermitAll
public class NavCatalogePage extends AppLayout {
    protected String userName;



    NavCatalogePage(){
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
                    UI.getCurrent().navigate("/");
                });
        exitButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        exitButton.getStyle().set("margin-left", "1.5em");
        addToNavbar(exitButton);

        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(FlexComponent.Alignment.CENTER);
        content.add(new Label("Выберите доступное рабочее пространство для перехода."));

        Div roleContainer = new Div();

        Collection<GrantedAuthority> roles= (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        RoleDict roleDict = new RoleDict();
        for(GrantedAuthority i : roles){
            roleContainer.add(new RoleCard(roleDict.get(i.getAuthority())));
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
