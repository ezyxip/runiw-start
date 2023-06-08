package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.EntryEntity;
import com.ezyxip.runiwstart.exceptions.ResourceNotFoundException;
import com.ezyxip.runiwstart.repositories.EntryRepository;

import java.util.Optional;

public class SimpleEntrySelectionStrategy implements  EntrySelectionStrategy{

    EntryRepository entryRepository;

    public SimpleEntrySelectionStrategy(EntryRepository entryRepository){
        this.entryRepository = entryRepository;
    }


    @Override
    public EntryEntity getEntry() throws Exception{
        Optional<EntryEntity> res = entryRepository.findAllByModeAndBooking("in", true).stream().findFirst();
        if(res.isEmpty()) throw new ResourceNotFoundException("Свободных ворот нет");
        return res.get();
    }
}
