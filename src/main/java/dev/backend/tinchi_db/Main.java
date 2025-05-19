package dev.backend.tinchi_db;

import dev.backend.tinchi_db.service.UserService;
import dev.backend.tinchi_db.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.printHRList();
    }
}
