package DAO;

import DTO.Fruit;
import DTO.Inputter;
import DTO.Menu;
import DTO.closeCustomers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Min Hiu
 */
public class FruitsList extends ArrayList<Fruit> implements Serializable {

    private boolean inputNewFruit(Fruit newFruit) {
        System.out.println("--- INPUT NEW FRUIT ---");
        System.out.println("Enter @EXIT to exit to the menu in field input ID.");
        String temID;
        boolean exit = false;
        do {
            temID = Inputter.inputCode();
            if (temID.contains("@EXIT")) {
                exit = true;
            }
        } while (checkExistedCode(temID) == true);
        if (exit != true) {
            newFruit.setId(temID);
            newFruit.setName(Inputter.inputName());
            newFruit.setPrice(Inputter.inputPrice());
            newFruit.setOrigin(Inputter.inputOrigin());
        }
        return exit;
    }

    private boolean checkExistedCode(String code) {
        for (int i = 0; i < this.size(); i++) {
            if (code.equals(this.get(i).getId())) {
                System.out.println("The code is existed in the list");
                System.out.println("Please try another.");
                return true;
            }
        }
        return false;
    }

    public void addFruitToList() {
        boolean exit = false;
        do {
            Fruit newFruit = new Fruit();
            exit = inputNewFruit(newFruit);
            if (exit == false) {
                if (this.add(newFruit)) {
                    System.out.println("Add Success.");
                    System.out.println("");
                } else {
                    System.out.println("Add Fail.");
                    System.out.println("");
                }
            } else {
//                System.out.println("Exit.");
                System.out.println("");
            }
        } while (exit != true);
    }

    public void showListFruits() {
        if (!this.isEmpty()) {
            System.out.println("");
            System.out.printf("%15s|%15s|%15s|%15s|%15s\n", "Item", "Fruit Name", "Origin", "Price", "Quantity");
            for (int i = 0; i < this.size(); i++) {
                System.out.printf("%15s|%15s|%15s|%15s|%15s\n", i + 1 + "", this.get(i).getName(), this.get(i).getOrigin(), this.get(i).getPrice() + "$", this.get(i).getQuantity());
            }

        } else {
            System.out.println("The list is empty!");
        }
        System.out.println("");
    }

