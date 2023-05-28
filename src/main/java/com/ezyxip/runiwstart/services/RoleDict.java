package com.ezyxip.runiwstart.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RoleDict extends HashMap<String, RoleStruct> {

    public RoleDict(
            @Value("${runiw.address.adminUI}")
             String adminUIAddress,

            @Value("${runiw.address.analystUI}")
            String analystUIAddress,

            @Value("${runiw.address.loaderUI}")
            String loaderUIAddress,

            @Value("${runiw.address.managerUI}")
            String managerUIAddress
    ){
        super();
        put("ROLE_ADMIN", new RoleStruct("Администратор", adminUIAddress));
        put("ROLE_ANALYST", new RoleStruct("Аналитик", analystUIAddress));
        put("ROLE_LOADER", new RoleStruct("Грузчик", loaderUIAddress));
        put("ROLE_MANAGER", new RoleStruct("Менеджер", managerUIAddress));
    }
}
