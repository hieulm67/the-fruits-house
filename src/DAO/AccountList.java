/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Account;
import DTO.Admin;
import DTO.Inputter;
import DTO.Saler;
import DTO.Voucher;
import DTO.closeCustomers;
import java.util.ArrayList;

/**
 *
 * @author emiul
 */
public class AccountList extends ArrayList<Account> {

    final double diamondPoint = 3000;
    final double platinumPoint = 1000;
    final double goldPoint = 500;
    final double silverPoint = 200;

    public Account login() {
        boolean again = true;
        do {
            String login = Inputter.signInID();
            String password = Inputter.signInPassword(false);
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getLogInID().equals(login) && this.get(i).getPassword().equals(password)) {
                    System.out.println("");
                    return this.get(i);
                }
            }
            System.out.println("This account is not existed.");
            again = Inputter.inputContinueOption();
        } while (again == true);
        return null;
    }

    private boolean checkAdminAdmin(Account tem) {
        return tem.getLogInID().equals("admin") && tem.getPassword().equals("admin");
    }

    public int typeAccount(Account tem) {
        if (checkAdminAdmin(tem)) {
            return 0;
        } else if (tem instanceof Admin) {
            return 1;
        } else if (tem instanceof Saler) {
            return 2;
        }
        return 3;
    }

    private boolean checkLoginID(String temLoginID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getLogInID().equals(temLoginID)) {
                System.out.println("The login id is already existed.");
                System.out.println("Please try another.");
                System.out.println("");
                return true;
            }
        }
        return false;
    }

    public void addSaler() {
        String temLoginID = "";
        do {
            temLoginID = Inputter.signInID();
        } while (checkLoginID(temLoginID));
        String temPassword = "";
        String confirmPassword = "";
        do {
            temPassword = Inputter.signInPassword(false);
            confirmPassword = Inputter.signInPassword(true);
        } while (!temPassword.equals(confirmPassword));
        Account newSaler = new Saler(temLoginID, temPassword, Inputter.inputName(), Inputter.inputPhone(), "Saler", Inputter.inputBirthDate(), Inputter.inputAddress(), Inputter.inputEmail(), new Voucher());
        if (this.add(newSaler)) {
            System.out.println("Add Success.");
        } else {
            System.out.println("Add Fail.");
        }
        System.out.println("");
    }

    public void addAdmin() {
        String temLoginID = "";
        do {
            temLoginID = Inputter.signInID();
        } while (checkLoginID(temLoginID));
        String temPassword = "";
        String confirmPassword = "";
        do {
            temPassword = Inputter.signInPassword(false);
            confirmPassword = Inputter.signInPassword(true);
        } while (!temPassword.equals(confirmPassword));
        Account newAdmin = new Admin(temLoginID, temPassword, Inputter.inputName(), Inputter.inputPhone(), "Admin", Inputter.inputBirthDate(), Inputter.inputAddress(), Inputter.inputEmail(), new Voucher());
        if (this.add(newAdmin)) {
            System.out.println("Add Success.");
        } else {
            System.out.println("Add Fail.");
        }
        System.out.println("");
    }

    public void addCustomer() {
        String temLoginID = "";
        do {
            temLoginID = Inputter.signInID();
        } while (checkLoginID(temLoginID));
        String temPassword = "";
        String confirmPassword = "";
        do {
            temPassword = Inputter.signInPassword(false);
            confirmPassword = Inputter.signInPassword(true);
        } while (!temPassword.equals(confirmPassword));
        Account newCustomer = new closeCustomers(temLoginID, temPassword, Inputter.inputName(), Inputter.inputPhone(), "Close Customer", Inputter.inputBirthDate(), Inputter.inputAddress(), Inputter.inputEmail(), "New Member", 0, new Voucher());
        if (this.add(newCustomer)) {
            System.out.println("Add Success.");
        } else {
            System.out.println("Add Fail.");
        }
        System.out.println("");
    }

    public void signUp(String name, String phone) {
        String temLoginID = "";
        do {
            temLoginID = Inputter.signInID();
        } while (checkLoginID(temLoginID));
        String temPassword = "";
        String confirmPassword = "";
        do {
            temPassword = Inputter.signInPassword(false);
            confirmPassword = Inputter.signInPassword(true);
        } while (!temPassword.equals(confirmPassword));
        Account newCustomer = new closeCustomers(temLoginID, temPassword, name, phone, "Close Customer", Inputter.inputBirthDate(), Inputter.inputAddress(), Inputter.inputEmail(), "New Member", 0, new Voucher());
        if (this.add(newCustomer)) {
            System.out.println("Sign Up Success.");
        } else {
            System.out.println("Sign Up Fail.");
        }
        System.out.println("");
    }

    public void removeSaler() {
        String removeLoginID = Inputter.signInID();
        Account saler = searchByLoginID(removeLoginID, "Saler");
        if (saler != null) {
            if (saler instanceof Saler) {
                try {
                    for (int i = 0; i < this.size(); i++) {
                        if (this.get(i).getLogInID().equals(removeLoginID) && this.get(i).getType().equals("Saler")) {
                            Account delete = null;
                            if (Inputter.inputConfirmOption()) {
                                if ((delete = this.remove(i)) != null) {
                                    System.out.println("Remove Success.");
                                } else {
                                    System.out.println("Remove Fail.");
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("This account is not Saler.");
            }
        } else {
            System.out.println("This account id not existed!");
        }
        System.out.println("");
    }

    public void removeAdmin() {
        String removeLoginID = Inputter.signInID();
        Account admin = searchByLoginID(removeLoginID, "Admin");
        if (admin != null) {
            if (admin instanceof Admin) {
                try {
                    for (int i = 0; i < this.size(); i++) {
                        if (this.get(i).getLogInID().equals(removeLoginID) && this.get(i).getType().equals("Admin")) {
                            Account delete = null;
                            if (Inputter.inputConfirmOption()) {
                                if ((delete = this.remove(i)) != null) {
                                    System.out.println("Remove Success.");
                                } else {
                                    System.out.println("Remove Fail.");
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("This account is not Admin.");
            }
        } else {
            System.out.println("This account id not existed!");
        }
        System.out.println("");
    }

    public void removeCustomer() {
        String removeLoginID = Inputter.signInID();
        Account customer = searchByLoginID(removeLoginID, "Close Customer");
        if (customer != null) {
            if (customer instanceof closeCustomers) {
                try {
                    for (int i = 0; i < this.size(); i++) {
                        if (this.get(i).getLogInID().equals(removeLoginID) && this.get(i).getType().equals("Close Customer")) {
                            Account delete = null;
                            if (Inputter.inputConfirmOption()) {
                                if ((delete = this.remove(i)) != null) {
                                    System.out.println("Remove Success.");
                                } else {
                                    System.out.println("Remove Fail.");
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("This account is not Close Customer.");
            }
        } else {
            System.out.println("This account id not existed!");
        }
        System.out.println("");
    }

    public int getIndex(Account tem) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getLogInID().equals(tem.getLogInID())) {
                return i;
            }
        }
        return -1;
    }

    public String getRank(closeCustomers tem) {
        double point = tem.getPoint();
        if (point >= diamondPoint) {
            return "Diamond";
        } else if (point >= platinumPoint) {
            return "Platinum";
        } else if (point >= goldPoint) {
            return "Gold";
        } else if (point >= silverPoint) {
            return "Silver";
        } else {
            return "New Member";
        }
    }

    public Account searchByLoginID(String loginID, String type) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getLogInID().equals(loginID) && this.get(i).getType().equals(type)) {
                return this.get(i);
            }
        }
        return null;
    }

    public Account searchByLoginID(String loginID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getLogInID().equals(loginID)) {
                return this.get(i);
            }
        }
        return null;
    }

    public closeCustomers searchCustomerByLoginID() {
        String loginID = Inputter.signInID();
        Account tem = searchByLoginID(loginID, "Close Customer");
        if (tem != null) {
            if (tem instanceof closeCustomers) {
                return (closeCustomers) tem;
            } else {
                System.out.println("This account is not CLose Customer!");
            }
        } else {
            System.out.println("This account id not existed!");
        }
        System.out.println("");
        return null;
    }

    public void promoteEmp(Account saler) {
        if (saler != null) {
            if (saler instanceof Saler) {
                try {
                    if (this.add(new Admin((Saler) saler))) {
                        System.out.println("Promote Success.");
                        this.remove(saler);
                    } else {
                        System.out.println("Promote Fail.");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("This account is not Saler!");
            }
        } else {
            System.out.println("This account is not existed!");
        }
        System.out.println("");
    }

    private int getNoOfType(String type) {
        int count = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getType().equals(type)) {
                count++;
            }
        }
        return count;
    }

    public void showSalers() {
        int count = 1;
        System.out.println("Number of salers: " + getNoOfType("Saler"));
        System.out.println("");
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) instanceof Saler) {
                System.out.println("Saler " + count);
                count++;
                this.get(i).output();
            }
        }
        System.out.println("");
    }

    public void showCustomers() {
        int count = 1;
        System.out.println("Number of customers: " + getNoOfType("Close Customer"));
        System.out.println("");
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) instanceof closeCustomers) {
                System.out.println("Customer " + count);
                count++;
                this.get(i).output();
            }
        }
        System.out.println("");
    }

    public void giveVoucherAdmin(String discount, Account tem) {
        if (tem != null) {
            if (tem instanceof Admin) {
                int quantity = Inputter.inputQuantity();
                for (int i = 0; i < quantity; i++) {
                    ((Admin) tem).getLocalVoucher().add(discount);
                }
                System.out.println("Add Voucher Success.");
            }
        } else {
            System.out.println("The account is not Admin!");
        }
        System.out.println("");
    }

    public void giveVoucherSaler(String discount, Account tem) {
        if (tem != null) {
            if (tem instanceof Saler) {
                int quantity = Inputter.inputQuantity();
                for (int i = 0; i < quantity; i++) {
                    ((Saler) tem).getLocalVoucher().add(discount);
                }
                System.out.println("Add Voucher Success.");
            }
        } else {
            System.out.println("The account is not Saler!");
        }
        System.out.println("");
    }

    public void giveVoucherCustomer(String discount, Account tem) {
        if (tem != null) {
            if (tem instanceof closeCustomers) {
                int quantity = Inputter.inputQuantity();
                for (int i = 0; i < quantity; i++) {
                    ((closeCustomers) tem).getVoucher().add(discount);
                }
                System.out.println("Add Voucher Success.");
            }
        } else {
            System.out.println("The account is not Close Customer!");
        }
        System.out.println("");
    }

    public void giveVoucher(String type, String discount) {
        boolean again = true;
        do {
            String loginID = Inputter.signInID();
            Account tem = searchByLoginID(loginID);
            if (tem != null) {
                if (type.equals("Admin") && tem instanceof Admin) {
                    giveVoucherAdmin(discount, tem);
                } else if (type.equals("Saler") && tem instanceof Saler) {
                    giveVoucherSaler(discount, tem);
                } else if (type.equals("Close Customer") && tem instanceof closeCustomers) {
                    giveVoucherCustomer(discount, tem);
                } else if (type.equals("Saler and Close Customer") && (tem instanceof Saler || tem instanceof closeCustomers)) {
                    if (tem instanceof Saler) {
                        giveVoucherSaler(discount, tem);
                    }
                    if (tem instanceof closeCustomers) {
                        giveVoucherCustomer(discount, tem);
                    }
                } else {
                    System.out.println("Invalid type");
                }
                again = Inputter.inputContinueOption();
            } else {
                System.out.println("The account is not existed!");
                again = Inputter.inputContinueOption();
            }
        } while (again == true);
        System.out.println("");
    }

    public void viewRankProcess(String rank, Account tem) {
        if (rank.equals("Diamond")) {
            System.out.println("You are already being at the highest rank.");
        } else if (rank.equals("Platinum")) {
            System.out.println("You need " + (diamondPoint - ((closeCustomers) tem).getPoint()) + " to reach Diamond Member.");
        } else if (rank.equals("Gold")) {
            System.out.println("You need " + (platinumPoint - ((closeCustomers) tem).getPoint()) + " to reach Platinum Member.");
        } else if (rank.equals("Silver")) {
            System.out.println("You need " + (goldPoint - ((closeCustomers) tem).getPoint()) + " to reach Gold Member.");
        } else {
            System.out.println("You need " + (silverPoint - ((closeCustomers) tem).getPoint()) + " to reach Silver Member.");
        }
        System.out.println("");
    }

    public void showInfoCustomer() {
        boolean again = false;
        try {
            do {
                String loginID = Inputter.signInID();
                closeCustomers tem = (closeCustomers) searchByLoginID(loginID, "Close Customer");
                if (tem != null) {
                    tem.output();
                } else {
                    System.out.println("The customer is not existed.");
                }
                again = Inputter.inputContinueOption();
            } while (again == true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getDiscount(String rank) {
        if (rank.equals("Diamond")) {
            return "40%";
        } else if (rank.equals("Platinum")) {
            return "30%";
        } else if (rank.equals("Gold")) {
            return "20%";
        } else if (rank.equals("Silver")) {
            return "10%";
        } else {
            return "New Member";
        }
    }
}
