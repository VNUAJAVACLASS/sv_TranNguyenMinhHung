package dev.backend.tinchi_db;

import dev.backend.tinchi_db.dao.SubjectDAO;
import dev.backend.tinchi_db.service.SubjectService;
import dev.backend.tinchi_db.service.UserService;
import dev.backend.tinchi_db.service.impl.SubjectServiceImpl;
import dev.backend.tinchi_db.service.impl.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        SubjectService subjectService = new SubjectServiceImpl();
        Scanner sc = new Scanner(System.in);
        int chon;
        String code;

        do {
            //Menu
            System.out.println("===============MENU===============");
            System.out.println("1. Xem toàn bộ danh sách nhân sự.");
            System.out.println("2. Xem danh sách sinh viên.");
            System.out.println("3. Xem danh sách giảng viên.");
            System.out.println("4. Tìm nhân sự.");
            System.out.println("5. Thêm nhân sự.");
            System.out.println("6. Sửa thông tin nhân sự.");
            System.out.println("7. Xóa nhân sự.");
            System.out.println("8. Xem danh sách môn học.");
            System.out.println("9. Tìm kiếm môn học.");
            System.out.println("10. Thêm môn học.");
            System.out.println("11. Sửa môn học.");
            System.out.println("12. Xóa môn học.");
            System.out.println("13. Đăng ký môn học.");
            System.out.println("14. Nhập điểm sinh viên.");
            System.out.println("0.Thoát chương trình");

            System.out.print("\nNhập lựa chọn: ");
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 -> userService.printHRList();
                case 2 -> userService.printStudentList();
                case 3 -> userService.printLecturerList();
                case 4 -> {
                    System.out.print("Nhập mã nhân sự: ");
                    code = sc.nextLine();
                    System.out.println(userService.searchHuman(code));
                }
                case 5 -> userService.addHR(sc);
                case 6 -> userService.updateHR(sc);
                case 7 -> userService.deleteHR(sc);
                case 8 -> subjectService.printSubjectList();
                case 9 -> {
                    System.out.print("Nhập mã môn học: ");
                    code = sc.nextLine();
                    System.out.println(subjectService.searchSubject(code));
                }
                case 10 -> subjectService.addSubject(sc);
                case 11 -> subjectService.updateSubject(sc);
                case 12 -> subjectService.deleteSubject(sc);
                case 13 ->{
                    //in ra danh sách sinh viên
                    userService.printStudentList();
                    System.out.print("Nhập mã sinh viên: ");
                    String studentCode = sc.nextLine();

                    //in ra danh sách môn
                    subjectService.printSubjectList();
                    System.out.print("Nhập mã môn học: ");
                    String subjectCode = sc.nextLine();

                    //Đang kí môn
                    userService.registerSubject(studentCode, subjectCode);
                }
                case 14 ->{
                    //in ra danh sách sinh viên
                    userService.printStudentList();
                    System.out.print("Nhập mã sinh viên: ");
                    String studentCode = sc.nextLine();

                    //in ra danh sách môn
                    subjectService.printSubjectList();
                    System.out.print("Nhập mã môn học: ");
                    String subjectCode = sc.nextLine();

                    //nhap diem
                    userService.addScore(studentCode, subjectCode);
                }
                case 0 ->{
                    System.out.println("Đang thoát chương trình...");
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Chọn lại!");
            }

            System.out.println("\n");
        } while (chon != 0);

        sc.close();
    }
}
