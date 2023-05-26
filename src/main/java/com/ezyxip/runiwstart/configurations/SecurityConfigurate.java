package com.ezyxip.runiwstart.configurations;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigurate extends VaadinWebSecurity {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //http.authorizeHttpRequests(auth->auth.requestMatchers("/auth", "/").permitAll());
        http.csrf().disable();
        //http.formLogin(Customizer.withDefaults());
        super.configure(http);
        setLoginView(http, "auth");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        UserDetails anton = User.builder()
                .username("Антон Цейдлер")
                .password("{noop}46q45m")
                .roles("ADMIN", "ANALYST")
                .build();
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);
//        jdbc.deleteUser("Антон Цейдлер");
//        jdbc.createUser(anton);
        return jdbc;
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder, UserDetailsService userDetailsService) throws Exception {
//        builder.userDetailsService(userDetailsService);
//        return builder.build();
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        return dao;
    }
}
