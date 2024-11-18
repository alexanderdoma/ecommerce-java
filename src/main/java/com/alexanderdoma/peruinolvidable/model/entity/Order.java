package com.alexanderdoma.peruinolvidable.model.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Order {
    private Integer id;
    private Date date;
    private double subtotal;
    private double total;
    private String status;
    private String payment_id;
    private Timestamp created_at;
    private User user;

    public Order() {
    }

    public Order(Integer id, Date date, double subtotal, double total, String status, String payment_id, Timestamp created_at, User user) {
        this.id = id;
        this.date = date;
        this.subtotal = subtotal;
        this.total = total;
        this.status = status;
        this.payment_id = payment_id;
        this.created_at = created_at;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", date=" + date + ", subtotal=" + subtotal + ", total=" + total + ", status=" + status + ", payment_id=" + payment_id + ", created_at=" + created_at + ", user=" + user + '}';
    }
}
