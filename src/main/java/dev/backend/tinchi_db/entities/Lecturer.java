package dev.backend.tinchi_db.entities;

import java.util.Scanner;

public class Lecturer extends Human{
    private String password;

    public Lecturer(){}

    public Lecturer(String code, String password) {
        super(code);
        this.password = password;
    }

    public Lecturer(String address, String code, String fullname) {
        super(address, code, fullname);
    }

    //method


    @Override
    public void nhap(Scanner sc) {
        super.nhap(sc);
        System.out.print("Nhập mật khẩu: ");
        password = sc.nextLine();
    }

    //Getter Setter
    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
