package dev.backend.webbanthucung;

import dev.backend.webbanthucung.entities.NguoiDung;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {Scanner sc = new Scanner(System.in);
       NguoiDung nd = new NguoiDung();
       nd.nhap(sc);

       DangNhapVNUA dn = new DangNhapVNUA(nd);
       dn.dangNhap();
       dn.layDuLieuLichHoc();
    }
}
