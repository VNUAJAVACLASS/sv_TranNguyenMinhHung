package dev.backend.tinchi_db.entities;

import java.util.Scanner;

public abstract class Human {
    //Khai bao cac thuoc tinh
    protected String code;
    protected String fullname;
    protected String address;
    protected int role;
    private Human hm;

    //Constructor
    public Human(){}

    public Human(String code) {
        this.code = code;
    }

    public Human(String code, String fullname) {
        this.code = code;
        this.fullname = fullname;
    }

    public Human(String code, String fullname, String address) {
        this.code = code;
        this.fullname = fullname;
        this.address = address;
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
        Human hm = null;
        if(hm instanceof Lecturer){
            this.role =0;
        }else if(hm instanceof Student){
            this.role = 1;
        }
    }

    public void enterInfo(Scanner sc){
        sc.nextLine();
        System.out.print("Nhập họ tên: ");
        fullname = sc.nextLine();
        System.out.print("Nhập địa chỉ: ");
        address = sc.nextLine();
        Human hm = null;
        if(hm instanceof Lecturer){
            this.role = 0;
        }else if(hm instanceof Student){
            this.role = 1;
        }
    }

    //To String
    public String toString(){
        String str = "\n\tMã: " + code
                + "\n\tHọ tên: " + fullname
                + "\n\tĐịa chỉ: " + address;

        return str;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
