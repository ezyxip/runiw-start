package com.ezyxip.runiwstart.entities;


import com.ezyxip.runiwstart.services.RoleDict;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {

   @EmbeddedId
   AuthorityEntityId id;

    public AuthorityEntity() {
        id = new AuthorityEntityId("", "");
    }

    public AuthorityEntity(String username, String authority) {
        id = new AuthorityEntityId(username, authority);
    }

    public void setUserName(String username){
        id.setUsername(username);
    }

    public void setAuthority(String authority){
        id.setAuthority(authority);
    }

    public String getUsername(){
        return id.getUsername();
    }

    public String getAuthority(){
        return id.getAuthority();
    }

    public AuthorityEntityId getId() {
        return id;
    }

    public void setId(AuthorityEntityId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String res = new RoleDict().get(getId().getAuthority()).getValue1();
        return res != null ? res : getId().getAuthority();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null /*|| getClass() != o.getClass()*/) return false;
        AuthorityEntity authority = (AuthorityEntity) o;
        return Objects.equals(getId(), authority.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
