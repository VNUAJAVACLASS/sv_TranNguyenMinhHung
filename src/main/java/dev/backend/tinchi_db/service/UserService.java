package dev.backend.tinchi_db.service;

import java.util.Scanner;

public interface UserService {
    String printHRList();
    String printStudentList();
    String printLecturerList();
    String searchHuman(String code);
    String addHR(Scanner sc);
    String updateHR(Scanner sc);
    String deleteHR(Scanner sc);
    String registerSubject(String studentCode, String subjectCode);
    String addScore(String  studentCode, String subjectCode);
}
