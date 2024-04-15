package com.example.clothingca;

import java.util.List;

public class Order {
    private String name;
    private String address;
    private List<ClothingItem> items;
    private double totalAmount;

    // Constructor that matches the parameters you are trying to pass
    public Order(String name, String address, List<ClothingItem> items, double totalAmount) {
        this.name = name;
        this.address = address;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    // Getters and setters for each field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ClothingItem> getItems() {
        return items;
    }

    public void setItems(List<ClothingItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
