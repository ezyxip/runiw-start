package com.ezyxip.runiwstart.services.acceptance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceManagerBuilderTest {

    @Test
    void build() {
        AcceptanceManagerBuilder builder = new AcceptanceManagerBuilder();
        builder.build();
    }
}