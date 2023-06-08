package com.ezyxip.runiwstart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
public class OperationManagerTest {
    static Logger logger = Logger.getLogger(OperationManagerTest.class.getName());
    @Test
    void test(){
        logger.info("Тест запущен");
    }
}
