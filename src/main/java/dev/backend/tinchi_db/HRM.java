package dev.backend.tinchi_db;

import dev.backend.tinchi_db.entities.Human;
import dev.backend.tinchi_db.entities.Lecturer;
import dev.backend.tinchi_db.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HRM {
    private List<Human> hrList = new ArrayList<Human>();

    public HRM(){

    }

    //method
    public void addHR(Human human){
        hrList.add(human);
    }

    public void addHR(Scanner sc){
        System.out.print("Chọn loại nhân sự (sv: 0, gv: 1): ");
        int loai = sc.nextInt();

        Human human = (loai == 0) ? new Student() :
                (loai == 1) ? new Lecturer() : null;

        human.nhap(sc);

        addHR(human);
    }

    public void printHRList(){
        for(Human hm: hrList){
            System.out.println(hm.toString());
        }
    }

    public void printStudentList(){
        for(Human hm: hrList){
            if(hm instanceof Student){
                Student st = (Student)hm;
                System.out.println(st.toString());
            }
        }
    }

    public void printLecturerList(){
        for(Human hm: hrList){
            if(hm instanceof Lecturer){
                Lecturer lt = (Lecturer)hm;
                System.out.println(lt.toString());
            }
        }
    }

    public void initDemoData(){
        Human h1 = new Student("671501", "Trần Nguyễn Minh hùng", "Nghệ An", "K67CNPMC");
        Human h2 = new Student("6660582", "Hoàng Thị Ánh Tuyết", "Hải Dương", "K66TYA");

        addHR(h2);
        addHR(h1);
    }

    public void initDemoData(Scanner sc){
        String chon;
        do {
            //Nhap thong tin nhan su
            addHR(sc);

            //Hoi co muon nhap tiep không
            System.out.print("Bạn có muốn nhập tiếp không(c/k): ");
            chon = sc.nextLine();
        }while ("c".equalsIgnoreCase(chon));
    }

    //Hàm tìm kiếm nhân sự
    public List<Human> searchHuman(String code){
        List<Human> humanList = new ArrayList<Human>();
        for(Human human: hrList){
            if(human.getCode().contains(code)){
                humanList.add(human);
            }
        }

        return humanList;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HRM hrm = new HRM();

        hrm.initDemoData();
        //hrm.initDemoData(sc);

        System.out.println("====================================================");
        System.out.println("Danh sách tất cả nhân sự: ");
        hrm.printHRList();

        System.out.println("====================================================");
        System.out.println("Danh sách tất cả sinh viên: ");
        hrm.printStudentList();

        System.out.println("====================================================");
        System.out.println("Danh sách tất cả giảng viên: ");
        hrm.printLecturerList();

        System.out.println("====================================================");
        System.out.println("Tìm kiếm: ");
        hrm.searchHuman("66");
    }
}
