package com.example.Farm_management.domain;

import com.example.Farm_management.domain.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plot_id")
    private Long id;
    private String name;

    private String image;
    private String area;
    private LocalDate creation_date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @OneToOne(mappedBy = "plot")
    @JsonIgnore
    private Crop crop;

    public Plot(Long id, String name, String image, String area, LocalDate creation_date, Status status, Farm farm, Crop crop) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.area = area;
        this.creation_date = creation_date;
        this.status = status;
        this.farm = farm;
        this.crop = crop;
    }

    public Plot() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
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

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}
