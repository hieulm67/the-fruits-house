package DTO;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Min Hiu
 */
public class Menu extends ArrayList<String> {

    private Scanner scan = new Scanner(System.in);

    public int getMenu() {
        int service = 0;
        boolean valid = true;

        System.out.println("");
        System.out.println("--- Welcome to Hieu's The Fruits House ---");
        System.out.println("");
        System.out.println("Thank you for choosing us ");
        System.out.println("");
        System.out.println("Please choose your service: ");
        System.out.println("1.Log in.");
        System.out.println("2.Our Products.");
        System.out.println("3.Visitor.");
        System.out.println("4.Sign up.");
        System.out.println("5.Exit.");
        do {
            try {
                valid = true;
                System.out.print("Please choose: ");
                scan = new Scanner(System.in);
                service = scan.nextInt();
                if (service < 1 || service > 5) {
                    System.out.println("Please try again.");
                    System.out.println("");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Please try again.");
                System.out.println("");
                valid = false;
            }
        } while (valid == false);
        System.out.println("");
        return service;
    }

    public int setMenuAdmin() {
        this.add("Please choose option: ");
        this.add("Fruit Storage Management.");
        this.add("Employee Management.");
        this.add("Customer Management.");
        this.add("Update Personal Information.");
        this.add("Give Vouchers.");
        this.add("Order Problems.");
        this.add("Sign Out.");
        return this.size();
    }

    public int setAdminAdmin() {
        this.add("Please choose option: ");
        this.add("Add New Admin.");
        this.add("Remove Admin.");
        this.add("Employee Management.");
        this.add("Customer Management.");
        this.add("Give Vouchers Admin.");
        this.add("Order Problems.");
        this.add("Sign Out.");
        return this.size();
    }

    public int setMenuVoucher() {
        this.add("Please choose option: ");
        this.add("Discount 100%");
        this.add("Discount 70%");
        this.add("Discount 50%");
        this.add("Discount 30%");
        this.add("Discount 10%");
        this.add("Exit");
        return this.size();
    }

    public int setMenuSaler() {
        this.add("Please choose option: ");
        this.add("View Customers Orders.");
        this.add("View Visitors Orders.");
        this.add("Check Information Customer.");
        this.add("Update Personal Information.");
        this.add("Import Product.");
        this.add("Update Quantity Product.");
        this.add("Order Problems.");
        this.add("Sign Out.");
        return this.size();
    }

    public int setMenuCustomer() {
        this.add("Please choose option: ");
        this.add("Shopping.");
        this.add("My Coupon.");
        this.add("Help Center.");
        this.add("Sign Out.");
        return this.size();
    }

    public int setMenuShopping() {
        this.add("Please choose option: ");
        this.add("Add To Cart.");
        this.add("View Cart.");
        this.add("Update Cart.");
        this.add("Pick Voucher.");
        this.add("Finish Order.");
        return this.size();
    }

    public int setMenuVisitorShopping() {
        this.add("Please choose option: ");
        this.add("Add To Cart.");
        this.add("View Cart.");
        this.add("Update Cart.");
        this.add("Finish Order.");
        return this.size();
    }

    public int setMenuHelpCenter() {
        this.add("Please choose option: ");
        this.add("Update Information");
        this.add("View Rank Climbing.");
        this.add("History Order.");
//        this.add("Order Problems");
        this.add("Exit.");
        return this.size();
    }

    public int setMenuFruitManagement() {
        this.add("Please choose option: ");
        this.add("Import Product.");
        this.add("Update Quantity Product.");
        this.add("Create New Fruit.");
        this.add("Show list fruits.");
        this.add("Update List Of Fruits.");
        this.add("View Customers Order.");
        this.add("View Visitors Order");
        this.add("Exit.");
        return this.size();
    }

    public int setMenuEmployeeManagement() {
        this.add("Please choose option: ");
        this.add("Update Employee.");
        this.add("Add Employee.");
        this.add("Remove Employee.");
        this.add("View All Employees.");
        this.add("Search Employee.");
        this.add("Exit.");
        return this.size();
    }

    public int setMenuUpdateInfo() {
        this.add("Please choose option: ");
        this.add("Password.");
        this.add("Name.");
        this.add("Phone.");
        this.add("Birth date.");
        this.add("Address.");
        this.add("Email.");
        this.add("Exit.");
        return this.size();
    }
    
    public int setMenuUpdateEmployee() {
        this.add("Please choose option: ");
        this.add("Promote Employee");
        this.add("Password.");
        this.add("Name.");
        this.add("Phone.");
        this.add("Birth date.");
        this.add("Address.");
        this.add("Email.");
        this.add("Exit.");
        return this.size();
    }

    public int setMenuUpdateFruit() {
        this.add("Please choose option: ");
        this.add("Name.");
        this.add("Origin.");
        this.add("Price");
        this.add("Exit.");
        return this.size();
    }

    public int setMenuVisitor() {
        this.add("Please choose option: ");
        this.add("Shopping");
        this.add("Term & Right Customer.");
        this.add("Sign up");
        this.add("Sign Out.");
        return this.size();
    }

    public int setMenuCustomerManagement() {
        this.add("Please choose option: ");
        this.add("View All Customers.");
        this.add("Search Customer.");
        this.add("Exit.");
        return this.size();
    }

    public int setMenuCustomerAdminAdmin() {
        this.add("Please choose option: ");
        this.add("View All Customers.");
        this.add("Search Customer.");
        this.add("Set Point Customer.");
        this.add("Exit.");
        return this.size();
    }
    
    public int setMenuUpdateOrder(){
        this.add("Please choose option: ");
        this.add("Status Customer Order.");
        this.add("Status Visitor Order.");
        this.add("Complete Day Customer.");
        this.add("Complete Day Visitor.");
        this.add("Search Customer Order.");
        this.add("Search Visitor Order.");
        this.add("Exit.");
        return this.size();
    }
    
    public int setMenuImport(){
        this.add("Please choose option: ");
        this.add("Import 1 Product.");
        this.add("Import Multi Product.");
        this.add("Exit");
        return this.size();
    }

    public int setMenuUpdateQuantityProduct(){
        this.add("Please choose option: ");
        this.add("Update 1 Product.");
        this.add("Update Multi Product.");
        this.add("Exit");
        return this.size();
    }
    
    public int setSearchCustomerMenu(){
        this.add("Please choose option: ");
        this.add("Search By Login ID.");
        this.add("Search By Login ID & Order Day.");
        this.add("Search By Order Day.");
        this.add("Exit");
        return this.size();
    }
    
    public int setSearchVisitorMenu(){
        this.add("Please choose option: ");
        this.add("Search By Name & Phone.");
        this.add("Search By Name & Phone & Order Day");
        this.add("Search By Order Day.");
        this.add("Exit");
        return this.size();
    }
    
    public int getUserChoice() {
        boolean valid = true;
        int choice = 0;
        do {
            valid = true;
            for (int i = 0; i < this.size(); i++) {
                if (i == 0) {
                    System.out.println(this.get(i));
                } else {
                    System.out.println(i + "- " + this.get(i));
                }
            }
            try {
                scan = new Scanner(System.in);
                System.out.print("Your choice: ");
                choice = scan.nextInt();
                if (choice < 1 || choice >= this.size()) {
                    System.out.println("Please try again.");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println(e);
                valid = false;
            }
            System.out.println("");
        } while (valid == false);
        return choice;
    }
}
