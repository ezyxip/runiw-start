package com.ezyxip.runiwstart.services;

public interface OperationManager {
    AgentContainer getAgentContainer(String username);
    void confirmWorkStart(String username);
    void complete(String username);
}
