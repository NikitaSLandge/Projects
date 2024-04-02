package com.example.Farm_management.Service.dto;

import com.example.Farm_management.domain.Farm;
import com.example.Farm_management.domain.enumeration.Status;

import java.util.Date;

public class PlotDto {
    private Long id;
    private String name;

    private String image;
    private String area;
    private Date creation_date;

    private Status status;

    private Farm farm;


    public PlotDto(Long id, String name, String image, String area, Date creation_date, Status status, Farm farm) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.area = area;
        this.creation_date = creation_date;
        this.status = status;
        this.farm = farm;
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

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
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
}
