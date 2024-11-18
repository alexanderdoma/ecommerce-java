package com.alexanderdoma.peruinolvidable.model.entity;

import java.sql.Timestamp;

public class Category {
    private Integer id;
    private String name ;
    private Timestamp created_at;

    public Category() {
    }
    
    public Category(Integer id, String name, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
