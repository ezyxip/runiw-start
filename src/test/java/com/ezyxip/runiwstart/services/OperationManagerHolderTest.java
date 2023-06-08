package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.services.acceptance.AcceptanceManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OperationManagerHolderTest {

    static Logger logger = Logger.getLogger(OperationManagerHolderTest.class.getName());

    @Autowired
    OperationManagerHolder holder;

    @Test
    void createAcceptanceManager() {
        try {
            AcceptanceManager acceptanceManager = holder.createAcceptanceManager();
            logger.info(acceptanceManager.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}