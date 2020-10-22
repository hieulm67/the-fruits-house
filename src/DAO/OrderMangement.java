package DAO;


import DAO.ListOrder;
import DTO.Customer;
import DTO.Order;
import java.io.Serializable;
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
public class OrderMangement extends Hashtable<Customer, ListOrder> implements Serializable{
    
    public void showAllOrder(){
        Set<Customer> listName = this.keySet();
        Iterator<Customer> itCustomer =listName.iterator();
        while(itCustomer.hasNext()){
            Customer tem = itCustomer.next();
            ListOrder list = this.get(tem);
            
            System.out.println("");
            System.out.println("Visitor Information and Order: ");
            System.out.println("Visitor: " + tem.getName());
            list.showListOrder();
            System.out.println("");
        }
    }
    
    public ListOrder searchCustomer(Customer buyer){
        Set<Customer> listName = this.keySet();
        Iterator<Customer> itCustomer = listName.iterator();
        while(itCustomer.hasNext()){
            Customer tem = itCustomer.next();
            ListOrder list = this.get(tem);
            
            if(tem.getName().equals(buyer.getName())){
                return list;
            }
        }
        return null;
    }
    
    public Order searchOrder(Customer cus, String orderID){
        Set<Customer> listName = this.keySet();
        Iterator<Customer> itCustomer = listName.iterator();
        while(itCustomer.hasNext()){
            Customer tem = itCustomer.next();
            ListOrder list = this.get(tem);
            
            if(tem.getName().equals(cus.getName()) && tem.getPhone().equals(cus.getPhone())){
                return list.searchOrder(orderID);
            }
        }
        return null;
    }
    
//    public Order searchOrderByDay(Customer cus, String orderDay){
//        Set<Customer> listName = this.keySet();
//        Iterator<Customer> itCustomer = listName.iterator();
//        while(itCustomer.hasNext()){
//            Customer tem = itCustomer.next();
//            ListOrder list = this.get(tem);
//            
//            if(tem.getName().equals(cus.getName()) && tem.getPhone().equals(cus.getPhone())){
//                return list.searchOrderByDay(orderDay);
//            }
//        }
//        return null;
//    } 
    
    public void searchOrderByDayAll1Cus(Customer cus, String orderDay){
        Set<Customer> listName = this.keySet();
        Iterator<Customer> itCustomer = listName.iterator();
        while(itCustomer.hasNext()){
            Customer tem = itCustomer.next();
            ListOrder list = this.get(tem);
            
            if(tem.getName().equals(cus.getName()) && tem.getPhone().equals(cus.getPhone())){
                list.searchOrderByDayAll(orderDay);
            }
        }
    } 
    
    public void searchOrderByDayAll(String orderDay){
        Set<Customer> listName = this.keySet();
        Iterator<Customer> itCustomer = listName.iterator();
        while(itCustomer.hasNext()){
            Customer tem = itCustomer.next();
            ListOrder list = this.get(tem);
            
            list.searchOrderByDayAll(orderDay);
        }
    } 
    
    public void addCountTime(Customer buyer){
        Set<Customer> listName = this.keySet();
        Iterator<Customer> itCustomer = listName.iterator();
        while(itCustomer.hasNext()){
            Customer tem = itCustomer.next();
            ListOrder list = this.get(tem);
            
            if(tem.getName().equals(buyer.getName())){
                int count = tem.getCountTime() + 1;
                tem.setCountTime(count);
            }
        }
    }
    
    public boolean addCustomerOrder(Customer buyer, Order newOrder){
        ListOrder tem = searchCustomer(buyer);
        if(tem == null){
            ListOrder newList = new ListOrder(newOrder);
            this.put(buyer, newList);
        }
        return false;
    }
}
