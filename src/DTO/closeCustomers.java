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
public class closeCustomers extends Account{
    private String birthDate;
    private String address;
    private String email;
    private String rank;
    private double point;
    private Voucher voucher;

    public closeCustomers() {
        super();
        birthDate = "";
        address = "";
        type = "";
        email = "";
        point = 0;
        voucher = new Voucher();
    }

    public closeCustomers(String logInID, String password, String name, String phone, String type, String birthDate, String address, String email, String rank, double point, Voucher voucher) {
        super(logInID, password, name, phone, type);
        this.birthDate = birthDate;
        this.address = address;
        this.rank = rank;
        this.email = email;
        this.point = point;
        this.voucher = voucher;
    }

    public String getLoginID() {
        return logInID;
    }

    public void setLoginID(String loginID) {
        this.logInID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    @Override
    public void output() {
        super.output();
        System.out.println("Birth Date: " + birthDate);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Member rank: " + rank);
        System.out.println("Point earned: " + point);
        System.out.println("Number of vouchers: " + voucher.size());
        System.out.println("");
    }

    @Override
    public String toString() {
        return logInID + "," + password + "," + name + "," + phone + "," + type + "," + birthDate + "," + address + "," + email + "," + rank + "," + point + "\n" + voucher.output();
    }
    
    
}
