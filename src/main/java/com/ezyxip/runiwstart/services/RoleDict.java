package com.ezyxip.runiwstart.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RoleDict extends HashMap<String, RoleStruct> {

    public RoleDict(){
        super();
        put("ROLE_ADMIN", new RoleStruct("Администратор", "/spaces/admin"));
        put("ROLE_ANALYST", new RoleStruct("Аналитик", "/spaces/analyst"));
        put("ROLE_LOADER", new RoleStruct("Грузчик", "/spaces/loader"));
        put("ROLE_MANAGER", new RoleStruct("Менеджер", "/spaces/manager"));
    }
}
