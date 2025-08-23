package dev.backend.tinchi_mysql.service.impl;

import dev.backend.tinchi_mysql.dao.SubjectDAO;
import dev.backend.tinchi_mysql.entities.Subject;
import dev.backend.tinchi_mysql.service.SubjectService;

import java.util.List;
import java.util.Scanner;

public class SubjectServiceImpl implements SubjectService {
    SubjectDAO subjectDAO;

    public SubjectServiceImpl() {
        subjectDAO = new SubjectDAO();
    }

    public String printSubjectList() {
        List<Subject> subjectList = subjectDAO.getAllSubject();

        StringBuilder sb = new StringBuilder();
        System.out.println("Danh sách nhân sự là:");

        if(subjectList.isEmpty()){
            System.out.println("Không có nhân sự nào!");
            return null;
        }

        int stt = 1;
        for(Subject s : subjectList){
            String line = String.format("%d. %s",stt++,s.toString());
            System.out.println(line);
            sb.append(line).append("\n");
        }

        return  sb.toString();
    }

    public String searchSubject(String code) {
        Subject subject = subjectDAO.getSubjectByCode(code);
        if (subject == null) {
            return "Không tìm thấy môn học!";
        }
        return subject.toString();
    }

    public String addSubject(Scanner sc) {
        Subject subject = new Subject();
        System.out.println("Nhập thông tin môn học:");
        subject.nhap(sc);

        if(subjectDAO.insertSubject(subject)){
            return "Thêm môn học thành công!";
        }
        return "Thêm môn học thất bại!";
    }

    public String updateSubject(Scanner sc) {
        System.out.print("Nhập mã môn học cần cập nhật: ");
        String code = sc.nextLine();

        Subject subject = subjectDAO.getSubjectByCode(code);
        if(subject == null){
            System.out.println("Môn học không tồn tại!");
            return null;
        }

        System.out.println("Nhập thông tin mới:");
        subject.enterInfo(sc);

        if(subjectDAO.updateSubject(subject)){
            System.out.println("Cập nhật thông tin thành công!");
            return "Cập nhật thông tin thành công!";
        }

        System.out.println("Sửa thông tin thất bại!");
        return "Sửa thông tin thất bại!";
    }

    public String deleteSubject(Scanner sc) {
        System.out.print("Nhập mã môn học muốn xóa: ");
        String code = sc.nextLine();
        if(subjectDAO.deleteSubject(code)){
            System.out.println("Xóa môn học thành công");
            return "Xóa môn học thành công";
        }

        System.out.println("Xóa môn học thất bại");
        return "Xóa môn học thất bại";
    }
}
