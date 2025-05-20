package dev.backend.tinchi_db.entities;

import java.util.Scanner;

public class Lecturer extends Human {
    private String password;

    public Lecturer() {
    }

    public Lecturer(String code, String password) {
        super(code);
        this.password = password;
    }

    public Lecturer(String code, String fullname, String address, String password) {
        super(code, fullname, address);
        this.password = password;
    }

    //method

    @Override
    public void nhap(Scanner sc) {
        super.nhap(sc);
        System.out.print("Nhập mật khẩu: ");
        password = sc.nextLine();
    }

    @Override
    public void enterInfo(Scanner sc) {
        super.enterInfo(sc);
        System.out.print("Nhập mật khẩu: ");
        password = sc.nextLine();
    }

    @Override
    public String toString() {
        return super.toString() + "\nMật khẩu: " + password;
    }

    //Getter Setter
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
