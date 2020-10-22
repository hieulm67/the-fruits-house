package DTO;

import DAO.FruitsList;
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
public class Order implements Serializable{
    String ID;
    FruitsList listFruitsInOrder;
    String dateOrder;
    String dateCome;
    double total;
    String saler;
    String status;

    public Order(String ID, FruitsList listFruitsInOrder, double total, String saler, String status, String dateOrder, String dateCome) {
        this.ID = ID;
        this.listFruitsInOrder = listFruitsInOrder;
        this.total = total;
        this.saler = saler;
        this.status = status;
        this.dateOrder = dateOrder;
        this.dateCome = dateCome;
    }
    
    //Setter

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setListFruitsInOrder(FruitsList listFruitsInOrder) {
        this.listFruitsInOrder = listFruitsInOrder;
    }
    
    //Getter

    public String getID() {
        return ID;
    }

    public FruitsList getListFruitsInOrder() {
        return listFruitsInOrder;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getSaler() {
        return saler;
    }

    public void setSaler(String saler) {
        this.saler = saler;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDateCome() {
        return dateCome;
    }

    public void setDateCome(String dateCome) {
        this.dateCome = dateCome;
    }
    
    public void output(){
        System.out.println("Order ID: " + ID);
            System.out.println("Saler: " + saler);
            if (total != listFruitsInOrder.getTotalPrice()) {
                System.out.println("Discount: " + (100 - (total * 100) / listFruitsInOrder.getTotalPrice()) + "%");
            }
            System.out.println("Total: " + total);
            System.out.println("Order Day: " + dateOrder);
            System.out.println("Complete Day: " + dateCome);
            System.out.println("Status: " + status);
            System.out.println("");
    }
}
