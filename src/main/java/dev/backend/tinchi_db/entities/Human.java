package dev.backend.tinchi_db.entities;

import java.util.Scanner;

public class Human {
    //Khai bao cac thuoc tinh
    protected String address;
    protected String code;
    protected String fullname;

    //Constructor
    public Human(){}

    public Human(String code) {
        this.code = code;
    }

    public Human(String code, String fullname) {
        this.code = code;
        this.fullname = fullname;
    }

    public Human(String fullname, String code, String address) {
        this.address = address;
        this.code = code;
        this.fullname = fullname;
    }

    //method
    public void nhap(Scanner sc){
        sc.nextLine();
        System.out.print("Nhập mã: " );
        code = sc.nextLine();
        System.out.print("Nhập họ tên: ");
        fullname = sc.nextLine();
        System.out.print("Nhập địa chỉ: ");
        address = sc.nextLine();
    }

    //To String
    public String toString(){
        return fullname + " - " + code + " - " + address;
    }


    //Getter
    public String getAddress() {
        return address;
    }

    public String getCode() {
        return code;
    }

    public String getFullname() {
        return fullname;
    }

    //Setter
    public void setAddress(String address) {
        this.address = address;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