    public void updateListFruit(boolean isCart) {
        int choice = 0;
        boolean valid = true;
        if (!this.isEmpty()) {
            do {
                valid = true;
                System.out.println("1.Update item selected.");
                System.out.println("2.Remove item selected.");
                System.out.println("3.Remove multi items.");
                System.out.print("Your choice: ");
                try {
                    Scanner scan = new Scanner(System.in);
                    choice = scan.nextInt();
                    if (choice < 1 || choice > 3) {
                        System.out.println("Invalid option.");
                        valid = false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    valid = false;
                }
            } while (valid == false);
            switch (choice) {
                case 1:
                    update(isCart);
                    break;
                case 2:
                    delete(isCart);
                    break;
                case 3:
                    deleteMulti(isCart);
                    break;
            }
        } else {
            if (!isCart) {
                System.out.println("The list is empty!");
            } else {
                System.out.println("The cart is empty!");
            }
            System.out.println("");
        }
    }

    private void deleteMulti(boolean isCart) {
        if (isCart) {
            addedToCart();
        } else {
            showListFruits();
        }
        ArrayList<Integer> needDelete = new ArrayList<>();
        String items = Inputter.deleteMultiProduct();
        StringTokenizer stk = new StringTokenizer(items, ", ");
        String s = "";
        while (stk.hasMoreTokens()) {
            s = stk.nextToken();
            if (s.contains("-")) {
                String pattern = s;
                String[] tem = pattern.split("-");
                try {
                    int item1 = Integer.parseInt(tem[0]) - 1;
                    int item2 = Integer.parseInt(tem[1]) - 1;

                    if (item2 < item1) {
                        int t = item1;
                        item1 = item2;
                        item2 = t;
                    }

                    for (int i = item1; i <= item2; i++) {
                        if (!checkExistedItem(needDelete, i)) {
                            needDelete.add(i);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                int tmp = Integer.parseInt(s) - 1;
                if (!checkExistedItem(needDelete, tmp)) {
                    needDelete.add(tmp);
                }
            }
        }
        if (Inputter.inputConfirmOption()) {
            for (int i = needDelete.size()-1; i >= 0; i--) {
                int index = needDelete.get(i);
                System.out.println(index);
                Fruit tem = this.remove(index);
                if (tem != null) {
                    System.out.println("Remove " + tem.getName() + " success.");
                    System.out.println("");
                } else {
                    System.out.println("Remove fail");
                    System.out.println("");
                }
            }
        }
    }

    private boolean checkExistedItem(ArrayList<Integer> needDelete, int tem) {
        for (int i = 0; i < needDelete.size(); i++) {
            if (tem == needDelete.get(i)) {
                return true;
            }
        }
        if (tem >= this.size()) {
            return true;
        }
        return false;
    }

    private void update(boolean isCart) {
        if (isCart) {
            addedToCart();
        } else {
            showListFruits();
        }
        int item = -1;
        do {
            item = Inputter.getItem();
        } while (item >= this.size());
        if (isCart == false) {
            int choice = 0;
            int limit = 0;
            do {
                Menu menu = new Menu();
                limit = menu.setMenuUpdateFruit();
                choice = menu.getUserChoice();
                switch (choice) {
                    case 1:
                        this.get(item).setName(Inputter.inputName());
                        break;
                    case 2:
                        this.get(item).setOrigin(Inputter.inputOrigin());
                        break;
                    case 3:
                        this.get(item).setPrice(Inputter.inputPrice());
                        break;
                    default:
                        break;
                }
            } while (choice != limit - 1);
        }
        if (isCart == true) {
            this.get(item).setQuantity(Inputter.inputQuantity());
        }
    }

    private void delete(boolean isCart) {
        if (isCart) {
            addedToCart();
        } else {
            showListFruits();
        }
        int item = -1;
        do {
            item = Inputter.getItem();
        } while (item >= this.size());
        if (Inputter.inputConfirmOption()) {
            Fruit tem = this.remove(item);
            if (tem != null) {
                System.out.println("Remove " + tem.getName() + " success.");
                System.out.println("");
            } else {
                System.out.println("Remove fail");
                System.out.println("");
            }
        }
    }

    public int checkExistedInList(Fruit addItem) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getName().equals(addItem.getName())) {
                return i;
            }
        }
        return -1;
    }

    public void addedToCart() {
        if (!this.isEmpty()) {
            System.out.println("");
            System.out.printf("%15s|%15s|%15s|%15s|\n", "Product", "Quantity", "Price", "Amount");
            for (int i = 0; i < this.size(); i++) {
                double amount = this.get(i).getPrice() * this.get(i).getQuantity();
                System.out.printf("%15s|%15d|%15s|%15s|\n", i + 1 + "." + this.get(i).getName(), this.get(i).getQuantity(), this.get(i).getPrice() + "$", amount + "$");
            }
        } else {
            System.out.println("The cart is empty!");
        }
        System.out.println("");
    }

    public double getTotalPrice() {
        if (!this.isEmpty()) {
            double total = 0;
            for (int i = 0; i < this.size(); i++) {
                double amount = this.get(i).getPrice() * this.get(i).getQuantity();
                total += amount;
            }
            return total;
        }
        return 0;
    }

    public double getTotalPrice(closeCustomers tem, String voucher) {
        if (!this.isEmpty()) {
            double total = 0;
            for (int i = 0; i < this.size(); i++) {
                double amount = this.get(i).getPrice() * this.get(i).getQuantity();
                total += amount;
            }
            if (voucher.equals("")) {
                return discountRankTotal(total, tem);
            } else {
                return discountVoucher(total, tem, voucher);
            }
        }
        return 0;
    }

    private double discountRankTotal(double total, closeCustomers tem) {
        if (tem.getRank().equals("Diamond")) { // 40%
            total = (total * 60) / 100;
        } else if (tem.getRank().equals("Platinum")) { // 30%
            total = (total * 70) / 100;
        } else if (tem.getRank().equals("Gold")) {// 20%
            total = (total * 80) / 100;
        } else if (tem.getRank().equals("Silver")) { // 10%
            total = (total * 90) / 100;
        }
        return total;
    }

    private double discountVoucher(double total, closeCustomers tem, String voucher) {
        if (voucher.equals("Discount 100%")) {
            total = 0;
        } else if (voucher.equals("Discount 70%")) {
            total = (total * 30) / 100;
        } else if (voucher.equals("Discount 50%")) {
            total = total / 2;
        } else if (voucher.equals("Discount 30%")) {
            if (tem.getRank().equals("Gold")) { // 50%
                total = total / 2;
            } else if (tem.getRank().equals("Silver")) { // 40%
                total = (total * 60) / 100;
            } else {
                total = (total * 70) / 100;
            }
        } else if (voucher.equals("Discount 10%")) {
            if (tem.getRank().equals("Diamond")) { // 40% + 10%
                total = total / 2;
            } else if (tem.getRank().equals("Platinum")) { // 30% + 10%
                total = (total * 60) / 100;
            } else if (tem.getRank().equals("Gold")) { // 20% + 10%
                total = (total * 70) / 100;
            } else if (tem.getRank().equals("Silver")) { // 10% + 10%
                total = (total * 80) / 100;
            } else {
                total = (total * 90) / 100;
            }
        }
        return total;
    }

    public void importProduct() {
        showListFruits();
        int item = -1;
        do {
            item = Inputter.getItem();
        } while (item >= this.size());

        if (item != 0) {
            int curQuantity = this.get(item).getQuantity();
            this.get(item).setQuantity(Inputter.inputQuantityImport() + curQuantity);
            System.out.println("Import Success.");
        }
    }

    public void updateQuantityProduct() {
        showListFruits();
        int item = -1;
        do {
            System.out.println("Press 0 to exit: ");
            item = Inputter.getItem();
        } while (item >= this.size());
        if (item != 0) {
            this.get(item).setQuantity(Inputter.inputQuantity());
            System.out.println("Update Success.");
            System.out.println("");
        }
    }

    public void importMultiProduct() {
        showListFruits();
        String items = Inputter.importMultiProduct();
        StringTokenizer stk = new StringTokenizer(items, ", ");
        while (stk.hasMoreTokens()) {
            String pattern = stk.nextToken();
            String[] tem = pattern.split("-");
            try {
                int item = Integer.parseInt(tem[0]) - 1;
                int curQuantity = this.get(item).getQuantity();
                this.get(item).setQuantity(Integer.parseInt(tem[1]) + curQuantity);
                System.out.println("Import Success.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateQuantityMultiProduct() {
        showListFruits();
        String items = Inputter.importMultiProduct();
        StringTokenizer stk = new StringTokenizer(items, ", ");
        while (stk.hasMoreTokens()) {
            String pattern = stk.nextToken();
            String[] tem = pattern.split("-");
            try {
                int item = Integer.parseInt(tem[0]) - 1;
                System.out.println(item);
                this.get(item).setQuantity(Integer.parseInt(tem[1]));
                System.out.println("Update Success.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
