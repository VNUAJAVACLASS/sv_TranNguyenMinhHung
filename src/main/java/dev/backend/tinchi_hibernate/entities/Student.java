package dev.backend.tinchi_hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Entity
@Table(name = "tbl_users")
public class Student extends Human {
    @Column(name = "class")
    private String _class;

//    private List<Subject> subjectList = new ArrayList<Subject>();

//    @Transient
//    private Map<String, Term>

    //Constructor
    public Student(){}

    public Student(String code){
        super(code);
    }

    public Student(String code, String fullname){
        super(code, fullname);
    }

    public Student(String code, String fullname, String _class){
        super(code, fullname);
        this._class = _class;
    }

    public Student(String code, String fullname, String address, String _class){
        super(code, fullname, address);
        this._class = _class;
    }

    //Method
//    public void addSubject(Subject sub){
//        subjectList.add(sub);
//    }

//    public float calTermAverageMark(){
//        float ts = 0;
//        int ms = 0;
//
//        for(Subject sub: subjectList){
//            ts += sub.getCredit() * sub.calConversionMark();
//            ms += sub.calConversionMark();
//        }
//
//        return ts/ms;
//    }

    @Override
    public void nhap(Scanner sc) {
        super.nhap(sc);
        System.out.print("Nhập lớp: ");
        _class = sc.nextLine();
    }

    @Override
    public void enterInfo(Scanner sc) {
        super.enterInfo(sc);
        System.out.print("Nhập lớp: ");
        _class = sc.nextLine();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tLớp: " + _class;
    }

    //Getter and Setter
    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}
