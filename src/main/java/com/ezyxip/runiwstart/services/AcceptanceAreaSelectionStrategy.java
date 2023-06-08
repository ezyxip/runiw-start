package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.AreaEntity;
import com.ezyxip.runiwstart.entities.EntryEntity;

import java.util.List;

public interface AcceptanceAreaSelectionStrategy {
    AreaEntity getArea(EntryEntity entry) throws Exception;
}
