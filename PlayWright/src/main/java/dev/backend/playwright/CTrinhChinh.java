package dev.backend.playwright;

import dev.backend.playwright.service.Impl.ReadHTMLServiceImpl;
import dev.backend.playwright.service.ReadHTMLService;

import java.io.IOException;
import java.util.*;

public class CTrinhChinh {
    public static void main(String[] args) throws IOException {
        ReadHTMLService js = new ReadHTMLServiceImpl();
        js.docFileHTML("src/main/resources/tkb_TranNguyenMinhHung.html");
//        js.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/html/tkb_HaNgocQuyen.html");

        //đọc thời khóa biểu giảng viên
//        js.docFileHTMLGV("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/html/tkb.html");


        Scanner sc = new Scanner(System.in);

        System.out.println("\n1. Lịch học hôm nay:");
        js.hienThiLichHocHomNay();

        System.out.println("\n===================================================");

        System.out.println("\n2. Xem thời khóa biểu cả tuần:");
        System.out.print("Nhập số tuần cần xem (1-22): ");
        int soTuan = sc.nextInt();
        js.hienThiLicHocCaTuan(soTuan);

        System.out.println("\n===================================================");

        System.out.println("\n3. Xem thời khóa biểu theo tuần, thứ:");
        System.out.print("Nhập tuần: ");
        int soTuan1 = sc.nextInt();
        System.out.print("Nhập thứ (2-7 hoặc 8 cho CN): ");
        int soThu = sc.nextInt();
        js.hienThiLichHocTheoTuanVaThu(soTuan1, soThu);

        System.out.println("\n===================================================");
        System.out.println("\n4. Xem thời khóa biểu theo ngày:");
        System.out.print("Nhập ngày (dd/MM/yyyy): ");
        sc.nextLine();
        String ngay = sc.nextLine();
        js.hienThiLichHocTheoNgay(ngay);

        System.out.println("\n===================================================");
        sc.close();
    }
}