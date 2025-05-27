package dev.backend.playwright.service.Impl;

import com.microsoft.playwright.*;
import dev.backend.playwright.entities.NguoiDung;
import dev.backend.playwright.service.ReadVnuaDataService;

import javax.swing.text.Style;
import java.util.*;

public class ReadVnuaDataServiceImpl implements ReadVnuaDataService {
    private static final String url = "https://daotao.vnua.edu.vn/#/home";
    private NguoiDung nd;
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private Map<Integer, String> mapHocKy = new HashMap<Integer, String>();

    public ReadVnuaDataServiceImpl() {
        nd = new NguoiDung();
    }

    public ReadVnuaDataServiceImpl(NguoiDung nd) {
        this.nd = nd;
    }

    public void dangNhap() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        page = browser.newPage();

        page.navigate(url);

        //cho nut dang nhap
        page.waitForSelector("button:has-text('Đăng nhập')");
        page.click("button:has-text('Đăng nhập')");

        //dien thong tin vao username va password
        page.waitForSelector("input[formcontrolname='username']");
        page.fill("input[formcontrolname='username']", nd.getUsername().trim());
        page.fill("input[formcontrolname='password']", nd.getPassword().trim());

        //click vao nut dang nhap
        page.click("button:has-text('Đăng nhập')");
        page.waitForSelector("span.text-primary.text-justify");
//        page.waitForTimeout(10000);

    }

    public void layDuLieuLichHoc() {
        if (page == null) {
            System.out.println("Bạn cần đăng nhập trước khi truy cập thời khóa biểu!");
            return;
        }

        // Click vào tab thời khóa biểu học kỳ
        String tkb = "#WEB_TKB_HK";
        page.waitForSelector(tkb);
        page.click(tkb);

        // Mở combobox chọn học kỳ
        String combobox = "div[role='combobox']";
        page.waitForSelector(combobox);
        page.click(combobox);
        page.waitForTimeout(10000);

        List<String> dsHocKy = new ArrayList<>();

        // Đợi các option hiển thị
        page.waitForSelector("div.ng-option");

        List<ElementHandle> options = page.querySelectorAll("div.ng-option");
        for (ElementHandle option : options) {
            String hocKyTxt = option.innerHTML().trim();
            dsHocKy.add(hocKyTxt);
        }

        // Hiển thị danh sách học kỳ
        System.out.println("Danh sách học kỳ:");
        int stt = 1;
        for (String hocKy : dsHocKy) {
            String hk = hocKy.replace("<!---->", "").trim();
            System.out.println(stt + ". " + hk);
            mapHocKy.put(stt, hk);
            stt++;
        }

        // Chọn học kỳ
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào học kỳ muốn xem: ");
        int chon = sc.nextInt();

        if (chon <= 0 || chon > mapHocKy.size()) {
            System.out.println("Dữ liệu nhập vào không hợp lệ!");
            return;
        }

        // Click lại combobox
        System.out.println("Đang mở lại combobox để chọn học kỳ...");
        page.waitForSelector("div[role='option']:has-text('" + mapHocKy.get(chon) + "')");
        page.click("div[role='option']:has-text('" + mapHocKy.get(chon) + "')");

        //doi bang du lieu tai
        page.waitForSelector("table.table");
        page.waitForTimeout(5000);
    }


}
