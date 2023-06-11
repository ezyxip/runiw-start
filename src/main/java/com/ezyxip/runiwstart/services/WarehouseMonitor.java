package com.ezyxip.runiwstart.services;

import com.ezyxip.runiwstart.entities.CargoBookingEntity;
import com.ezyxip.runiwstart.entities.CargotypeEntity;
import com.ezyxip.runiwstart.entities.CargounitEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class WarehouseMonitor {
    Logger logger = Logger.getLogger(WarehouseMonitor.class.getName());
    ArrayList<NomenclaturePosition> stocks = new ArrayList<>();
    ArrayList<NomenclaturePosition> reservedCargo = new ArrayList<>();

    public WarehouseMonitor() {
        List<CargounitEntity> allCargo = DataStorage.cargounitRepository.findAll();
        List<CargotypeEntity> types = DataStorage.cargotypeRepository.findAll();
        for (CargotypeEntity ct : types) {
            int cargoCount = 0;
            int generalBookingCount = 0;
            for (CargounitEntity cu : allCargo) {
                if (!cu.getCargotype().equals(ct)) continue;
                List<CargoBookingEntity> bookings = cu.getBooking();
                int bookingCount = cu.getCount();
                for (CargoBookingEntity cb : bookings) {
                    bookingCount -= cb.getCount();
                    generalBookingCount += cb.getCount();
                }
                cargoCount += bookingCount;
            }
            NomenclaturePosition position = new NomenclaturePosition(ct, cargoCount);
            stocks.add(position);
            NomenclaturePosition reservedPosition = new NomenclaturePosition(ct, generalBookingCount);
            reservedCargo.add(reservedPosition);
        }
    }

    public ArrayList<NomenclaturePosition> getStocks() {
        return stocks;
    }

    public ArrayList<NomenclaturePosition> getReservedCargo() {
        return reservedCargo;
    }
}
