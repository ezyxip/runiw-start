package com.ezyxip.runiwstart.services;


import com.ezyxip.runiwstart.entities.OperationManagerEntity;

import java.io.*;

public interface OperationManager extends Serializable {

    static byte[] serialize(OperationManager operationManager) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(operationManager);
        return byteStream.toByteArray();
    }

    static OperationManager deserialize(byte[] obj) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(obj);
        ObjectInputStream objectStream = new ObjectInputStream(byteStream);
        return (OperationManager) objectStream.readObject();
    }
    String getId();
    AgentContainer getAgentContainer(String username);
    void confirmWorkStart(String username);
    boolean complete(String username);
    void reserveResource();
    String getTitle();
    void stop();
    void save() throws IOException;
    boolean isEnable();
}
