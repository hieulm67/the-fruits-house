package Client;

import DAO.AccountList;
import DAO.FileDAO;
import DAO.FruitsList;
import DAO.OrderMangement;
import DAO.ListOrder;
import DAO.OrderManagementCloseCustomer;
import DTO.Account;
import DTO.Admin;
import DTO.Customer;
import DTO.Fruit;
import DTO.Order;
import DTO.Inputter;
import DTO.Menu;
import DTO.Saler;
import DTO.closeCustomers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Min Hiu
 */
public class FruitsShop {

    final static String fileNameFruitList = "listFruits.txt";
    final static String fileNameOrderManagement = "orderManagement.txt";
    final static String fileNameVisitorList = "listVisitors.txt";
    final static String fileNameAccountList = "listAccount.txt";
    final static String fileNameOrderManagementCloseCustomer = "orderManagementCloseCustomer.txt";

    private static int addToCart(FruitsList listFruit, FruitsList cart, int countInCart, int item) {
        int position = cart.checkExistedInList(listFruit.get(item));
        int temQuantity = 0;

        //Nếu có tồn tại chỉ sửa Quantity của Fruit đó
        if (position > -1) {
            int currQuantity = cart.get(position).getQuantity();
            do {
                temQuantity = Inputter.inputQuantity() + currQuantity;
                if (temQuantity > listFruit.get(item).getQuantity()) {
                    System.out.println("Sorry we don't have enough quantity you need.");
                    System.out.println("");
                }
            } while (temQuantity > listFruit.get(item).getQuantity());
            cart.get(position).setQuantity(temQuantity);
        } //Nếu ko tồn tại add vào cart
        else {
            Fruit tem = new Fruit(listFruit.get(item).getId(), listFruit.get(item).getName(), listFruit.get(item).getPrice(), 1, listFruit.get(item).getOrigin());
            cart.add(tem);

            do {
                temQuantity = Inputter.inputQuantity();
                if (temQuantity > listFruit.get(item).getQuantity()) {
                    System.out.println("Sorry we don't have enough quantity you need.");
                    System.out.println("");
                }
            } while (temQuantity > listFruit.get(item).getQuantity());
            cart.get(countInCart).setQuantity(temQuantity);
            countInCart++;
        }
        return countInCart;
    }

    private static void sale(FruitsList listFruit, FruitsList cart) {
        for (int i = 0; i < listFruit.size(); i++) {
            for (int j = 0; j < cart.size(); j++) {
                if (cart.get(j).getId().equals(listFruit.get(i).getId())) {
                    int currentQuantity = listFruit.get(i).getQuantity();
                    listFruit.get(i).setQuantity(currentQuantity - cart.get(j).getQuantity());
                }
            }
        }
    }

    private static void updateCompleteDay(Account tem, OrderManagementCloseCustomer orderManage) {
        String id = Inputter.inputIDOrder();
        Order order = orderManage.searchOrder((closeCustomers) tem, id);
        order.output();
        order.setDateCome(Inputter.inputCompleteDay(order.getDateOrder()));
        System.out.println("Update complete.");
        System.out.println("");
        order.output();
    }

    private static void updateCompleteDayVisitor(Customer tem, OrderMangement orderManage) {
        String id = Inputter.inputIDOrder();
        Order order = orderManage.searchOrder(tem, id);
        order.output();
        order.setDateCome(Inputter.inputCompleteDay(order.getDateOrder()));
        System.out.println("Update complete.");
        System.out.println("");
        order.output();
    }

    public static void termAndRight() {
        System.out.println("--- Term and Right with Close Customer ---");
        System.out.println("");
        System.out.println("Nothing special more than when you coming our's The Fruit House.");
        System.out.println("Not only choosing for the best quality fruit but also earn point to get great privileges.");
        System.out.println("Easy to up rank. Get attractive benefits");
        System.out.println("");
        System.out.println("Close Customer System: ");
        System.out.println("");
        System.out.println("-How to earn point?");
        System.out.println("With 1$ you get 1 point.");
        System.out.println("");
        System.out.println("-Rank Member: ");
        System.out.println("New Customer - Silver - Gold - Platinum - Diamond");
        System.out.println("*100 points to reach Silver Member.");
        System.out.println("*200 points to reach Gold Member.");
        System.out.println("*500 points to reach Platinum Member.");
        System.out.println("*3000 points to reach Diamond Member.");
        System.out.println("");
        System.out.println("-Right of rank member: ");
        System.out.println("*Silver member have 10% discount on total bill.");
        System.out.println("*Gold member have 20% discount on total bill.");
        System.out.println("*Platinum member have 30% discount on total bill.");
        System.out.println("*Diamond member have 40% discount on total bill.");
        System.out.println("*Discount 10% of our store can use with all rank member.");
        System.out.println("*Discount 30% of our store can use with Gold and Silver Member.");
        System.out.println("*Dimond and Platinum have larger chance getting our special discount.");
        System.out.println("");
        System.out.println("What are you waiting for? Join us now. :D");
        System.out.println("");
    }

