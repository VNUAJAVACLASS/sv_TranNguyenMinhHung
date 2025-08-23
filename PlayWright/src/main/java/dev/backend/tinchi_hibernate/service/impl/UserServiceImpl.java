package dev.backend.tinchi_hibernate.service.impl;

import dev.backend.tinchi_hibernate.dao.SubjectDAO;
import dev.backend.tinchi_hibernate.dao.UserDAO;
import dev.backend.tinchi_hibernate.entities.Human;
import dev.backend.tinchi_hibernate.entities.Lecturer;
import dev.backend.tinchi_hibernate.entities.Student;
import dev.backend.tinchi_hibernate.entities.Subject;
import dev.backend.tinchi_hibernate.service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private SubjectDAO subjectDAO;

    public UserServiceImpl() {
        userDAO = new UserDAO();
        subjectDAO = new SubjectDAO();
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

    //Sua thong tin nhan su
    public String updateHR(Scanner sc) {
        System.out.print("Nhập mã nhân sự cần cập nhật: ");
        String code = sc.nextLine();

        Human oldInfo = userDAO.getHumanByCode(code);
        if (oldInfo == null) {
            return "Không tìm thấy nhân sự!";
        }

        System.out.println("Nhập thông tin mới:");
        if(oldInfo instanceof Lecturer){
            oldInfo = new Lecturer();
        }else if(oldInfo instanceof Student){
            oldInfo = new Student();
        }

        oldInfo.enterInfo(sc);
        oldInfo.setCode(code);

        if(userDAO.updateHuman(oldInfo)) {
            return "Cập nhật thông tin thành công!";
        }

        return "Sửa thông tin thất bại!";
    }

    //Xoa nhan su
    public String deleteHR(Scanner sc) {
        System.out.print("Nhập mã nhân sự muốn xóa: ");
        String code = sc.nextLine();
        if(userDAO.deleteHumanByCode(code)){
            return "Xóa nhân sự thành công!";
        }

        return "Xóa nhân sự thất bại!";
    }

    //Dang ki mon hoc cho sinh vien
    public String registerSubject(String studentCode, String subjectCode) {
        Student student = userDAO.getStudentByCode(studentCode);
        if(student == null){
            System.out.println("Không tìm thấy sinh viên!");
            return "Không tìm thấy sinh viên!";
        }
        System.out.println("Chọn môn học đăng ký");
        Subject subject =  subjectDAO.getSubjectByCode(subjectCode);
        if(subject == null){
            System.out.println("Không tìm thấy môn học!");
            return "Không tìm thấy môn học!";
        }

        if(userDAO.addSubjectForStudent(student, subject)) {
            System.out.println("Thêm môn học cho sinh viên thành công!");
            return "Thêm môn học cho sinh viên thành công!";
        }

        return "Thêm môn học cho sinh viên thất bại!";
    }

    //Nhap diem
    public String addScore(String studentCode, String subjectCode) {
        Student student = userDAO.getStudentByCode(studentCode);
        if(student == null){
            System.out.println("Không tìm thấy sinh viên!");
            return "Không tìm thấy sinh viên!";
        }

        Subject subject = subjectDAO.getSubjectByCode(subjectCode);
        if(subject == null){
            System.out.println("Không tìm thấy môn học!");
            return "Không tìm thấy môn học!";
        }

        if(userDAO.addScore(student, subject)) {
            System.out.println("Nhập điểm sinh viên thành công!");
            return "Nhập điểm sinh viên thành công!";
        }

        return "Nhập điểm sinh viên không thành công!";
    }
}
