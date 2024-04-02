package com.example.Farm_management.domain;

import com.example.Farm_management.domain.enumeration.Status;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
public class Farm {
    private static final String ENTITY_NAME = "Farm";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String image;
    private String area;
    private LocalDate creation_date;

    @Enumerated(EnumType.STRING)
    private Status status = Status.active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "farm")
    @JsonIgnore
    private Set<Plot> plot;


    @OneToMany(mappedBy = "farm")
    @JsonIgnore
    private Set<Crop> crop;


    @OneToMany(mappedBy = "farm")
    @JsonIgnore
    private Set<Supplier> supplier;

    @OneToMany(mappedBy = "farm")
    @JsonIgnore
    private Set<Transaction> transaction;


    public Farm() {
    }

    public Farm(Long id, String name, String location, String image, String area, LocalDate creation_date, Status status, User user, Set<Plot> plot, Set<Crop> crop, Set<Supplier> supplier, Set<Transaction> transaction) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.image = image;
        this.area = area;
        this.creation_date = creation_date;
        this.status = status;
        this.user = user;
        this.plot = plot;
        this.crop = crop;
        this.supplier = supplier;
        this.transaction = transaction;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Plot> getPlot() {
        return plot;
    }

    public void setPlot(Set<Plot> plot) {
        this.plot = plot;
    }

    public Set<Crop> getCrop() {
        return crop;
    }

    public void setCrop(Set<Crop> crop) {
        this.crop = crop;
    }

    public Set<Supplier> getSupplier() {
        return supplier;
    }

    public void setSupplier(Set<Supplier> supplier) {
        this.supplier = supplier;
    }

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }
}
