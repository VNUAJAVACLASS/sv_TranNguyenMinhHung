package dev.backend.tinchi_db.dao;

import dev.backend.tinchi_db.entities.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    Connection conn;

    public SubjectDAO() {
        try {
            String url = "jdbc:ucanaccess://lib/baitoantinchi.accdb";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subject> getAllSubject() {
        List<Subject> listSubject = new ArrayList<>();

        String sql = "select * from tbl_subject";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //Duyet tung dong du lieu
            while (rs.next()) {
                String subjectCode = rs.getString("subject_code");
                String subjectName = rs.getString("subject_name");
                int credit = rs.getInt("credit");
                float attendenceMark = rs.getFloat("attendence_exam_mark");
                float midExamMark1 = rs.getFloat("middle_exam_mark1");
                float midExamMark2 = rs.getFloat("middle_exam_mark2");
                float midExamMark3 = rs.getFloat("middle_exam_mark3");
                float finnalExamMark = rs.getFloat("final_exam_mark");

                Subject subject = new Subject(subjectCode, subjectName, credit, attendenceMark, midExamMark1,midExamMark2, midExamMark3,finnalExamMark);

                listSubject.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSubject;
    }
}
