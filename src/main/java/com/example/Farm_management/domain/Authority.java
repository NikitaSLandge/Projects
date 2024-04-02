package com.example.Farm_management.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "authority")
public class Authority {
    @Id
    private String name;
    @ManyToMany(mappedBy = "authorities")
    private Set<User> user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

