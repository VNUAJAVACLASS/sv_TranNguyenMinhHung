package dev.backend.tinchi_db.service;

import java.util.Scanner;

public interface SubjectService {
    String printSubjectList();
    String searchSubject(String code);
    String addSubject(Scanner sc);
    String updateSubject(Scanner sc);
    String deleteSubject(Scanner sc);
}
