package dev.backend.tinchi_db;

import dev.backend.tinchi_db.service.UserService;
import dev.backend.tinchi_db.service.impl.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
//        userService.printHRList();
//        System.out.println("===========================================");
//        userService.printStudentList();
//        System.out.println("===========================================");
//        userService.printLecturerList();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào mã nhân sự: ");
        String code = sc.nextLine();
        System.out.println(userService.searchHuman(code));

    }
}
