package dev.backend.playwright;

import dev.backend.playwright.entities.NguoiDung;
import dev.backend.playwright.service.Impl.ReadHTMLServiceImpl;
import dev.backend.playwright.service.Impl.ReadVnuaDataServiceImpl;
import dev.backend.playwright.service.ReadHTMLService;
import dev.backend.playwright.service.ReadVnuaDataService;

import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NguoiDung nd = new NguoiDung();
        nd.nhap(sc);

        ReadVnuaDataService dn = new ReadVnuaDataServiceImpl(nd);
        dn.dangNhap();
        dn.layDuLieuLichHoc();

        ReadHTMLService hr = new ReadHTMLServiceImpl();
        try {
            hr.docFileHTML("src/main/resources/tkb.html");
            System.out.println("\n1. Lịch học hôm nay:");
            hr.hienThiLichHocHomNay();

            System.out.println("\n===================================================");

            System.out.println("\n2. Xem thời khóa biểu cả tuần:");
            System.out.print("Nhập số tuần cần xem (1-22): ");
            int soTuan = sc.nextInt();
            hr.hienThiLicHocCaTuan(soTuan);

            System.out.println("\n===================================================");

            System.out.println("\n3. Xem thời khóa biểu theo tuần, thứ:");
            System.out.print("Nhập tuần: ");
            int soTuan1 = sc.nextInt();
            System.out.print("Nhập thứ (2-7 hoặc 8 cho CN): ");
            int soThu = sc.nextInt();
            hr.hienThiLichHocTheoTuanVaThu(soTuan1, soThu);

            System.out.println("\n===================================================");
            System.out.println("\n4. Xem thời khóa biểu theo ngày:");
            System.out.print("Nhập ngày (dd/MM/yyyy): ");
            sc.nextLine();
            String ngay = sc.nextLine();
            hr.hienThiLichHocTheoNgay(ngay);

            System.out.println("\n===================================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
