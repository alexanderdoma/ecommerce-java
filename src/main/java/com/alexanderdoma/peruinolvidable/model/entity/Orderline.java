package com.alexanderdoma.peruinolvidable.model.entity;

import java.sql.Timestamp;

public class Orderline {
    private Integer id;
    private int quantity;
    private double total;
    private Timestamp created_at;
    private Order order;
    private Product product;

    public Orderline() {
    }

    public Orderline(Integer id, int quantity, double total, Timestamp created_at, Order order, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.created_at = created_at;
        this.order = order;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Orderline{" + "id=" + id + ", quantity=" + quantity + ", total=" + total + ", created_at=" + created_at + ", order=" + order + ", product=" + product + '}';
    }
}
