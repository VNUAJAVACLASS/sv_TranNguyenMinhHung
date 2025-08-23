package dev.backend.tinchi_hibernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Scanner;

@Entity
@Table(name = "tbl_subject")
public class Subject {
    @Id
    private String subjectCode;
    private String subjectName;
    private int credit;
    private float attendanceMark;
    private float midExamMark1, midExamMark2, midExamMark3;
    private float finalExamMark;
    private float totalMark;

    //Constructor
    public Subject() {
    }

    public Subject(String subjectCode, String subjectName, int credit) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credit = credit;
    }

    public Subject(String subjectCode, String subjectName, int credit, float attendanceMark, float midExamMark1, float midExamMark2, float midExamMark3, float finalExamMark) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credit = credit;
        this.attendanceMark = attendanceMark;
        this.midExamMark1 = midExamMark1;
        this.midExamMark2 = midExamMark2;
        this.midExamMark3 = midExamMark3;
        this.finalExamMark = finalExamMark;
    }

    //method
    public float calConversionMark() {
        float subjectMark = getTotalMark();
        float conversionMark = -1;

        if (subjectMark <= 3.9) conversionMark = 0;
        else if (subjectMark <= 4.9) conversionMark = 1;
        else if (subjectMark <= 5.4) conversionMark = 1.5f;
        else if (subjectMark <= 6.4) conversionMark = 2;
        else if (subjectMark <= 6.9) conversionMark = 2.5f;
        else if (subjectMark <= 7.9) conversionMark = 3;
        else if (subjectMark <= 8.4) conversionMark = 3.5f;
        else conversionMark = 4;

        return conversionMark;
    }

    public float calConversionMark(String grade) {
        float conversionMark = switch (grade) {
            case "F" -> 0;
            case "D" -> 1;
            case "D+" -> 1.5f;
            case "C" -> 2;
            case "C+" -> 2.5f;
            case "B" -> 3;
            case "B+" -> 3.5f;
            case "A" -> 4;

            default -> -1;
        };

        return conversionMark;
    }

    public float calSubjectnMark(float cc, float gk, float ck) {
        float total = cc * attendanceMark + gk * midExamMark1 + ck * finalExamMark;
        setTotalMark(total);

        return total;
    }

    public float calSubjectnMark(float cc, float gk, float gk2, float ck) {
        float total = cc * attendanceMark + gk * midExamMark1 + gk2 * midExamMark2 + ck * finalExamMark;
        setTotalMark(total);

        return total;
    }

    public float calSubjectnMark(float cc, float gk, float gk2, float gk3, float ck) {
        float total = cc * attendanceMark + gk * midExamMark1 + gk2 * midExamMark2 + +gk3 * midExamMark3 + finalExamMark;
        setTotalMark(total);

        return total;
    }

    public String calGrade() {
        float subjectMark = getTotalMark();
        String grade = subjectMark <= 3.9 ? "F" :
                       subjectMark <= 4.9 ? "D" :
                       subjectMark <= 5.4 ? "D+" :
                       subjectMark <= 6.4 ? "C" :
                       subjectMark <= 6.9 ? "C+" :
                       subjectMark <= 7.9 ? "B" :
                       subjectMark <= 8.4 ? "B+" :
                       subjectMark <= 10 ? "A" : null;

        return grade;
    }

    @Override
    public String toString() {
        return "\n\tMã môn học: " + subjectCode
                + "\n\tTên môn học: " + subjectName
                + "\n\tSố tín chỉ: " + credit
                + "\n\tĐiểm chuyên cần: " + attendanceMark
                + "\n\tĐiểm giữa kì 1: " + midExamMark1
                + "\n\tĐiểm giữa kì 2: " + midExamMark2
                + "\n\tĐiểm giữa kì 3: " + midExamMark3
                + "\n\tĐiểm cuối kì: " + finalExamMark;
    }

    public void nhap(Scanner sc){
        System.out.print("Nhập mã môn học: "); subjectCode = sc.nextLine();
        System.out.print("Nhập tên môn học: "); subjectName = sc.nextLine();
        System.out.print("Nhập số tín chỉ: "); credit = sc.nextInt();
        System.out.print("Nhập điểm chuyên cần: "); attendanceMark = sc.nextFloat();
        System.out.print("Nhập điểm giữa kì 1: "); midExamMark1 = sc.nextFloat();
        System.out.print("Nhập điểm giữa kì 2: "); midExamMark2 = sc.nextFloat();
        System.out.print("Nhập điểm giữa kì 3: "); midExamMark3 = sc.nextFloat();
        System.out.print("Nhập điểm cuối kì: "); finalExamMark = sc.nextFloat();
    }

    public void enterInfo(Scanner sc){
        System.out.print("Nhập tên môn học: "); subjectName = sc.nextLine();
        System.out.print("Nhập số tín chỉ: "); credit = sc.nextInt();
        System.out.print("Nhập điểm chuyên cần: "); attendanceMark = sc.nextFloat();
        System.out.print("Nhập điểm giữa kì 1: "); midExamMark1 = sc.nextFloat();
        System.out.print("Nhập điểm giữa kì 2: "); midExamMark2 = sc.nextFloat();
        System.out.print("Nhập điểm giữa kì 3: "); midExamMark3 = sc.nextFloat();
        System.out.print("Nhập điểm cuối kì: "); finalExamMark = sc.nextFloat();
    }

    //Getter and Setter
    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public float getAttendanceMark() {
        return attendanceMark;
    }

    public void setAttendanceMark(float attendanceMark) {
        this.attendanceMark = attendanceMark;
    }

    public float getMidExamMark1() {
        return midExamMark1;
    }

    public void setMidExamMark1(float midExamMark1) {
        this.midExamMark1 = midExamMark1;
    }

    public float getMidExamMark2() {
        return midExamMark2;
    }

    public void setMidExamMark2(float midExamMark2) {
        this.midExamMark2 = midExamMark2;
    }

    public float getMidExamMark3() {
        return midExamMark3;
    }

    public void setMidExamMark3(float midExamMark3) {
        this.midExamMark3 = midExamMark3;
    }

    public float getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(float totalMark) {
        this.totalMark = totalMark;
    }

    public float getFinalExamMark() {
        return finalExamMark;
    }

    public void setFinalExamMark(float finalExamMark) {
        this.finalExamMark = finalExamMark;
    }
}
