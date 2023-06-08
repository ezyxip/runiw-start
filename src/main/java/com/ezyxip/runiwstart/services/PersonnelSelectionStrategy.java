package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.UserEntity;

import java.util.List;

public interface PersonnelSelectionStrategy {
    List<UserEntity> getEmployer(int count, String role) throws Exception;
}
