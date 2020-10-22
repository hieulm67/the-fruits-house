package DAO;

import DTO.Order;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Min Hiu
 */
public class ListOrder extends Hashtable<String, Order> {

    int count = 0;

    public ListOrder(String id, Order newOrder) {
        this.put(newOrder.getID() + "", newOrder);
        count = Integer.parseInt(id);
        count++;
    }

    public ListOrder(Order newOrder) {
        if (newOrder != null) {
            this.put(newOrder.getID() + "", newOrder);
            count++;
        } else {
            count = 0;
        }
    }

    public void addOrder(Order newOrder) {
        if (newOrder != null) {
            this.put(newOrder.getID() + "", newOrder);
            count++;
        }
    }

    public Order searchOrder(String ID) {
        Set<String> listID = this.keySet();
        Iterator<String> it = listID.iterator();
        while (it.hasNext()) {
            String id = it.next();
            Order order = this.get(id);

            if (id.equals(ID)) {
                return order;
            }
        }
        return null;
    }
    
    public Order searchOrderByDay(String day){
        Set<String> listID = this.keySet();
        Iterator<String> it = listID.iterator();
        while (it.hasNext()) {
            String id = it.next();
            Order order = this.get(id);

            if (order.getDateOrder().equals(day)) {
                return order;
            }
        }
        return null;
    }
    
    public void searchOrderByDayAll(String day){
        Set<String> listID = this.keySet();
        Iterator<String> it = listID.iterator();
        while (it.hasNext()) {
            String id = it.next();
            Order order = this.get(id);

            if (order.getDateOrder().equals(day)) {
                order.output();
            }
        }
    }

    public void showOrder(Order order) {
        System.out.println("Order ID: " + order.getID());
        System.out.println("Saler: " + order.getSaler());
        if (order.getTotal() != order.getListFruitsInOrder().getTotalPrice()) {
            System.out.println("Discount: " + (100 - (order.getTotal() * 100) / order.getListFruitsInOrder().getTotalPrice()) + "%");
        }
        System.out.println("Total: " + order.getTotal());
        System.out.println("Order Day: " + order.getDateOrder());
        System.out.println("Complete Day: " + order.getDateCome());
        System.out.println("Status: " + order.getStatus());
        order.getListFruitsInOrder().addedToCart();
        System.out.println("");
    }

    public void showListOrder() {
        Set<String> listID = this.keySet();
        Iterator<String> it = listID.iterator();
        while (it.hasNext()) {
            String id = it.next();
            Order order = this.get(id);

            System.out.println("Order ID: " + id);
            System.out.println("Saler: " + order.getSaler());
            if (order.getTotal() != order.getListFruitsInOrder().getTotalPrice()) {
                System.out.println("Discount: " + (100 - (order.getTotal() * 100) / order.getListFruitsInOrder().getTotalPrice()) + "%");
            }
            System.out.println("Total: " + order.getTotal());
            System.out.println("Order Day: " + order.getDateOrder());
            System.out.println("Complete Day: " + order.getDateCome());
            System.out.println("Status: " + order.getStatus());
            order.getListFruitsInOrder().addedToCart();
            System.out.println("");
        }
    }

    //Getter
    public int getCountID() {
        return count;
    }
}
