package DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Inputter {

    static Scanner scan = new Scanner(System.in);

    public static String inputCode() {
        boolean exit = false;
        String code;
        do {
            scan = new Scanner(System.in);
            System.out.print("Enter the ID: ");
            code = scan.nextLine().toUpperCase();
            if (code.contains("@EXIT")) {
                exit = true;
            }
        } while (!code.matches("^[a-zA-Z0-9]+$") && exit == false);
        return code;
    }

    public static String inputName() {
        String name;
        do {
            scan = new Scanner(System.in);
            System.out.print("Enter the name: ");
            name = scan.nextLine().trim();
        } while (!name.matches("^[a-zA-Z]+$"));
        return name;
    }

    public static double inputPrice() {
        double price = -1;
        boolean valid = true;
        do {
            try {
                valid = true;
                scan = new Scanner(System.in);
                System.out.print("Enter the price: ");
                price = scan.nextDouble();
                if (price < 0) {
                    System.out.println("Invalid price.");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid price.");
                valid = false;
            }
        } while (valid == false);
        return price;
    }

    public static int inputQuantity() {
        int quantity = -1;
        boolean valid = true;
        do {
            try {
                valid = true;
                scan = new Scanner(System.in);
                System.out.print("Enter the quantity: ");
                quantity = scan.nextInt();
                if (quantity < 0) {
                    System.out.println("Invalid quantity.");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid quantity.");
                valid = false;
            }
        } while (valid == false);
        return quantity;
    }

    public static String inputOrigin() {
        String origin;
        do {
            scan = new Scanner(System.in);
            System.out.print("Enter the origin: ");
            origin = scan.nextLine().trim();
        } while (!origin.matches("^[a-zA-Z ]+$"));
        return origin;
    }

    public static int getItem() {
        int item = -1;
        boolean valid = true;
        do {
            try {
                valid = true;
                scan = new Scanner(System.in);
                System.out.print("Please choose item: ");
                item = scan.nextInt();
                if (item < 0) {
                    System.out.println("Invalid item.");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid item.");
                valid = false;
            }
        } while (valid == false);
        return item - 1;
    }

    public static String inputCustomerName() {
        String name;
        do {
            scan = new Scanner(System.in);
            System.out.print("Enter your name: ");
            name = scan.nextLine().trim();
        } while (!checkName(name));
        return name;
    }

    private static boolean checkName(String temName) {
        return temName.matches("^[a-zA-Z ]{1,50}+$");
    }

    private static boolean checkBirthDate(String temBirth) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        try {
            Date birthDate = new Date();
            birthDate = formatter.parse(temBirth);
            Date date = new Date();
            if ((birthDate.getYear() + 1900) >= (date.getYear() + 1900) || (birthDate.getYear() + 1900) < (date.getYear() + 1800)) {
                System.out.println("Invalid date.");
                System.out.println("Try again.");
                return false;
            }
        } catch (ParseException pe) {
            System.out.println("Invalid date.");
            System.out.println("Try again.");
            return false;
        } catch (Exception e) {
            System.out.println("Invalid date.");
            System.out.println("Try again.");
            return false;
        }
        return true;
    }

    private static boolean checkAddress(String temAddress) {
        return temAddress.matches("^[a-zA-Z0-9./ ]{2,50}+$");
    }

    private static boolean checkPhone(String temPhone) {
        return temPhone.matches("^[0-9]{10}$");
    }

    private static boolean checkEmail(String temEmail) {
        return temEmail.matches("^[a-zA-Z0-9]+@[a-zA-Z.]+$");
    }

    public static String inputBirthDate() {
        String temBirthDate;
        do {
            System.out.print("Input your birth date: ");
            scan = new Scanner(System.in);
            temBirthDate = scan.nextLine();
        } while (checkBirthDate(temBirthDate) == false);
        return temBirthDate;
    }

    public static String inputAddress() {
        String temAddress;
        do {
            System.out.print("Input your address: ");
            scan = new Scanner(System.in);
            temAddress = scan.nextLine();
            if (temAddress.equals("")) {
                return temAddress;
            }
        } while (checkAddress(temAddress) == false);
        return temAddress;
    }

    public static String inputPhone() {
        String temPhone;
        do {
            System.out.print("Input your phone (10 digits): ");
            scan = new Scanner(System.in);
            temPhone = scan.nextLine();
        } while (checkPhone(temPhone) == false);
        return temPhone;
    }

    public static String inputEmail() {
        String temEmail;
        do {
            System.out.print("Input your email: ");
            scan = new Scanner(System.in);
            temEmail = scan.nextLine();
        } while (checkEmail(temEmail) == false);
        return temEmail;
    }

    private static boolean checkID(String temID) {
        return temID.matches("^[a-zA-Z0-9]{2,30}+$");
    }

    public static String signInID() {
        String loginID;
        do {
            System.out.print("Input login ID: ");
            scan = new Scanner(System.in);
            loginID = scan.nextLine();
        } while (checkID(loginID) == false);
        return loginID;
    }

    public static String signInPassword(boolean isConfirm) {
        String loginPass;
        do {
            if (isConfirm) {
                System.out.print("Confirm your password: ");
            } else {
                System.out.print("Input your password: ");
            }
            scan = new Scanner(System.in);
            loginPass = scan.nextLine();
        } while (checkID(loginPass) == false);
        return loginPass;
    }

    public static boolean inputContinueOption() {
        int option = -1;
        boolean valid = true;
        do {
            try {
                valid = true;
                scan = new Scanner(System.in);
                System.out.println("Do you want to try again: ");
                System.out.println("1.Yes");
                System.out.println("2.No");
                System.out.print("Your choice: ");
                option = scan.nextInt();
                if (option < 0 || option > 2) {
                    System.out.println("Invalid option.");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid option.");
                valid = false;
            }
        } while (valid == false);
        if (option == 1) {
            return true;
        }
        return false;
    }

    public static boolean inputConfirmOption() {
        int option = -1;
        boolean valid = true;
        do {
            try {
                valid = true;
                scan = new Scanner(System.in);
                System.out.println("Are you sure?");
                System.out.println("1.Yes");
                System.out.println("2.No");
                System.out.print("Your choice: ");
                option = scan.nextInt();
                if (option < 0 || option > 2) {
                    System.out.println("Invalid option.");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid option.");
                valid = false;
            }
        } while (valid == false);
        if (option == 1) {
            return true;
        }
        return false;
    }

    public static double inputPoint() {
        double point = -1;
        boolean valid = true;
        do {
            try {
                valid = true;
                scan = new Scanner(System.in);
                System.out.print("Enter the point: ");
                point = scan.nextDouble();
                if (point < 0) {
                    System.out.println("Invalid point.");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid point.");
                valid = false;
            }
        } while (valid == false);
        return point;
    }

    public static String inputStatus() {
        int status = -1;
        boolean valid = true;
        do {
            try {
                valid = true;
                scan = new Scanner(System.in);
                System.out.println("Change status order: ");
                System.out.println("1.In process.");
                System.out.println("2.Complete.");
                System.out.println("3.Cancel.");
                System.out.print("Your choice: ");
                status = scan.nextInt();
                if (status < 1 || status > 3) {
                    System.out.println("Invalid point.");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid point.");
                valid = false;
            }
        } while (valid == false);
        if (status == 1) {
            return "In process";
        } else if (status == 2) {
            return "Complete";
        } else {
            return "Cancel";
        }
    }

    public static String inputIDOrder() {
        String code;
        do {
            scan = new Scanner(System.in);
            System.out.print("Enter Order ID: ");
            code = scan.nextLine();
        } while (!code.matches("^[0-9]+$"));
        return code;
    }

    public static int inputQuantityImport() {
        int quantity = -1;
        boolean valid = true;
        do {
            try {
                valid = true;
                scan = new Scanner(System.in);
                System.out.print("Enter the quantity need to import: ");
                quantity = scan.nextInt();
                if (quantity < 0) {
                    System.out.println("Invalid quantity.");
                    valid = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid quantity.");
                valid = false;
            }
        } while (valid == false);
        return quantity;
    }
    
    public static String inputCompleteDay(String orderDay){
        String temComplete;
        do {
            System.out.print("Input complete day of order: ");
            scan = new Scanner(System.in);
            temComplete = scan.nextLine();
        } while (checkCompleteDay(temComplete, orderDay) == false);
        return temComplete;
    }
    
    public static boolean checkCompleteDay(String completeDay, String orderDay){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        try {
            Date complete = new Date();
            complete = formatter.parse(completeDay);
            Date order = new Date();
            order = formatter.parse(orderDay);
            if (complete.before(order)) {
                System.out.println("Invalid date.");
                System.out.println("Try again.");
                return false;
            }
        } catch (ParseException pe) {
            System.out.println("Invalid date.");
            System.out.println("Try again.");
            return false;
        } catch (Exception e) {
            System.out.println("Invalid date.");
            System.out.println("Try again.");
            return false;
        }
        return true;
    }
    
    public static boolean checkOrderDay(String orderDay){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        try {
            Date order = formatter.parse(orderDay);
            System.out.println(order.toString());
            Date date = new Date();
            if(order.after(date) || order.getYear() + 1900 < date.getYear() + 1880){
                System.out.println("Invalid date.");
                System.out.println("Try again.");
                return false;
            }
        } catch (ParseException pe) {
            System.out.println("Invalid date.");
            System.out.println("Try again.");
            return false;
        } catch (Exception e) {
            System.out.println("Invalid date.");
            System.out.println("Try again.");
            return false;
        }
        return true;
    }
    
    public static String inputOrderDay(){
        String temComplete;
        do {
            System.out.print("Input order day of order: ");
            scan = new Scanner(System.in);
            temComplete = scan.nextLine();
        } while (checkOrderDay(temComplete) == false);
        return temComplete;
    }
    
    public static String importMultiProduct(){
        String items;
        do{
            System.out.println("Input follow the format: Item-Quantity,Item-Quantity,... ");
            System.out.print("Your choice: ");
            scan = new Scanner(System.in);
            items = scan.nextLine();
        }
        while(!checkItems(items));
        return items;
    }
    
    public static boolean checkItems(String items){
        return items.matches("^[0-9-,]*$");
    }
    
    public static String deleteMultiProduct(){
        String items;
        do{
            System.out.println("Input follow the format: Item, Item , Item1-Item2 to delete from Item1 to Item2 ");
            System.out.print("Your choice: ");
            scan = new Scanner(System.in);
            items = scan.nextLine();
        }
        while(!checkItems(items));
        return items;
    }

//    public static void main(String[] args) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        Date orderDate = new Date();
//        Date completeDate = new Date();
//        completeDate.setDate(completeDate.getDate() + 3);
//        String orderDay = formatter.format(orderDate);
//        String completeDay = formatter.format(completeDate);
//        System.out.println(orderDay);
//        System.out.println(completeDay);
//    }
}
