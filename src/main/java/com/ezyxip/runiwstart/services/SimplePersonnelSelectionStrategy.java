package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.UserEntity;
import com.ezyxip.runiwstart.exceptions.ResourceNotFoundException;
import com.ezyxip.runiwstart.repositories.AuthorityRepository;
import com.ezyxip.runiwstart.repositories.UserRepository;

import java.util.List;

public class SimplePersonnelSelectionStrategy implements PersonnelSelectionStrategy{
    UserRepository userRepository;
    AuthorityRepository authorityRepository;
    public SimplePersonnelSelectionStrategy(UserRepository userRepository, AuthorityRepository authorityRepository){
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }
    @Override
    public List<UserEntity> getEmployer(int count, String role) throws Exception{
        List<UserEntity> res = userRepository.findAllByBooking(true).stream().limit(count).toList();
        res = res.stream().filter(u -> {
            return authorityRepository.findAllById_Username(u.getUsername()).stream().anyMatch(a-> a.getAuthority().equals(role));
        }).toList();
        if(res.isEmpty()) throw new ResourceNotFoundException("Ни одного подходящего работника не найдено");
        return res;
    }
}
