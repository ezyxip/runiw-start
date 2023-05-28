package com.ezyxip.runiwstart;

import com.ezyxip.runiwstart.system.ServiceConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.logging.Logger;

@SpringBootApplication
public class RuniwStartApplication {

    static Logger logger = Logger.getLogger(RuniwStartApplication.class.getName());

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(RuniwStartApplication.class, args);
    }

}
