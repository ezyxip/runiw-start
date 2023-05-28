package com.ezyxip.runiwstart.system;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

public class RuniwUser extends User {

    public RuniwUser(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
    }
}
