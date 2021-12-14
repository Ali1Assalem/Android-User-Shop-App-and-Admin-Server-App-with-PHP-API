package com.example.adminshopserver.Model;

public class Cart {
    private int id, amount, size;
    private String name, link, with;
    private double price;

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getWith() {
        return with;
    }

    public double getPrice() {
        return price;
    }

}
