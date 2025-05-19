package dev.backend.tinchi_db.entities;

public class Subject {
    private String subjectCode;
    private String subjectName;
    private int credit;
    private float attendanceMark, midExamMark, finalExamMark;

    //Constructor
    public Subject(){}

    public Subject(String subjectCode, String subjectName, int credit) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credit = credit;
    }

    //method
    public float calConversionMark(){
        float subjectMark = calSubjectnMark();
        float conversionMark = -1;

        if(subjectMark <= 3.9) conversionMark = 0;
        else if(subjectMark <= 4.9) conversionMark = 1;
        else if (subjectMark <= 5.4) conversionMark = 1.5f;
        else if(subjectMark <= 6.4) conversionMark = 2;
        else if(subjectMark <= 6.9) conversionMark = 2.5f;
        else if(subjectMark <= 7.9) conversionMark = 3;
        else if(subjectMark <= 8.4) conversionMark = 3.5f;
        else conversionMark = 4;

        return conversionMark;
    }

    public float calConversionMark(String grade){
        float conversionMark = switch (grade){
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

    public float calSubjectnMark(){
        return attendanceMark * 0.1f + midExamMark * 0.4f + finalExamMark * 0.5f;
    }

    public String calGrade(){
        float subjectMark = calSubjectnMark();
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

    public float getMidExamMark() {
        return midExamMark;
    }

    public void setMidExamMark(float midExamMark) {
        this.midExamMark = midExamMark;
    }

    public float getFinalExamMark() {
        return finalExamMark;
    }

    public void setFinalExamMark(float finalExamMark) {
        this.finalExamMark = finalExamMark;
    }
}
