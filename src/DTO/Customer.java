/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;


/**
 *
 * @author emiul
 */
public class Customer{
    private String name;
    private String phone;
    private int countTime;

    public Customer() {
        name = "";
        phone = "";
        countTime = 0;
    }

    public Customer(String name, String phone, int countTime) {
        this.name = name;
        this.phone = phone;
        this.countTime = countTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCountTime() {
        return countTime;
    }

    public void setCountTime(int countTime) {
        this.countTime = countTime;
    }
    
    public void output(){
        System.out.println("");
        System.out.println("Deatil Informaton: ");
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
    }

    @Override
    public String toString() {
        return name + "," + phone + "," + countTime;
    }
}
