package dev.backend.tinchi_db.service;

public interface UserService {
    //Lay danh sach nhan su
    String printHRList();
    //Lay danh sach sinh vien
    String printStudentList();
    //lay danh sach giang viet
    String printLecturerList();
    //tim kiem theo ma
    String searchHuman(String code);
}
