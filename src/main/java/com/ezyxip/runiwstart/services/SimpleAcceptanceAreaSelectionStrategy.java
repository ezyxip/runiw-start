package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.AreaEntity;
import com.ezyxip.runiwstart.entities.EntryEntity;
import com.ezyxip.runiwstart.entities.EntryToAreaRelation;
import com.ezyxip.runiwstart.exceptions.ResourceNotFoundException;
import com.ezyxip.runiwstart.repositories.AreaRepository;

import java.util.List;
import java.util.Optional;

public class SimpleAcceptanceAreaSelectionStrategy implements AcceptanceAreaSelectionStrategy{
    @Override
    public AreaEntity getArea(EntryEntity entry) throws Exception {
        Optional<EntryToAreaRelation> res = entry.getAreas().stream().filter(e -> e.getArea().isBooking()).findFirst();
        if(res.isEmpty()) throw new ResourceNotFoundException("Подходящая площадка не найдена");
        return res.get().getArea();
    }
}
