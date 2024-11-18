package com.alexanderdoma.peruinolvidable.model.entity;

import java.sql.Timestamp;

public class Admin {
    private Integer id;
    private String username;
    private String password;
    private Timestamp created_at;

    public Admin() {
    }

    public Admin(Integer id, String username, String password, Timestamp created_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", username=" + username + ", password=" + password + ", created_at=" + created_at + '}';
    }
}
