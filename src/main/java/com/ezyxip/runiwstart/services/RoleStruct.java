package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.system.Pair;

public class RoleStruct extends Pair<String, String> {
    public RoleStruct(){
        super();
    }

    public RoleStruct(String name, String link){
        super(name, link);
    }
}
