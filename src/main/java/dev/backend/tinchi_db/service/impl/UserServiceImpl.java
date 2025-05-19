package dev.backend.tinchi_db.service.impl;

import dev.backend.tinchi_db.dao.UserDAO;
import dev.backend.tinchi_db.entities.Human;
import dev.backend.tinchi_db.service.UserService;

import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAO();
    }

    public String printHRList() {
        List<Human> hrList = userDAO.getAllHuman(); // Không cần khởi tạo rỗng rồi gán lại

        StringBuilder result = new StringBuilder();
        System.out.println("Danh sách nhân sự là:");

        if (hrList.isEmpty()) {
            System.out.println("Không có nhân sự nào!");
            return "Không có nhân sự nào!";
        }

        int stt = 1;
        for (Human human : hrList) {
            String line = String.format("%d. %s", stt++, human.toString());
            System.out.println(line);
            result.append(line).append("\n");
        }

        return result.toString();
    }

}
