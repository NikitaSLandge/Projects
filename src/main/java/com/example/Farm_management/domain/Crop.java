package com.example.Farm_management.domain;

import com.example.Farm_management.domain.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long id;
    private String name;
    private LocalDate Creation_date;
    private LocalDate cultivation_date;
    private LocalDate harvesting_date;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "plot_id")
    private Plot plot;

    @OneToOne(mappedBy = "crop")
    private Stock stock;

    public Crop() {
    }

    public Crop(Long id, String name, LocalDate creation_date, LocalDate cultivation_date, LocalDate harvesting_date, String description, Status status, Farm farm, Plot plot, Stock stock) {
        this.id = id;
        this.name = name;
        Creation_date = creation_date;
        this.cultivation_date = cultivation_date;
        this.harvesting_date = harvesting_date;
        this.description = description;
        this.status = status;
        this.farm = farm;
        this.plot = plot;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreation_date() {
        return Creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        Creation_date = creation_date;
    }

    public LocalDate getCultivation_date() {
        return cultivation_date;
    }

    public void setCultivation_date(LocalDate cultivation_date) {
        this.cultivation_date = cultivation_date;
    }

    public LocalDate getHarvesting_date() {
        return harvesting_date;
    }

    public void setHarvesting_date(LocalDate harvesting_date) {
        this.harvesting_date = harvesting_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
