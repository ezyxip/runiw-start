package com.ezyxip.runiwstart.UI;

import com.ezyxip.runiwstart.system.UriCacheStore;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinServletResponse;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Route("/auth")
@AnonymousAllowed
@PageTitle("Авторизация")
public class AuthPage extends VerticalLayout {

    SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler(){{
       setDefaultTargetUrl("/");
    }};
    @Autowired
    DaoAuthenticationProvider authenticationManager;

    @Autowired
    HttpSession httpSession;
    Logger logger = Logger.getLogger(AuthPage.class.getName());
    protected H1 h1 = new H1("Авторизация");
    protected TextField textField = new TextField("Логин");
    protected PasswordField passwordField =  new PasswordField("Пароль");

    public AuthPage(){
        add(h1, textField, passwordField);
        Button button = new Button("Войти", new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                if(auth()){
                    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    logger.info("Вход выполнен успешно. Пользователь: " + user.getUsername());
                    if(VaadinServletRequest.getCurrent().getSession().getAttribute("uriCache") != null) {
                        UriCacheStore store = (UriCacheStore) VaadinServletRequest.getCurrent().getSession().getAttribute("uriCache");
                        logger.info(store.toString());
                        UI.getCurrent().navigate(store.getCache().firstElement());
                    }else{
                        UI.getCurrent().navigate("/");
                    }
                } else {
                    logger.info("Неверные логин и пароль. Логин/пароль:" + textField.getValue() + "/" + passwordField.getValue());
                    onBadAuth();
                }
            }
        });
        add(button);
        setAlignItems(Alignment.CENTER);
    }

    public boolean auth(){
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                textField.getValue(),
                passwordField.getValue()
        );
        try {
            Authentication a = authenticationManager.authenticate(authentication);
            securityContext.setAuthentication(a);
            SecurityContextHolder.setContext(securityContext);
            httpSession.setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public void onBadAuth(){
        Label label = new Label("Неверное имя пользователя или пароль");
        label.getStyle().set("color", "red");
        add(label);
    }
}
