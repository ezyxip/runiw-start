package com.ezyxip.runiwstart.configurations;

import com.ezyxip.runiwstart.repositories.*;
import com.ezyxip.runiwstart.services.*;
import com.ezyxip.runiwstart.system.PrimaryServiceConnector;
import com.ezyxip.runiwstart.system.ServiceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    ServiceConnector serviceConnector(){
        return new PrimaryServiceConnector("startModule", "startMPassword");
    }

    @Bean
    PersonnelSelectionStrategy personnelSelectionStrategy(@Autowired UserRepository userRepository, @Autowired AuthorityRepository authorityRepository){
        return new SimplePersonnelSelectionStrategy(userRepository, authorityRepository);
    }

    @Bean
    AcceptanceAreaSelectionStrategy acceptanceAreaSelectionStrategy(){
        return new SimpleAcceptanceAreaSelectionStrategy();
    }

    @Bean
    CargoReleaseSelectionStrategy cargoReleaseSelectionStrategy(@Autowired CargounitRepository cargounitRepository){
        return new SimpleCargoReleaseSelectionStrategy(cargounitRepository);
    }

    @Bean
    EntrySelectionStrategy entrySelectionStrategy(@Autowired EntryRepository entryRepository){
        return new SimpleEntrySelectionStrategy(entryRepository);
    }
}
