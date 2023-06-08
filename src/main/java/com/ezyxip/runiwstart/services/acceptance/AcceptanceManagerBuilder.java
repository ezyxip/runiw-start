package com.ezyxip.runiwstart.services.acceptance;

import com.ezyxip.runiwstart.entities.AreaEntity;
import com.ezyxip.runiwstart.entities.EntryEntity;
import com.ezyxip.runiwstart.entities.UserEntity;
import com.ezyxip.runiwstart.repositories.CargounitRepository;

import java.util.*;
import java.util.stream.Collectors;

public class AcceptanceManagerBuilder {
    private AcceptanceManager res;
    
    public AcceptanceManagerBuilder(){
        res = new AcceptanceManager();
    }
    
    public AcceptanceManagerBuilder setRepository(CargounitRepository cargounitRepository){
        res.setCargounitRepository(cargounitRepository);
        return this;
    }
    
    public AcceptanceManagerBuilder setEmployers(List<UserEntity> employers){
        res.setEmployers(employers);
        return this;
    }
    
    public AcceptanceManagerBuilder setEmployers(UserEntity... employers){
        return setEmployers(Arrays.asList(employers));
    }
    
    public AcceptanceManagerBuilder addEmployers(UserEntity... employers){
        res.employers.addAll(Arrays.stream(employers).collect(Collectors.toSet()));
        return this;
    }
    
    public AcceptanceManagerBuilder setArea(AreaEntity area){
        res.setArea(area);
        return this;
    }
    
    public AcceptanceManagerBuilder setEntry(EntryEntity entry){
        res.setEntry(entry);
        return this;
    }
    
    public AcceptanceManager build(){
        if(res.getArea() != null 
                && res.getEmployers() != null 
                && res.getEntry() != null 
                && res.getCargounitRepository() != null){
            return res;
        }else{
            return null;
        }
    }
}
