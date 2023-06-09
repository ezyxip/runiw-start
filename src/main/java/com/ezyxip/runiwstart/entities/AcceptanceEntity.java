package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "acceptance")
public class AcceptanceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private LocalDate date;
    private boolean isclosed;

    public AcceptanceEntity() {
    }

    public AcceptanceEntity(String source, LocalDate date, boolean isclosed) {
        this.source = source;
        this.date = date;
        this.isclosed = isclosed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isIsClosed() {
        return isclosed;
    }

    public void setIsClosed(boolean isclosed) {
        this.isclosed = isclosed;
    }
}
