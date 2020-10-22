/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Order;
import DTO.closeCustomers;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author emiul
 */
public class OrderManagementCloseCustomer extends Hashtable<closeCustomers, ListOrder> {

    public void showAllOrder() {
        Set<closeCustomers> listName = this.keySet();
        Iterator<closeCustomers> itCustomer = listName.iterator();
        while (itCustomer.hasNext()) {
            closeCustomers tem = itCustomer.next();
            ListOrder list = this.get(tem);

            System.out.println("");
            System.out.println("Customer Information and Order: ");
            System.out.println("Login ID: " + tem.getLogInID());
            System.out.println("Customer: " + tem.getName());
            System.out.println("Rank: " + tem.getRank());
            System.out.println("");
            list.showListOrder();
            System.out.println("");
        }
    }

    public Order searchOrder(closeCustomers cus, String orderID) {
        Set<closeCustomers> listName = this.keySet();
        Iterator<closeCustomers> itCustomer = listName.iterator();
        while (itCustomer.hasNext()) {
            closeCustomers tem = itCustomer.next();
            ListOrder list = this.get(tem);

            if (tem.getLogInID().equals(cus.getLogInID())) {
                return list.searchOrder(orderID);
            }
        }
        return null;
    }

//    public Order searchOrderByDay(closeCustomers cus, String orderDay){
//        Set<closeCustomers> listName = this.keySet();
//        Iterator<closeCustomers> itCustomer = listName.iterator();
//        while(itCustomer.hasNext()){
//            closeCustomers tem = itCustomer.next();
//            ListOrder list = this.get(tem);
//            
//            if(tem.getLogInID().equals(cus.getLogInID())){
//                return list.searchOrderByDay(orderDay);
//            }
//        }
//        return null;
//    } 
    public void searchOrderByDayAll1Cus(closeCustomers cus, String orderDay) {
        Set<closeCustomers> listName = this.keySet();
        Iterator<closeCustomers> itCustomer = listName.iterator();
        while (itCustomer.hasNext()) {
            closeCustomers tem = itCustomer.next();
            ListOrder list = this.get(tem);

            if (tem.getLogInID().equals(cus.getLogInID())) {
                System.out.println("");
                System.out.println("Customer Information and Order: ");
                System.out.println("Login ID: " + tem.getLogInID());
                System.out.println("Customer: " + tem.getName());
                System.out.println("Rank: " + tem.getRank());
                System.out.println("");
                list.searchOrderByDayAll(orderDay);
                System.out.println("");

            }
        }
    }

    public void searchOrderByDayAll(String orderDay) {
        Set<closeCustomers> listName = this.keySet();
        Iterator<closeCustomers> itCustomer = listName.iterator();
        while (itCustomer.hasNext()) {
            closeCustomers tem = itCustomer.next();
            ListOrder list = this.get(tem);

            System.out.println("");
            System.out.println("Customer Information and Order: ");
            System.out.println("Login ID: " + tem.getLogInID());
            System.out.println("Customer: " + tem.getName());
            System.out.println("Rank: " + tem.getRank());
            System.out.println("");
            list.searchOrderByDayAll(orderDay);
            System.out.println("");
        }
    }

    public ListOrder searchCustomer(closeCustomers buyer) {
        Set<closeCustomers> listName = this.keySet();
        Iterator<closeCustomers> itCustomer = listName.iterator();
        while (itCustomer.hasNext()) {
            closeCustomers tem = itCustomer.next();
            ListOrder list = this.get(tem);

            if (tem.getLogInID().equals(buyer.getLogInID())) {
                return list;
            }
        }
        return null;
    }

    public boolean addCustomerOrder(closeCustomers buyer, Order newOrder) {
        ListOrder tem = searchCustomer(buyer);
        if (tem == null) {
            ListOrder newList = new ListOrder(newOrder);
            this.put(buyer, newList);
        }
        return false;
    }
}
