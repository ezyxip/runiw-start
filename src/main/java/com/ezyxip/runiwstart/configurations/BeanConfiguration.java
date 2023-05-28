package com.ezyxip.runiwstart.configurations;

import com.ezyxip.runiwstart.services.RoleDict;
import com.ezyxip.runiwstart.system.PrimaryServiceConnector;
import com.ezyxip.runiwstart.system.ServiceConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    ServiceConnector serviceConnector(){
        return new PrimaryServiceConnector("startModule", "startMPassword");
    }

}
