package dev.backend.tinchi_hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Scanner;

@Entity
@Table(name = "tbl_users")
public class Lecturer extends Human {
    @Column(name = "password")
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
        return super.toString() + "\n\tMật khẩu: " + password;
    }

    //Getter Setter
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
