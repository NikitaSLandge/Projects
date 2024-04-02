package com.example.Farm_management.Service.dto;

import com.example.Farm_management.domain.Crop;
import com.example.Farm_management.domain.Farm;
import lombok.Data;



public class StockDto {
    private Long id;
    private double total_stock;
    private double used_stock;
    private double available_stock;
    private Crop crop;
    //private Farm farm;

    public StockDto() {
    }

    public StockDto(Long id, double total_stock, double used_stock, double available_stock, Crop crop) {
        this.id = id;
        this.total_stock = total_stock;
        this.used_stock = used_stock;
        this.available_stock = available_stock;
        this.crop = crop;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal_stock() {
        return total_stock;
    }

    public void setTotal_stock(double total_stock) {
        this.total_stock = total_stock;
    }

    public double getUsed_stock() {
        return used_stock;
    }

    public void setUsed_stock(double used_stock) {
        this.used_stock = used_stock;
    }

    public double getAvailable_stock() {
        return available_stock;
    }

    public void setAvailable_stock(double available_stock) {
        this.available_stock = available_stock;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }


}
