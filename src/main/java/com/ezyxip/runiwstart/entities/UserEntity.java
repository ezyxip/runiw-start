package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    @Id
    @Column(nullable = false, columnDefinition = "varchar(50)")
    String username;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    String password;

    @Column(nullable = false, columnDefinition = "tinyint(1)")
    boolean enabled = true;

    @Transient
    List<AuthorityEntity> authorities;

    boolean booking = true;


    public UserEntity() {
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        enabled = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    public boolean isBooking() {
        return booking;
    }

    public void setBooking(boolean booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return isEnabled() == that.isEnabled() && booking == that.booking && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getAuthorities(), that.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), isEnabled(), getAuthorities(), booking);
    }

    @Override
    public String toString() {
        return username;
    }
}