    public static void updateEmployee(Account currentAccount, AccountList accountList) {
        int choiceAdmin = 0;
        int limitOptionAdmin = 0;
        do {
            Menu menuUpdateAdmin = new Menu();
            limitOptionAdmin = menuUpdateAdmin.setMenuUpdateEmployee();// Set menu nho quan ly thong tin ca nhan va lay ra gioi han
            choiceAdmin = menuUpdateAdmin.getUserChoice();// Lay ra lua chon menu nho

            switch (choiceAdmin) {
                case 1:
                    try {
                        if (accountList.add(new Admin((Saler) currentAccount))) {
                            System.out.println("Promote Success.");
                            accountList.remove(currentAccount);
                        } else {
                            System.out.println("Promote Fail.");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    System.out.println("Old password: " + currentAccount.getPassword());
                    currentAccount.setPassword(Inputter.signInPassword(false));
                    break;
                case 3:
                    System.out.println("Old name: " + currentAccount.getName());
                    currentAccount.setName(Inputter.inputName());
                    break;
                case 4:
                    System.out.println("Old phone: " + currentAccount.getPhone());
                    currentAccount.setPhone(Inputter.inputPhone());
                    break;
                case 5:
                    System.out.println("Old birth date: " + ((Admin) currentAccount).getBirthDate());
                    ((Admin) currentAccount).setBirthDate(Inputter.inputBirthDate());
                    break;
                case 6:
                    System.out.println("Old address: " + ((Admin) currentAccount).getAddress());
                    ((Admin) currentAccount).setAddress(Inputter.inputAddress());
                    break;
                case 7:
                    System.out.println("Old email: " + ((Admin) currentAccount).getEmail());
                    ((Admin) currentAccount).setEmail(Inputter.inputEmail());
                    break;
                default:
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;
            }
        } while (choiceAdmin != limitOptionAdmin - 1);
    }

    public static void updateOrderCustomerStatus(Account tem, OrderManagementCloseCustomer orderManage) {
        String id = Inputter.inputIDOrder();
        Order order = orderManage.searchOrder((closeCustomers) tem, id);
        order.output();
        order.setStatus(Inputter.inputStatus());
        System.out.println("Update complete.");
        System.out.println("");
        order.output();
    }

    public static void updateOrderVisitorStatus(Customer tem, OrderMangement orderManage) {
        String id = Inputter.inputIDOrder();
        Order order = orderManage.searchOrder(tem, id);
        order.output();
        order.setStatus(Inputter.inputStatus());
        System.out.println("Update complete.");
        System.out.println("");
        order.output();
    }

    public static void employeeMenu(int limitOptionAdminAdmin, int choiceAdminAdmin, AccountList accountList) {
        do {
            Menu menuEmployee = new Menu();
            limitOptionAdminAdmin = menuEmployee.setMenuEmployeeManagement();// Set menu nho quan ly nhan vien va lay raa gioi han chon
            choiceAdminAdmin = menuEmployee.getUserChoice();// Lay ra lua chon menu nho

            switch (choiceAdminAdmin) {
                case 1:
                    boolean again = true;
                    do {
                        String searchLoginID = Inputter.signInID();
                        Account saler = accountList.searchByLoginID(searchLoginID);
                        if (saler != null) {
                            if (saler instanceof Saler) {
                                updateEmployee(saler, accountList);
                            } else {
                                System.out.println("This account is not Saler!");
                            }
                        } else {
                            System.out.println("This account is not existed!");
                        }
                        again = Inputter.inputContinueOption();
                        System.out.println("");
                    } while (again == true);
                    break;
                case 2:
                    accountList.addSaler();
                    break;
                case 3:
                    accountList.removeSaler();
                    break;
                case 4:
                    accountList.showSalers();
                    break;
                case 5:
                    String searchLoginID = Inputter.signInID();
                    Account saler = accountList.searchByLoginID(searchLoginID);
                    saler.output();
                    break;
                default:
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;
            }
        } while (choiceAdminAdmin != limitOptionAdminAdmin - 1);
    }

    public static void customerMenu(int limitOptionAdminAdmin, int choiceAdminAdmin, AccountList accountList) {
        do {
            Menu menuCustomerManage = new Menu();
            limitOptionAdminAdmin = menuCustomerManage.setMenuCustomerAdminAdmin();
            choiceAdminAdmin = menuCustomerManage.getUserChoice();

            switch (choiceAdminAdmin) {
                case 1:
                    accountList.showCustomers();
                    break;
                case 2:
                    accountList.showInfoCustomer();
                    break;
                case 3:
                    closeCustomers tem = accountList.searchCustomerByLoginID();
                    if (tem != null) {
                        tem.setPoint(Inputter.inputPoint());
                        tem.setRank(accountList.getRank(tem));
                    } else {
                        System.out.println("This account is not Close Customer.");
                        System.out.println("");
                    }
                    break;
                default:
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;
            }
        } while (choiceAdminAdmin != limitOptionAdminAdmin - 1);
    }

    public static void personalInfoAdmin(int limitOptionAdmin, int choiceAdmin, Account currentAccount, AccountList accountList) {
        do {
            Menu menuUpdateAdmin = new Menu();
            limitOptionAdmin = menuUpdateAdmin.setMenuUpdateInfo();// Set menu nho quan ly thong tin ca nhan va lay ra gioi han
            choiceAdmin = menuUpdateAdmin.getUserChoice();// Lay ra lua chon menu nho

            switch (choiceAdmin) {
                case 1:
                    System.out.println("Your old password: " + currentAccount.getPassword());
                    currentAccount.setPassword(Inputter.signInPassword(false));
                    break;
                case 2:
                    System.out.println("Your old name: " + currentAccount.getName());
                    currentAccount.setName(Inputter.inputName());
                    break;
                case 3:
                    System.out.println("Your old phone: " + currentAccount.getPhone());
                    currentAccount.setPhone(Inputter.inputPhone());
                    break;
                case 4:
                    System.out.println("Your old birth date: " + ((Admin) currentAccount).getBirthDate());
                    ((Admin) currentAccount).setBirthDate(Inputter.inputBirthDate());
                    break;
                case 5:
                    System.out.println("Your old address: " + ((Admin) currentAccount).getAddress());
                    ((Admin) currentAccount).setAddress(Inputter.inputAddress());
                    break;
                case 6:
                    System.out.println("Your old email: " + ((Admin) currentAccount).getEmail());
                    ((Admin) currentAccount).setEmail(Inputter.inputEmail());
                    break;
                default:
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;
            }
        } while (choiceAdmin != limitOptionAdmin - 1);
    }

    public static void personalInfoEmployee(int limitOptionSaler, int choiceSaler, Account currentAccount, AccountList accountList) {
        do {
            Menu menuUpdateSaler = new Menu();
            limitOptionSaler = menuUpdateSaler.setMenuUpdateInfo();// Set menu nho quan ly thong tin ca nhan va lay ra gioi han
            choiceSaler = menuUpdateSaler.getUserChoice();// Lay ra lua chon menu nho

            switch (choiceSaler) {
                case 1:
                    System.out.println("Your old password: " + currentAccount.getPassword());
                    currentAccount.setPassword(Inputter.signInPassword(false));
                    break;
                case 2:
                    System.out.println("Your old name: " + currentAccount.getName());
                    currentAccount.setName(Inputter.inputName());
                    break;
                case 3:
                    System.out.println("Your old phone: " + currentAccount.getPhone());
                    currentAccount.setPhone(Inputter.inputPhone());
                    break;
                case 4:
                    System.out.println("Your old birth date: " + ((Saler) currentAccount).getBirthDate());
                    ((Admin) currentAccount).setBirthDate(Inputter.inputBirthDate());
                    break;
                case 5:
                    System.out.println("Your old address: " + ((Saler) currentAccount).getAddress());
                    ((Admin) currentAccount).setAddress(Inputter.inputAddress());
                    break;
                case 6:
                    System.out.println("Your old email: " + ((Saler) currentAccount).getEmail());
                    ((Admin) currentAccount).setEmail(Inputter.inputEmail());
                    break;
                default:
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;
            }
        } while (choiceSaler != limitOptionSaler - 1);
    }

    public static void giveDiscount(int limitOptionAdmin, int choiceAdmin, AccountList accountList) {
        do {
            Menu menuDiscount = new Menu();
            limitOptionAdmin = menuDiscount.setMenuVoucher();
            choiceAdmin = menuDiscount.getUserChoice();// Lay ra lua chon menu nho

            switch (choiceAdmin) {
                case 1:
                    accountList.giveVoucher("Saler and Close Customer", "Discount 100%");
                    break;
                case 2:
                    accountList.giveVoucher("Saler and Close Customer", "Discount 70%");
                    break;
                case 3:
                    accountList.giveVoucher("Saler and Close Customer", "Discount 50%");
                    break;
                case 4:
                    accountList.giveVoucher("Saler and Close Customer", "Discount 30%");
                    break;
                case 5:
                    accountList.giveVoucher("Saler and Close Customer", "Discount 10%");
                    break;
                default:
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;
            }
        } while (choiceAdmin != limitOptionAdmin - 1);
    }

    public static void giveDiscountToAdmin(int limitOptionAdminAdmin, int choiceAdminAdmin, AccountList accountList) {
        do {
            Menu menuDiscount = new Menu();
            limitOptionAdminAdmin = menuDiscount.setMenuVoucher();// Set menu quan ly voucher
            choiceAdminAdmin = menuDiscount.getUserChoice();// Lay ra lua chon menu nho

            switch (choiceAdminAdmin) {
                case 1:
                    accountList.giveVoucher("Admin", "Discount 100%");
                    break;
                case 2:
                    accountList.giveVoucher("Admin", "Discount 70%");
                    break;
                case 3:
                    accountList.giveVoucher("Admin", "Discount 50%");
                    break;
                case 4:
                    accountList.giveVoucher("Admin", "Discount 30%");
                    break;
                case 5:
                    accountList.giveVoucher("Admin", "Discount 10%");
                    break;
                default:
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;
            }
        } while (choiceAdminAdmin != limitOptionAdminAdmin - 1);
    }

    public static void orderMenu(int limitOptionAdminAdmin, int choiceAdminAdmin, AccountList accountList, OrderMangement orderManage, OrderManagementCloseCustomer orderManageCloseCustomer, ArrayList<Customer> visitorList) {
        do {
            Menu menuUpdateOrder = new Menu();
            limitOptionAdminAdmin = menuUpdateOrder.setMenuUpdateOrder();
            choiceAdminAdmin = menuUpdateOrder.getUserChoice();// Lay ra lua chon menu nho

            switch (choiceAdminAdmin) {
                case 1:
                    boolean again = true;
                    do {
                        String searchLoginID = Inputter.signInID();
                        Account saler = accountList.searchByLoginID(searchLoginID);
                        if (saler != null) {
                            if (saler instanceof closeCustomers) {
                                updateOrderCustomerStatus(saler, orderManageCloseCustomer);
                            } else {
                                System.out.println("This account is not Customer!");
                            }
                        } else {
                            System.out.println("This account is not existed!");
                        }
                        again = Inputter.inputContinueOption();
                        System.out.println("");
                    } while (again == true);
                    break;
                case 2:
                    again = true;
                    do {
                        Customer tem = null;
                        String name = Inputter.inputName();
                        String phone = Inputter.inputPhone();

                        for (int i = 0; i < visitorList.size(); i++) {
                            if (visitorList.get(i).getName().equals(name) && visitorList.get(i).getPhone().equals(phone)) {
                                tem = visitorList.get(i);
                            }
                        }
                        if (tem != null) {
                            updateOrderVisitorStatus(tem, orderManage);
                        } else {
                            System.out.println("This account is not existed!");
                        }
                        again = Inputter.inputContinueOption();
                        System.out.println("");
                    } while (again == true);
                    break;
                case 3:
                    again = true;
                    do {
                        String searchLoginID = Inputter.signInID();
                        Account saler = accountList.searchByLoginID(searchLoginID);
                        if (saler != null) {
                            if (saler instanceof closeCustomers) {
                                updateCompleteDay(saler, orderManageCloseCustomer);
                            } else {
                                System.out.println("This account is not Customer!");
                            }
                        } else {
                            System.out.println("This account is not existed!");
                        }
                        again = Inputter.inputContinueOption();
                        System.out.println("");
                    } while (again == true);
                    break;
                case 4:
                    again = true;
                    do {
                        Customer tem = null;
                        String name = Inputter.inputName();
                        String phone = Inputter.inputPhone();

                        for (int i = 0; i < visitorList.size(); i++) {
                            if (visitorList.get(i).getName().equals(name) && visitorList.get(i).getPhone().equals(phone)) {
                                tem = visitorList.get(i);
                            }
                        }
                        if (tem != null) {
                            updateCompleteDayVisitor(tem, orderManage);
                        } else {
                            System.out.println("This account is not existed!");
                        }
                        again = Inputter.inputContinueOption();
                        System.out.println("");
                    } while (again == true);
                    break;
                case 5:
                    searchCustomerOrderMenu(orderManageCloseCustomer, accountList);
                    break;
                case 6:
                    searchVisitorOrderMenu(visitorList, orderManage);
                    break;
                default:
                    break;
            }
        } while (choiceAdminAdmin != limitOptionAdminAdmin - 1);
    }

    public static void searchCustomerOrderMenu(OrderManagementCloseCustomer orderManageCloseCustomer, AccountList accountList) {
        int choiceSearch = 0;
        int limitMenuSearch = 0;
        do {
            Menu menuImport = new Menu();
            limitMenuSearch = menuImport.setSearchCustomerMenu();
            choiceSearch = menuImport.getUserChoice();

            switch (choiceSearch) {
                case 1:
                    String searchLoginID = Inputter.signInID();
                    Account saler = accountList.searchByLoginID(searchLoginID);
                    if (saler != null) {
                        if (saler instanceof closeCustomers) {
                            String idOrder = Inputter.inputIDOrder();
                            Order order = orderManageCloseCustomer.searchOrder((closeCustomers) saler, idOrder);
                            order.output();
                        } else {
                            System.out.println("This account is not Customer!");
                        }
                    } else {
                        System.out.println("This account is not existed!");
                    }
                    System.out.println("");
                    break;
                case 2:
                    searchLoginID = Inputter.signInID();
                    saler = accountList.searchByLoginID(searchLoginID);
                    if (saler != null) {
                        if (saler instanceof closeCustomers) {
                            String orderDay = Inputter.inputOrderDay();
                            orderManageCloseCustomer.searchOrderByDayAll1Cus((closeCustomers) saler, orderDay);
                        } else {
                            System.out.println("This account is not Customer!");
                        }
                    } else {
                        System.out.println("This account is not existed!");
                    }
                    System.out.println("");
                    break;
                case 3:
                    String orderDay = Inputter.inputOrderDay();
                    orderManageCloseCustomer.searchOrderByDayAll(orderDay);
                    break;
                default:
                    break;
            }
        } while (choiceSearch != limitMenuSearch - 1);
    }

    public static void searchVisitorOrderMenu(ArrayList<Customer> visitorList, OrderMangement orderManage) {
        int choiceSearch = 0;
        int limitMenuSearch = 0;
        do {
            Menu menuImport = new Menu();
            limitMenuSearch = menuImport.setSearchVisitorMenu();
            choiceSearch = menuImport.getUserChoice();

            switch (choiceSearch) {
                case 1:
                    Customer tem = null;
                    String name = Inputter.inputName();
                    String phone = Inputter.inputPhone();

                    for (int i = 0; i < visitorList.size(); i++) {
                        if (visitorList.get(i).getName().equals(name) && visitorList.get(i).getPhone().equals(phone)) {
                            tem = visitorList.get(i);
                        }
                    }
                    if (tem != null) {
                        String idOrder = Inputter.inputIDOrder();
                        Order order = orderManage.searchOrder(tem, idOrder);
                        order.output();
                    } else {
                        System.out.println("This account is not existed!");
                    }
                    System.out.println("");
                    break;
                case 2:
                    tem = null;
                    name = Inputter.inputName();
                    phone = Inputter.inputPhone();

                    for (int i = 0; i < visitorList.size(); i++) {
                        if (visitorList.get(i).getName().equals(name) && visitorList.get(i).getPhone().equals(phone)) {
                            tem = visitorList.get(i);
                        }
                    }
                    if (tem != null) {
                        String orderDay = Inputter.inputOrderDay();
                        orderManage.searchOrderByDayAll1Cus(tem, orderDay);
                    } else {
                        System.out.println("This account is not existed!");
                    }
                    System.out.println("");
                    break;
                case 3:
                    String orderDay = Inputter.inputOrderDay();
                    orderManage.searchOrderByDayAll(orderDay);
                default:
                    break;
            }
        } while (choiceSearch != limitMenuSearch - 1);
    }

    public static void fruitStorageMenu(int limitOptionAdmin, int choiceAdmin, FruitsList listFruit, OrderManagementCloseCustomer orderManageCloseCustomer, OrderMangement orderManage) {
        do {
            Menu menuFruit = new Menu();
            limitOptionAdmin = menuFruit.setMenuFruitManagement();// Set menu nho quan ly trai cay va lay ra gioi han
            choiceAdmin = menuFruit.getUserChoice();// Lay ra lua chon menu nho

            switch (choiceAdmin) {
                case 1:
                    importProductMenu(listFruit);
                    break;
                case 2:
                    updateQuantityProductMenu(listFruit);
                    break;
                case 3:
                    listFruit.showListFruits();
                    listFruit.addFruitToList();
                    break;
                case 4:
                    listFruit.showListFruits();
                    break;
                case 5:
                    listFruit.updateListFruit(false);
                    break;
                case 6:
                    orderManageCloseCustomer.showAllOrder();
                    break;
                case 7:
                    orderManage.showAllOrder();
                    break;
                default:
                    FileDAO.writeFruitListText(fileNameFruitList, listFruit);
                    break;
            }
        } while (choiceAdmin != limitOptionAdmin - 1);
    }

    public static void importProductMenu(FruitsList listFruit) {
        int choiceImport = 0;
        int limitMenuImport = 0;
        do {
            Menu menuImport = new Menu();
            limitMenuImport = menuImport.setMenuImport();
            choiceImport = menuImport.getUserChoice();

            switch (choiceImport) {
                case 1:
                    listFruit.importProduct();
                    break;
                case 2:
                    listFruit.importMultiProduct();
                    break;
                default:
                    break;
            }
        } while (choiceImport != limitMenuImport - 1);
    }

    public static void updateQuantityProductMenu(FruitsList listFruit) {
        int choiceUpdate = 0;
        int limitMenuUpdate = 0;
        do {
            Menu menuImport = new Menu();
            limitMenuUpdate = menuImport.setMenuUpdateQuantityProduct();
            choiceUpdate = menuImport.getUserChoice();

            switch (choiceUpdate) {
                case 1:
                    listFruit.updateQuantityProduct();
                    break;
                case 2:
                    listFruit.updateQuantityMultiProduct();
                    break;
                default:
                    break;
            }
        } while (choiceUpdate != limitMenuUpdate - 1);
    }

    public static void menuAdminAdmin(int limitMenuLogin, int choiceMenuLogin, Menu menuAccount, AccountList accountList, OrderMangement orderManage, OrderManagementCloseCustomer orderManageCloseCustomer, ArrayList<Customer> visitorList) {
        limitMenuLogin = menuAccount.setAdminAdmin();// Set menu dang admin tong quat

        do {
            choiceMenuLogin = menuAccount.getUserChoice();// Lay lua chon trong menu admin tong quat

            int choiceAdminAdmin = 0;// Lua chon menu nho trong option
            int limitOptionAdminAdmin = 0;// Gioi han lua chon nho
            switch (choiceMenuLogin) {
                case 1:
                    accountList.addAdmin();
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;

                case 2:
                    accountList.removeAdmin();
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;

                case 3:
                    employeeMenu(limitOptionAdminAdmin, choiceAdminAdmin, accountList);
                    break;
                case 4:
                    customerMenu(limitOptionAdminAdmin, choiceAdminAdmin, accountList);
                    break;

                case 5:
                    giveDiscountToAdmin(limitOptionAdminAdmin, choiceAdminAdmin, accountList);
                    break;
                case 6:
                    orderMenu(limitOptionAdminAdmin, choiceAdminAdmin, accountList, orderManage, orderManageCloseCustomer, visitorList);
                    break;

                default:
                    break;
            }
        } while (choiceMenuLogin != limitMenuLogin - 1);
    }

    public static void menuAdmin(int limitMenuLogin, int choiceMenuLogin, Menu menuAccount, AccountList accountList, FruitsList listFruit, OrderMangement orderManage, OrderManagementCloseCustomer orderManageCloseCustomer, Account currentAccount, ArrayList<Customer> visitorList) {
        limitMenuLogin = menuAccount.setMenuAdmin();// Set menu dang admin

        do {
            choiceMenuLogin = menuAccount.getUserChoice();// Lay ra lua chon trong menu admin

            int choiceAdmin = 0;// Lua chon menu nho trong option
            int limitOptionAdmin = 0;// Gioi han lua chon nho

            switch (choiceMenuLogin) {
                case 1: // Option quan ly trai cay
                    fruitStorageMenu(limitOptionAdmin, choiceAdmin, listFruit, orderManageCloseCustomer, orderManage);
                    break;

                case 2: // Option quan ly nhan vien
                    employeeMenu(limitOptionAdmin, choiceAdmin, accountList);
                    break;

                case 3:
                    do {
                        Menu menuCustomerManage = new Menu();
                        limitOptionAdmin = menuCustomerManage.setMenuCustomerManagement();
                        choiceAdmin = menuCustomerManage.getUserChoice();

                        switch (choiceAdmin) {
                            case 1:
                                accountList.showCustomers();
                                break;
                            case 2:
                                accountList.showInfoCustomer();
                                break;
                            default:
                                break;
                        }
                    } while (choiceAdmin != limitOptionAdmin - 1);
                    break;

                case 4: // Option quan ly thong tin ca nhan
                    personalInfoAdmin(limitOptionAdmin, choiceAdmin, currentAccount, accountList);
                    break;

                case 5://Option tang voucher
                    giveDiscount(limitOptionAdmin, choiceAdmin, accountList);
                    break;
                case 6:
                    orderMenu(limitOptionAdmin, choiceAdmin, accountList, orderManage, orderManageCloseCustomer, visitorList);
                    break;

                default:
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    FileDAO.writeOrderManagementText(fileNameOrderManagementCloseCustomer, orderManageCloseCustomer);
                    FileDAO.writeOrderManagementText(fileNameOrderManagement, orderManage);
                    break;
            }
        } while (choiceMenuLogin != limitMenuLogin - 1);
    }

    public static void menuCustomer(int limitMenuLogin, int choiceMenuLogin, Menu menuAccount, AccountList accountList, FruitsList listFruit, OrderMangement orderManage, OrderManagementCloseCustomer orderManageCloseCustomer, Account currentAccount) {
        limitMenuLogin = menuAccount.setMenuCustomer(); //Account customer

        do {
            choiceMenuLogin = menuAccount.getUserChoice();

            int choiceCustomer = 0;
            int limitCustomer = 0;
            double total = 0;
            switch (choiceMenuLogin) {
                case 1:
                    FruitsList cart = new FruitsList();
                    int countInCart = 0;
                    String voucher = "";
                    do {
                        Menu menuShopping = new Menu();
                        limitCustomer = menuShopping.setMenuShopping();
                        choiceCustomer = menuShopping.getUserChoice();

                        switch (choiceCustomer) {
                            case 1:
                                boolean exit = false;
                                if (!listFruit.isEmpty()) {
                                    do {
                                        listFruit.showListFruits();

                                        //Chọn ra item cần add vào cart
                                        int item = -1;
                                        do {
                                            System.out.println("Press 0 to exit.");
                                            item = Inputter.getItem();
                                        } while (item >= listFruit.size());

                                        //Kiểm tra cart đã có Fruit chọn chưa , lấy ra vị trí trong cart
                                        if (item > -1) {
                                            countInCart = addToCart(listFruit, cart, countInCart, item);
                                            total = cart.getTotalPrice((closeCustomers) currentAccount, voucher);
                                            //Biến flag khi cần thoát quá trình add vào cart    
                                        } else {
                                            exit = true;
                                        }
                                    } while (exit == false);
                                } else {
                                    System.out.println("The list is empty!");
                                }
                                break;
                            case 2:
                                cart.addedToCart();
                                String currentRank = accountList.getRank((closeCustomers) currentAccount);
                                if (!currentRank.equals("New Member")) {
                                    System.out.println("Your Rank: " + currentRank + "| Discount: " + accountList.getDiscount(currentRank));
                                }
                                System.out.println("Current voucher: " + voucher);
                                System.out.println("Total order: " + total);
                                System.out.println("");
                                break;
                            case 3:
                                cart.updateListFruit(true);
                                break;
                            case 4:
                                if (currentAccount != null) {
                                    voucher = ((closeCustomers) currentAccount).getVoucher().getVoucher();
                                    total = cart.getTotalPrice((closeCustomers) currentAccount, voucher);
                                }
                                break;
                            default:
                                ListOrder tem = orderManageCloseCustomer.searchCustomer((closeCustomers) currentAccount);
                                int temID = 0;
                                Order shopping = null;
                                if (!cart.isEmpty()) {
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                    Date orderDate = new Date();
                                    Date completeDate = new Date();
                                    completeDate.setDate(completeDate.getDate() + 3);
                                    String orderDay = formatter.format(orderDate);
                                    String completeDay = formatter.format(completeDate);
                                    if (tem != null) {
                                        System.out.println(tem.getCountID());

                                        shopping = new Order(tem.getCountID() + "", cart, total, "Saler full time", "In process.", orderDay, completeDay);
                                        tem.addOrder(shopping);
                                        double point = ((closeCustomers) currentAccount).getPoint();
                                        ((closeCustomers) currentAccount).setPoint(point + total);
                                        ((closeCustomers) currentAccount).setRank(accountList.getRank(((closeCustomers) currentAccount)));
                                    } else {
                                        shopping = new Order(temID + "", cart, total, "Saler full time", "In process.", orderDay, completeDay);
                                        orderManageCloseCustomer.addCustomerOrder((closeCustomers) currentAccount, shopping);
                                        ((closeCustomers) currentAccount).setPoint(((closeCustomers) currentAccount).getPoint() + total);
                                        ((closeCustomers) currentAccount).setRank(accountList.getRank(((closeCustomers) currentAccount)));
                                    }
                                    if (!voucher.equals("")) {
                                        ((closeCustomers) currentAccount).getVoucher().remove(voucher);
                                    }
                                    sale(listFruit, cart);
                                    FileDAO.writeOrderManagementText(fileNameOrderManagementCloseCustomer, orderManageCloseCustomer);
                                }
                                break;
                        }
                    } while (choiceCustomer != limitCustomer - 1);
                    break;
                case 2:
                    ((closeCustomers) currentAccount).getVoucher().showVouchers();
                    break;
                case 3:
                    do {
                        Menu menuHelpCenter = new Menu();
                        limitCustomer = menuHelpCenter.setMenuHelpCenter();
                        choiceCustomer = menuHelpCenter.getUserChoice();

                        int choiceHelp = 0;
                        int limitHelp = 0;
                        switch (choiceCustomer) {
                            case 1:
                                do {
                                    Menu menuUpdateCustomer = new Menu();
                                    limitHelp = menuUpdateCustomer.setMenuUpdateInfo();// Set menu nho quan ly thong tin ca nhan va lay ra gioi han
                                    choiceHelp = menuUpdateCustomer.getUserChoice();// Lay ra lua chon menu nho

                                    switch (choiceHelp) {
                                        case 1:
                                            System.out.println("Your old password: " + currentAccount.getPassword());
                                            currentAccount.setPassword(Inputter.signInPassword(false));
                                            break;
                                        case 2:
                                            System.out.println("Your old name: " + currentAccount.getName());
                                            currentAccount.setName(Inputter.inputName());
                                            break;
                                        case 3:
                                            System.out.println("Your old phone: " + currentAccount.getPhone());
                                            currentAccount.setPhone(Inputter.inputPhone());
                                            break;
                                        case 4:
                                            System.out.println("Your old birth date: " + ((closeCustomers) currentAccount).getBirthDate());
                                            ((closeCustomers) currentAccount).setBirthDate(Inputter.inputBirthDate());
                                            break;
                                        case 5:
                                            System.out.println("Your old address: " + ((closeCustomers) currentAccount).getAddress());
                                            ((closeCustomers) currentAccount).setAddress(Inputter.inputAddress());
                                            break;
                                        case 6:
                                            System.out.println("Your old email: " + ((closeCustomers) currentAccount).getEmail());
                                            ((closeCustomers) currentAccount).setEmail(Inputter.inputEmail());
                                            break;
                                        default:
                                            FileDAO.writeAccountText(fileNameAccountList, accountList);
                                            break;
                                    }
                                } while (choiceHelp != limitHelp - 1);
                                break;
                            case 2:
                                String rank = accountList.getRank((closeCustomers) currentAccount);
                                System.out.println("Your point: " + ((closeCustomers) currentAccount).getPoint() + "| Rank: " + rank);
                                accountList.viewRankProcess(rank, currentAccount);
                                break;
                            case 3:
                                ListOrder listOrder = orderManageCloseCustomer.searchCustomer((closeCustomers) currentAccount);
                                listOrder.showListOrder();
                                break;
                            default:
                                break;
                        }
                    } while (choiceCustomer != limitCustomer - 1);
                    break;
                default:
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;
            }
        } while (choiceMenuLogin != limitMenuLogin - 1);
    }

    public static void main(String[] args) {
        FruitsList listFruit = FileDAO.readFruitListText(fileNameFruitList);
        ArrayList<Customer> visitorList = FileDAO.readVisitorText(fileNameVisitorList);
        OrderMangement orderManage = FileDAO.readOrderManagementText(fileNameOrderManagement, visitorList);
        AccountList accountList = FileDAO.readAccountText(fileNameAccountList);
        OrderManagementCloseCustomer orderManageCloseCustomer = FileDAO.readOrderManagementText(fileNameOrderManagementCloseCustomer, accountList);

        int service = 0;
        do {
            Menu menu = new Menu();
            service = menu.getMenu();
            switch (service) {
                case 1:// Login service
                    Account currentAccount = null;
                    currentAccount = accountList.login();

                    if (currentAccount != null) {
                        int currentType = accountList.typeAccount(currentAccount); // Lay ra dang account
                        int choiceMenuLogin = 0;
                        int limitMenuLogin = 0;

                        Menu menuAccount = new Menu();
                        switch (currentType) {

                            case 0: //Dang admin tong quat
                                menuAdminAdmin(limitMenuLogin, choiceMenuLogin, menuAccount, accountList, orderManage, orderManageCloseCustomer, visitorList);
                                break;

                            case 1: // Dang admin
                                menuAdmin(limitMenuLogin, choiceMenuLogin, menuAccount, accountList, listFruit, orderManage, orderManageCloseCustomer, currentAccount, visitorList);
                                break;

                            case 2:
                                limitMenuLogin = menuAccount.setMenuSaler(); //Account Saler

                                int limitOptionSaler = 0;
                                int choiceSaler = 0;
                                do {
                                    choiceMenuLogin = menuAccount.getUserChoice();

                                    switch (choiceMenuLogin) {
                                        case 1:
                                            orderManageCloseCustomer.showAllOrder();
                                            break;
                                        case 2:
                                            orderManage.showAllOrder();
                                            break;
                                        case 3:
                                            accountList.showInfoCustomer();
                                            break;
                                        case 4:
                                            personalInfoEmployee(limitOptionSaler, choiceSaler, currentAccount, accountList);
                                            break;
                                        case 5:
                                            importProductMenu(listFruit);
                                            break;
                                        case 6:
                                            updateQuantityProductMenu(listFruit);
                                            break;
                                        case 7:
                                            orderMenu(limitOptionSaler, choiceSaler, accountList, orderManage, orderManageCloseCustomer, visitorList);
                                        default:
                                            break;
                                    }
                                } while (choiceMenuLogin != limitMenuLogin - 1);
                                break;

                            case 3:
                                menuCustomer(limitMenuLogin, choiceMenuLogin, menuAccount, accountList, listFruit, orderManage, orderManageCloseCustomer, currentAccount);
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 2:
                    listFruit.showListFruits();
                    break;
                case 3:

                    int choiceMenuVisitor = 0;
                    int limitMenuVisitor = 0;
                    String name = "";
                    String phone = "";

                    do {
                        Menu menuVisitor = new Menu();
                        limitMenuVisitor = menuVisitor.setMenuVisitor();
                        choiceMenuVisitor = menuVisitor.getUserChoice();

                        switch (choiceMenuVisitor) {
                            case 1:
                                FruitsList cart = new FruitsList();
                                Order buying = null;
                                int choiceVisitorShopping = 0;
                                int limitVisitorShopping = 0;
                                int countInCart = 0;
                                do {
                                    Menu menuVisitorShopping = new Menu();
                                    limitVisitorShopping = menuVisitorShopping.setMenuVisitorShopping();
                                    choiceVisitorShopping = menuVisitorShopping.getUserChoice();

                                    switch (choiceVisitorShopping) {
                                        case 1:
//                                            if (visit == null) {
                                            boolean exit = false;
                                            if (!listFruit.isEmpty()) {
                                                do {
                                                    listFruit.showListFruits();

                                                    //Chọn ra item cần add vào cart
                                                    int item = -1;
                                                    do {
                                                        System.out.println("Press 0 to exit.");
                                                        item = Inputter.getItem();
                                                    } while (item >= listFruit.size());

                                                    //Kiểm tra cart đã có Fruit chọn chưa , lấy ra vị trí trong cart
                                                    if (item > -1) {
                                                        countInCart = addToCart(listFruit, cart, countInCart, item);

                                                        //Biến flag khi cần thoát quá trình add vào cart    
                                                    } else {
                                                        exit = true;
                                                    }
                                                } while (exit == false);
                                            } else {
                                                System.out.println("The list is empty!");
                                            }
                                            break;
                                        case 2:
                                            cart.addedToCart();
                                            System.out.println("Total: " + cart.getTotalPrice());
                                            System.out.println("");
                                            break;
                                        case 3:
                                            cart.updateListFruit(true);
                                            break;
                                        default:
                                            if (!cart.isEmpty()) {
                                                name = Inputter.inputName();
                                                phone = Inputter.inputPhone();
                                                Customer visit = null;
                                                for (int i = 0; i < visitorList.size(); i++) {
                                                    if (visitorList.get(i).getName().equals(name) && visitorList.get(i).getPhone().equals(phone)) {
                                                        visit = visitorList.get(i);
                                                    }
                                                }

                                                if (visit == null) {
                                                    visit = new Customer(name, phone, 0);
                                                    visitorList.add(visit);
                                                } else {
                                                    System.out.println("");
                                                    System.out.println("You had visited our store " + visit.getCountTime() + " times.");
                                                    System.out.println("Thank you for choosing us. :D");
                                                    System.out.println("We recommend you to sign up the account to get more benefits.");
                                                    System.out.println("Please consider our invitation.");
                                                    System.out.println("");
                                                }
                                                if (visit != null) {
                                                    //Lấy ra listOrder của buyer
                                                    ListOrder tem = orderManage.searchCustomer(visit);
                                                    int temID = 0;

                                                    //Nếu buyer đã tồn tại chỉ cần thêm order
                                                    if (!cart.isEmpty()) {
                                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                                        Date orderDate = new Date();
                                                        Date completeDate = new Date();
                                                        completeDate.setDate(completeDate.getDate() + 3);
                                                        String orderDay = formatter.format(orderDate);
                                                        String completeDay = formatter.format(completeDate);
                                                        if (tem != null) {
                                                            temID = tem.getCountID();
                                                            buying = new Order(temID + "", cart, cart.getTotalPrice(), "Saler full time", "In process.", orderDay, completeDay);
                                                            tem.addOrder(buying);
                                                            orderManage.addCountTime(visit);
                                                        } //Nếu buyer ko tồn tại tạo buyer cùng vs listOrder của buyer
                                                        else {
                                                            buying = new Order(temID + "", cart, cart.getTotalPrice(), "Saler full time", "In process.", orderDay, completeDay);
                                                            orderManage.addCustomerOrder(visit, buying);
                                                        }
                                                        sale(listFruit, cart);
                                                        FileDAO.writeOrderManagementText(fileNameOrderManagement, orderManage);
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                } while (choiceVisitorShopping != limitVisitorShopping - 1);
                                break;
                            case 2:
                                termAndRight();
                                break;
                            case 3:
                                if (!name.equals("") && !phone.equals("")) {
                                    accountList.signUp(name, phone);
                                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                                }
                                break;
                            default:
                                break;
                        }
                    } while (choiceMenuVisitor != limitMenuVisitor - 1 && choiceMenuVisitor != 3);
                    break;
                case 4:
                    accountList.addCustomer();
                    FileDAO.writeAccountText(fileNameAccountList, accountList);
                    break;
                default:
                    if (FileDAO.writeAccountText(fileNameAccountList, accountList) && FileDAO.writeFruitListText(fileNameFruitList, listFruit) && FileDAO.writeOrderManagementText(fileNameOrderManagementCloseCustomer, orderManageCloseCustomer) && FileDAO.writeOrderManagementText(fileNameOrderManagement, orderManage) && FileDAO.writeVisitorText(fileNameVisitorList, visitorList)) {
                        System.out.println("Write File Success.");
                    } else {
                        System.out.println("Write File Fail.");
                    }
                    break;
            }
        } while (service != 5);

    }
}
