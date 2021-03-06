package DTO;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Min Hiu
 */
public class Fruit implements Serializable{
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String origin;
    
    //Constructor

    public Fruit() {
        id = "";
        name = "";
        price = -1;
        quantity = 1;
        origin ="";
    }

    public Fruit(String id, String name, double price, int quantity, String origin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }
    
    //Getter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getOrigin() {
        return origin;
    }
    
    //Setter

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity + "," + origin;
    }
}
