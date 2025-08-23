package dev.backend.tinchi_db.dao;

import dev.backend.tinchi_db.entities.Subject;

import static dev.backend.tinchi_db.Connection.AccessConnection.getConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    Connection conn;

    public SubjectDAO() {
        conn = getConnection();
    }

    //lấy danh sách môn học
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

                Subject subject = new Subject(subjectCode, subjectName, credit, attendenceMark, midExamMark1, midExamMark2, midExamMark3, finnalExamMark);

                listSubject.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSubject;
    }

    //tìm kiếm môn theo mã
    public Subject getSubjectByCode(String subjectCode) {
        String sql = "select * from tbl_subject where subject_code=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, subjectCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String subjectCode1 = rs.getString("subject_code");
                String subjectName = rs.getString("subject_name");
                int credit = rs.getInt("credit");
                float attendenceMark = rs.getFloat("attendence_exam_mark");
                float midExamMark1 = rs.getFloat("middle_exam_mark1");
                float midExamMark2 = rs.getFloat("middle_exam_mark2");
                float midExamMark3 = rs.getFloat("middle_exam_mark3");
                float finnalExamMark = rs.getFloat("final_exam_mark");

                return new Subject(subjectCode1, subjectName, credit, attendenceMark, midExamMark1, midExamMark2, midExamMark3, finnalExamMark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Thêm môn học
    public boolean insertSubject(Subject subject) {
        String sql = "insert into tbl_subject (subject_code, subject_name, credit, attendence_exam_mark,middle_exam_mark1,middle_exam_mark2,middle_exam_mark3,final_exam_mark) " +
                "values(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, subject.getSubjectCode());
            pstmt.setString(2, subject.getSubjectName());
            pstmt.setInt(3, subject.getCredit());
            pstmt.setFloat(4, subject.getAttendanceMark());
            pstmt.setFloat(5, subject.getMidExamMark1());
            pstmt.setFloat(6, subject.getMidExamMark2());
            pstmt.setFloat(7, subject.getMidExamMark3());
            pstmt.setFloat(8, subject.getFinalExamMark());

            int rowInserted = pstmt.executeUpdate();
            return rowInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Sửa thông tin môn học
    public boolean updateSubject(Subject subject) {
        String sql = "update tbl_subject set subject_name = ?, credit = ?," +
                "attendence_exam_mark = ?, middle_exam_mark1 = ?, middle_exam_mark2 = ?," +
                "middle_exam_mark3 = ?, final_exam_mark = ? where subject_code = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, subject.getSubjectName());
            pstmt.setInt(2, subject.getCredit());
            pstmt.setFloat(3, subject.getAttendanceMark());
            pstmt.setFloat(4, subject.getMidExamMark1());
            pstmt.setFloat(5, subject.getMidExamMark2());
            pstmt.setFloat(6, subject.getMidExamMark3());
            pstmt.setFloat(7, subject.getFinalExamMark());
            pstmt.setString(8, subject.getSubjectCode());

            int rowUpdated = pstmt.executeUpdate();
            return rowUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //xóa môn học
    public boolean deleteSubject(String subjectCode) {
        String sql = "delete from tbl_subject where subject_code=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, subjectCode);
            int rowDeleted = pstmt.executeUpdate();
            return rowDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
