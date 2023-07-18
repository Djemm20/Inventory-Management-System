package com.example.inventory;

import java.sql.Date;

public class Goods {
    int id;
    String name;
    String category;
    String quantity;
    String cost;
    String sell;
    String stocked;

    public Goods(int id, String name, String category, String quantity, String cost, String sell, Date stocked) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.cost = cost;
        this.sell = sell;
        this.stocked = String.valueOf(stocked);
    }

    // Getters and setters for the properties
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost() { return cost; }

    public void setCost(String cost) { this.cost = cost; }

    public String getSell(){ return sell; }

    public void setSell(String sell) { this.sell = sell; }

    public String getStocked() { return stocked; }

    public void setStocked(String stocked) { this.stocked = stocked; }
}
