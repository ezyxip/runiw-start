package com.ezyxip.runiwstart.services;

public class AgentContainer {
    private AgentType type;
    private OperationAgent agent;
    private String title;
    private String info;

    public AgentContainer(AgentType type, OperationAgent agent) {
        this(type, agent, "Заголовок", "Описание задания");
    }

    public AgentContainer(AgentType type, OperationAgent agent, String title, String info) {
        this.type = type;
        this.agent = agent;
        this.title = title;
        this.info = info;
    }

    public AgentType getType() {
        return type;
    }

    public OperationAgent getAgent() {
        agent.confirmStart();
        return agent;
    }

    public OperationAgent getAgentAgain(){
        if(type == AgentType.UNCONFIRMED) throw new RuntimeException("Вызван агент без подтверждения");
        return agent;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    protected void setInfo(String info) {
        this.info = info;
    }
}
