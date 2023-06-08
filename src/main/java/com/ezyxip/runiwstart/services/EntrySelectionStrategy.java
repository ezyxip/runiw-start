package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.EntryEntity;

public interface EntrySelectionStrategy {
    EntryEntity getEntry() throws Exception;
}
