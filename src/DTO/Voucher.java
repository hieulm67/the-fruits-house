/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author emiul
 */
public class Voucher extends ArrayList<String> {

    public String output() {
        String s = "";
        for (int i = 0; i < this.size(); i++) {
            if (i == 0) {
                s = s + this.get(i);
            } else {
                s = s + "," + this.get(i);
            }
        }
        return s;
    }

    public void showVouchers() {
        System.out.println("Your vouchers: ");
        for (int i = 0; i < this.size(); i++) {
            if (i == 0) {
                System.out.print(this.get(i));
            } else {
                System.out.print(", " + this.get(i));
            }
        }
        System.out.println("");
    }

    public String getVoucher() {
        String voucher = "";
        if (!this.isEmpty()) {
            int choice = 0;
            boolean valid = true;
            Scanner scan = new Scanner(System.in);
            System.out.println("Your voucher: ");
            for (int i = 0; i < this.size(); i++) {
                System.out.println((i + 1) + "- " + this.get(i));
            }
            do {
                try {
                    valid = true;
                    System.out.print("Please choose your voucher: ");
                    choice = scan.nextInt();
                    if (choice < 1 || choice > this.size()) {
                        valid = false;
                        System.out.println("Invalid option.");
                        System.out.println("Please try again.");
                    }
                } catch (Exception e) {
                    valid = false;
                    System.out.println("Invalid option.");
                    System.out.println("Please try again.");
                }
            } while (valid == false);
            voucher = this.get(choice - 1);
        }
        else{
            System.out.println("List voucher is empty.");
        }
        System.out.println("");
        return voucher;
    }
}
