package com.glite.popeyes.view.menu.our_menu.model;

import android.graphics.Bitmap;

/**
 * Created by QUOC CUONG on 22/09/2016.
 */
public class OrderItem {

    private String id;
    private String name;
    private Bitmap imageSource;
    private String details;
    private double price;
    private int quantity;

    public OrderItem(String id, String name, Bitmap imageSource, String details, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.imageSource = imageSource;
        this.details = details;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImageSource() {
        return imageSource;
    }

    public void setImageSource(Bitmap imageSource) {
        this.imageSource = imageSource;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
