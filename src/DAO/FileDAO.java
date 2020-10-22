package DAO;

import DTO.Account;
import DTO.Admin;
import DTO.Customer;
import DTO.Fruit;
import DTO.Order;
import DTO.Saler;
import DTO.Voucher;
import DTO.closeCustomers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
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
public class FileDAO {

    public static FruitsList readFruitListText(String fileName) {
        FileReader readFile = null;
        BufferedReader bufferRead = null;
        FruitsList tem = new FruitsList();
        try {
            readFile = new FileReader(fileName);
            bufferRead = new BufferedReader(readFile);
            while (bufferRead.ready()) {
                String[] line = bufferRead.readLine().split(",");
                Fruit temFruit = new Fruit(line[0], line[1], Double.parseDouble(line[2]), Integer.parseInt(line[3]), line[4]);
                tem.add(temFruit);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (readFile != null) {
                    readFile.close();
                }
                if (bufferRead != null) {
                    bufferRead.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return tem;
    }

    public static boolean writeFruitListText(String fileName, FruitsList list) {
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(fileName);
            for (int i = 0; i < list.size(); i++) {
                writter.println(list.get(i).toString());
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (writter != null) {
                    writter.close();
                }
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    public static OrderMangement readOrderManagementText(String fileName, ArrayList<Customer> list) {
        FileReader readFile = null;
        BufferedReader bufferRead = null;
        OrderMangement tem = new OrderMangement();
        String s = "";
        try {
            Customer cus = null;
            ListOrder listOrder = null;
            Order newOrder = null;
            FruitsList listFruits = null;
            String orderID = "";
            String saler = "";
            String status = "";
            String total = "";
            String orderDay = "";
            String completeDay = "";

            readFile = new FileReader(fileName);
            bufferRead = new BufferedReader(readFile);
            while (bufferRead.ready()) {
                if ((s = bufferRead.readLine()) != null) {
                    boolean isCustomerName = (s.charAt(s.length() - 1) == ':');
                    boolean isOrderID = (s.contains("ID"));
                    boolean isSaler = (s.contains("Saler"));
                    boolean isTotal = (s.contains("Total"));
                    boolean isStatus = (s.contains("Status"));
                    boolean isOrderDay = (s.contains("Order Day"));
                    boolean isCompeleteDay = (s.contains("Complete Day"));
                    
                    if (isCustomerName) {
                        if ((cus != null)) {
                            newOrder = new Order(orderID, listFruits, Double.parseDouble(total), saler, status, orderDay, completeDay);
                            listOrder.addOrder(newOrder);
                            tem.put(cus, listOrder);
                        }
                        String customerName = s.substring(0, s.length() - 1);
                        for (int i = 0; i < list.size(); i++) {
                            if (customerName.equals(list.get(i).getPhone())) {
                                cus = list.get(i);
                                newOrder = null;
                                listFruits = null;
                                listOrder = null;
                            }
                        }
                        listOrder = new ListOrder(newOrder);
                    } else if (isOrderID) {
                        if (listFruits != null) {
                            newOrder = new Order(orderID, listFruits, Double.parseDouble(total), saler, status, orderDay, completeDay);
//                            listOrder.put(orderID, newOrder);
                            listOrder.addOrder(newOrder);
                        }
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                orderID = s.substring(i + 1);
                                newOrder = null;
                                listFruits = null;
                            }
                        }
                        listFruits = new FruitsList();
                    } else if (isSaler) {
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                saler = s.substring(i + 1);
                            }
                        }
                    }
                    else if(isTotal){
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                total = s.substring(i + 1);
                            }
                        }
                    }
                    else if(isStatus){
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                status = s.substring(i + 1);
                            }
                        }
                    }
                    else if(isOrderDay){
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                orderDay = s.substring(i + 1);
                            }
                        }
                    }
                    else if(isCompeleteDay){
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                completeDay = s.substring(i + 1);
                            }
                        }
                    }
                    else {
                        String[] tmp = s.split(",");
                        Fruit newFruit = new Fruit(tmp[0], tmp[1], Double.parseDouble(tmp[2]), Integer.parseInt(tmp[3]), tmp[4]);
                        listFruits.add(newFruit);
                    }
                }
            }
            newOrder = new Order(orderID, listFruits, Double.parseDouble(total), saler, status, orderDay, completeDay);
            listOrder = new ListOrder(orderID, newOrder);
            tem.put(cus, listOrder);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (readFile != null) {
                    readFile.close();
                }
                if (bufferRead != null) {
                    bufferRead.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return tem;
    }

    public static boolean writeOrderManagementText(String fileName, OrderMangement management) {
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(fileName);
            Set<Customer> listName = management.keySet();
            Iterator<Customer> itCustomer = listName.iterator();
            while (itCustomer.hasNext()) {
                Customer tem = itCustomer.next();
                writter.println(tem.getPhone()+ ":");
                ListOrder list = management.get(tem);

                Set<String> listID = list.keySet();
                Iterator<String> it = listID.iterator();
                while (it.hasNext()) {
                    String id = it.next();
                    writter.println("ID:" + id);
                    Order order = list.get(id);
                    writter.println("Order Day:" + order.getDateOrder());
                    writter.println("Complete Day:" + order.getDateCome());
                    writter.println("Saler:" + order.getSaler());
                    writter.println("Total:" + order.getTotal());
                    writter.println("Status:" + order.getStatus());
                    FruitsList fruitList = order.getListFruitsInOrder();
                    for (int i = 0; i < fruitList.size(); i++) {
                        writter.println(fruitList.get(i).toString());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (writter != null) {
                    writter.close();
                }
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Customer> readVisitorText(String fileName) {
        FileReader readFile = null;
        BufferedReader bufferRead = null;
        ArrayList<Customer> tem = new ArrayList<>();
        try {
            readFile = new FileReader(fileName);
            bufferRead = new BufferedReader(readFile);
            while (bufferRead.ready()) {
                String s = bufferRead.readLine();
                String[] info = s.split(",");
                Customer temCus = new Customer(info[0], info[1], Integer.parseInt(info[2]));
                tem.add(temCus);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (readFile != null) {
                    readFile.close();
                }
                if (bufferRead != null) {
                    bufferRead.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return tem;
    }

    public static boolean writeVisitorText(String fileName, ArrayList<Customer> list) {
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(fileName);
            for (int i = 0; i < list.size(); i++) {
                writter.println(list.get(i).toString());
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (writter != null) {
                    writter.close();
                }
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    public static AccountList readAccountText(String fileName) {
        FileReader readFile = null;
        BufferedReader bufferRead = null;
        AccountList tem = new AccountList();
        String s = "";
        try {
            readFile = new FileReader(fileName);
            bufferRead = new BufferedReader(readFile);
            while (bufferRead.ready()) {
                if ((s = bufferRead.readLine()) != null) {
                    boolean isAdmin = s.contains("Admin");
                    boolean isSaler = s.contains("Saler");
                    boolean isCustomer = s.contains("Close Customer");
                    if (isAdmin) {
                        String[] info = s.split(",");
                        Voucher listVoucher = new Voucher();
                        String vouchers = "";
                        if ((vouchers = bufferRead.readLine()) != null) {
                            StringTokenizer stk = new StringTokenizer(vouchers, ",");
                            while (stk.hasMoreTokens()) {
                                listVoucher.add(stk.nextToken());
                            }
                        }
                        Account newAccount = new Admin(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], listVoucher);
                        tem.add(newAccount);
                    } else if (isSaler) {
                        String[] info = s.split(",");
                        Voucher listVoucher = new Voucher();
                        String vouchers = "";
                        if ((vouchers = bufferRead.readLine()) != null) {
                            StringTokenizer stk = new StringTokenizer(vouchers, ",");
                            while (stk.hasMoreTokens()) {
                                listVoucher.add(stk.nextToken());
                            }
                        }
                        Account newAccount = new Saler(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], listVoucher);
                        tem.add(newAccount);
                    } else if (isCustomer) {
                        String[] info = s.split(",");
                        Voucher listVoucher = new Voucher();
                        String vouchers = "";
                        if ((vouchers = bufferRead.readLine()) != null) {
                            StringTokenizer stk = new StringTokenizer(vouchers, ",");
                            while (stk.hasMoreTokens()) {
                                listVoucher.add(stk.nextToken());
                            }
                        }
                        Account newAccount = new closeCustomers(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], Double.parseDouble(info[9]), listVoucher);
                        tem.add(newAccount);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (readFile != null) {
                    readFile.close();
                }
                if (bufferRead != null) {
                    bufferRead.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return tem;
    }

    public static boolean writeAccountText(String fileName, AccountList list) {
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(fileName);
            for (int i = 0; i < list.size(); i++) {
                writter.println(list.get(i).toString());
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (writter != null) {
                    writter.close();
                }
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    public static OrderManagementCloseCustomer readOrderManagementText(String fileName, AccountList list) {
        FileReader readFile = null;
        BufferedReader bufferRead = null;
        OrderManagementCloseCustomer tem = new OrderManagementCloseCustomer();
        String s = "";
        try {
            closeCustomers cus = null;
            ListOrder listOrder = null;
            Order newOrder = null;
            FruitsList listFruits = null;
            String orderID = "";
            String saler = "";
            double total = 0;
            String status = "";
            String orderDay = "";
            String completeDay = "";

            readFile = new FileReader(fileName);
            bufferRead = new BufferedReader(readFile);
            while (bufferRead.ready()) {
                if ((s = bufferRead.readLine()) != null) {
                    boolean isCustomerName = (s.charAt(s.length() - 1) == ':');
                    boolean isOrderID = (s.contains("ID"));
                    boolean isSaler = (s.contains("Saler"));
                    boolean isTotal = (s.contains("Total"));
                    boolean isStatus = (s.contains("Status"));
                    boolean isOrderDay = (s.contains("Order Day"));
                    boolean isCompeleteDay = (s.contains("Complete Day"));
                    
                    if (isCustomerName) {
                        if ((cus != null)) {
                            newOrder = new Order(orderID, listFruits, total, saler, status, orderDay, completeDay);
                            listOrder.addOrder(newOrder);
                            tem.put(cus, listOrder);
                        }
                        String customerName = s.substring(0, s.length() - 1);
                        for (int i = 0; i < list.size(); i++) {
                            if (customerName.equals(list.get(i).getLogInID()) && (list.get(i) instanceof closeCustomers)) {
                                cus = (closeCustomers) list.get(i);
                                newOrder = null;
                                listFruits = null;
                                listOrder = null;
                            }
                        }
                        listOrder = new ListOrder(newOrder);
                    } else if (isOrderID) {
                        if (listFruits != null) {
                            newOrder = new Order(orderID, listFruits, total, saler, status, orderDay, completeDay);
                            listOrder.addOrder(newOrder);
                        }
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                orderID = s.substring(i + 1);
                                listFruits = null;
                                newOrder = null;
                            }
                        }
                        listFruits = new FruitsList();
                    } else if (isSaler) {
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                saler = s.substring(i + 1);
                            }
                        }
                    } else if (isTotal) {
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                String temTotal = s.substring(i + 1);
                                total = Double.parseDouble(temTotal);
                            }
                        }
                    }
                    else if(isStatus){
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                status = s.substring(i + 1);
                            }
                        }
                    }
                    else if(isOrderDay){
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                orderDay = s.substring(i + 1);
                            }
                        }
                    }
                    else if(isCompeleteDay){
                        for (int i = 0; i < s.length(); i++) {
                            if (s.charAt(i) == ':') {
                                completeDay = s.substring(i + 1);
                            }
                        }
                    }
                    else {
                        String[] tmp = s.split(",");
                        Fruit newFruit = new Fruit(tmp[0], tmp[1], Double.parseDouble(tmp[2]), Integer.parseInt(tmp[3]), tmp[4]);
                        listFruits.add(newFruit);
                    }
                }
            }
            newOrder = new Order(orderID, listFruits, total, saler, status, orderDay, completeDay);
            listOrder.addOrder(newOrder);
            tem.put(cus, listOrder);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (readFile != null) {
                    readFile.close();
                }
                if (bufferRead != null) {
                    bufferRead.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
//        tem.showAllOrder();
        return tem;
    }

    public static boolean writeOrderManagementText(String fileName, OrderManagementCloseCustomer management) {
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(fileName);
            Set<closeCustomers> listName = management.keySet();
            Iterator<closeCustomers> itCustomer = listName.iterator();
            while (itCustomer.hasNext()) {
                closeCustomers tem = itCustomer.next();
                writter.println(tem.getLoginID() + ":");
                ListOrder list = management.get(tem);

                Set<String> listID = list.keySet();
                Iterator<String> it = listID.iterator();
                while (it.hasNext()) {
                    String id = it.next();
                    writter.println("ID:" + id);
                    Order order = list.get(id);
                    writter.println("Order Day:" + order.getDateOrder());
                    writter.println("Complete Day:" + order.getDateCome());
                    writter.println("Saler:" + order.getSaler());
                    writter.println("Total:" + order.getTotal());
                    writter.println("Status:" + order.getStatus());
                    FruitsList fruitList = order.getListFruitsInOrder();
                    for (int i = 0; i < fruitList.size(); i++) {
                        writter.println(fruitList.get(i).toString());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (writter != null) {
                    writter.close();
                }
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }
}
