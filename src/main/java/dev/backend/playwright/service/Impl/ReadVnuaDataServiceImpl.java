package dev.backend.playwright.service.Impl;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import dev.backend.playwright.entities.NguoiDung;
import dev.backend.playwright.service.ReadVnuaDataService;

public class ReadVnuaDataServiceImpl implements ReadVnuaDataService {
    private static final String url = "https://daotao.vnua.edu.vn/#/home";
    private NguoiDung nd = new NguoiDung();
    private Playwright playwright;
    private Browser browser;
    private Page page;

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
        page.waitForTimeout(10000);

    }

    public void layDuLieuLichHoc() {
        if(page == null){
            System.out.println("Bạn cần đăng nhập trước khi truy cập thời khóa biểu!");
            return;
        }

        //Click vào thời khóa biểu dạng học kỳ
        page.click("#WEB_TKB_HK");
    }
}
