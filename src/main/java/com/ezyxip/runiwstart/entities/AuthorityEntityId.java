package com.ezyxip.runiwstart.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuthorityEntityId implements Serializable {
    String username;
    String authority;

    public AuthorityEntityId() {
    }

    public AuthorityEntityId(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthorityEntityId{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityEntityId that = (AuthorityEntityId) o;
        return /*Objects.equals(getUsername(), that.getUsername()) &&*/ Objects.equals(getAuthority(), that.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getAuthority());
    }
}
