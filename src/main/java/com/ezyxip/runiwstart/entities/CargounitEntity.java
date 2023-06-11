package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cargounit")
public class CargounitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private CargotypeEntity cargotype;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cell_id")
    private CellEntity cell;

    @ManyToOne(fetch = FetchType.EAGER)
    private AcceptanceEntity acceptance;

    private int count;

    public CargounitEntity() {
    }

    public CargounitEntity(CargotypeEntity cargotype, CellEntity cell, AcceptanceEntity acceptance, int count) {
        this.cargotype = cargotype;
        this.cell = cell;
        this.acceptance = acceptance;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CargotypeEntity getCargotype() {
        return cargotype;
    }

    public void setCargotype(CargotypeEntity cargotype) {
        this.cargotype = cargotype;
    }

    public CellEntity getCell() {
        return cell;
    }

    public void setCell(CellEntity cell) {
        this.cell = cell;
    }

    public AcceptanceEntity getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(AcceptanceEntity acceptance) {
        this.acceptance = acceptance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CargounitEntity{" +
                "id=" + id +
                ", cargotype=" + cargotype +
                ", cell=" + cell +
                ", acceptance=" + acceptance +
                ", count=" + count +
                '}';
    }
}
