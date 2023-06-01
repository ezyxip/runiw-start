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

    public AuthorityEntityId getId() {
        return id;
    }

    public void setId(AuthorityEntityId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new RoleDict().get(getId().getAuthority()).getValue1();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityEntity that = (AuthorityEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
