package dev.backend.playwright;

import dev.backend.playwright.entities.NguoiDung;
import dev.backend.playwright.service.Impl.ReadVnuaDataServiceImpl;
import dev.backend.playwright.service.ReadVnuaDataService;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {Scanner sc = new Scanner(System.in);
       NguoiDung nd = new NguoiDung();
       nd.nhap(sc);

       ReadVnuaDataService dn = new ReadVnuaDataServiceImpl(nd);
       dn.dangNhap();
       dn.layDuLieuLichHoc();
    }
}
