package com.ezyxip.runiwstart.services;

import java.util.HashMap;

public class RoleDict extends HashMap<String, String> {
    public RoleDict(){
        super();
        put("ROLE_ADMIN", "Администратор");
        put("ROLE_ANALYST", "Аналитик");
        put("ROLE_LOADER", "Грузчик");
        put("ROLE_MANAGER", "Менеджер");
    }
}
