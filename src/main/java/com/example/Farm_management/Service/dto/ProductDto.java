package com.example.Farm_management.Service.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class ProductDto {

    private Long id;

    private String name;

    private String image;

    private String category;

    private String description;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String image, String category, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
        this.description = description;
    }
}
