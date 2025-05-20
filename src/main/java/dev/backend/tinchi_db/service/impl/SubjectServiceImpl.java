package dev.backend.tinchi_db.service.impl;

import dev.backend.tinchi_db.dao.SubjectDAO;
import dev.backend.tinchi_db.entities.Subject;
import dev.backend.tinchi_db.service.SubjectService;

import java.util.List;

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
}
