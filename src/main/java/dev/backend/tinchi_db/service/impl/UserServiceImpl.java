package dev.backend.tinchi_db.service.impl;

import dev.backend.tinchi_db.dao.UserDAO;
import dev.backend.tinchi_db.entities.Human;
import dev.backend.tinchi_db.entities.Lecturer;
import dev.backend.tinchi_db.entities.Student;
import dev.backend.tinchi_db.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAO();
    }

    public String printHRList() {
        List<Human> hrList = userDAO.getAllHuman();

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

    //in danh sach giang vien
    public String printLecturerList() {
        List<Human> hrList = userDAO.getAllLecturer();

        StringBuilder result = new StringBuilder();
        System.out.println("Danh sách giảng viên là:");

        if (hrList.isEmpty()) {
            System.out.println("Không có giảng viên nào!");
            return null;
        }

        int stt = 1;
        for (Human human : hrList) {
            String line = String.format("%d. %s", stt++, human.toString());
            System.out.println(line);
            result.append(line).append("\n");
        }

        return result.toString();
    }

    //in danh sach sinh vien
    public String printStudentList() {
        List<Human> hrList = userDAO.getAllStudent();

        StringBuilder result = new StringBuilder();
        System.out.println("Danh sách sinh viên là:");

        if (hrList.isEmpty()) {
            System.out.println("Không có sinh viên nào!");
            return null;
        }

        int stt = 1;
        for (Human human : hrList) {
            String line = String.format("%d. %s", stt++, human.toString());
            System.out.println(line);
            result.append(line).append("\n");
        }

        return result.toString();
    }

    //tim kiem nhan su theo ma
    public String searchHuman(String code) {
        Human human = userDAO.getHumanByCode(code);
        if (human == null) {
            return "Không tìm thấy!";
        }

        return human.toString();
    }

    //them nhan su
    public String addHR(Scanner sc) {
        System.out.print("Chọn loại nhân sự (0 - Lecturer, 1 - Student): ");
        int chon = sc.nextInt();

        Human hm;
        switch (chon) {
            case 0->{
                hm = new Lecturer();
                hm.nhap(sc);
            }
            case 1->{
                hm = new Student();
                hm.nhap(sc);
            }
            default -> {
                return "Loại nhân sự không hợp lệ!";
            }
        }

        if(userDAO.addHuman(hm)) {
            return "Thêm nhân sự thành công!";
        }

        return "Thêm nhân sự thất bại!";
    }
}
