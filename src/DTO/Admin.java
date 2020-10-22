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
public class Admin extends Account{
    private String birthDate;
    private String address;
    private String email;
    private Voucher localVoucher;

    public Admin() {
        super();
        birthDate = "";
        address = "";
        email = ""; 
        localVoucher = new Voucher();
    }
    
    public Admin(Saler tem){
        this.logInID = tem.getLogInID();
        this.password = tem.getPassword();
        this.name = tem.getName();
        this.phone = tem.getPhone();
        this.type = "Admin";
        this.birthDate = tem.getBirthDate();
        this.address = tem.getAddress();
        this.email = tem.getEmail();
        this.localVoucher = tem.getLocalVoucher();
    }

    public Admin(String logInID, String password, String name, String phone, String type, String birthDate, String address, String email, Voucher localVoucher) {
        super(logInID, password, name, phone, type);
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.localVoucher = localVoucher;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Voucher getLocalVoucher() {
        return localVoucher;
    }

    public void setLocalVoucher(Voucher localVoucher) {
        this.localVoucher = localVoucher;
    }

    @Override
    public void output() {
        super.output(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Birth Date: " + birthDate);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Number of vouchers: " + localVoucher.size());
        System.out.println("");
    }

    @Override
    public String toString() {
        return logInID + "," + password + "," + name + "," + phone + "," + type + "," + birthDate + "," + address + "," + email + "\n" + localVoucher.output();
    }
}
