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
public class Account {
    String logInID;
    String password;
    String name;
    String phone;
    String type;

    public Account() {
        logInID = "";
        password ="";
        type = "";
        name = "";
        phone = "";
    }

    public Account(String logInID, String password, String name, String phone, String type) {
        this.logInID = logInID;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public String getLogInID() {
        return logInID;
    }

    public void setLogInID(String logInID) {
        this.logInID = logInID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    
    public void output(){
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Type: " + type);
    }

    @Override
    public String toString() {
        return logInID + "," + password + "," + name + "," + phone + "," + type;
    }
    
    
}
