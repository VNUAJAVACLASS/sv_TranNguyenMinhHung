package dev.backend.playwright.service.Impl;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import dev.backend.playwright.entities.NguoiDung;
import dev.backend.playwright.service.ReadVnuaDataService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadVnuaDataServiceImpl implements ReadVnuaDataService {
    private static final String url = "https://daotao.vnua.edu.vn/#/home";
    private NguoiDung nd;
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private Map<Integer, String> mapHocKy = new HashMap<Integer, String>();
    String htmlWrapper;
    private int selectedHocKy = -1;

    public ReadVnuaDataServiceImpl() {
        nd = new NguoiDung();
    }

    public ReadVnuaDataServiceImpl(NguoiDung nd) {
        this.nd = nd;
    }

    public void logIn() {
        System.out.println("\nLoading.......");
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true)
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

    public void readScheduleData(){
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
        selectedHocKy = chon;


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


        //lưu file html
        String html = page.evaluate("document.querySelector('table.table')?.outerHTML").toString();
        if(html != null) {
            String htmlWrapper = """
						    <!DOCTYPE html>
						    <html>
						    <head>
						        <meta charset="UTF-8">
						        <title>Thời khóa biểu</title>
						    </head>
						    <body>
						""" + html + """
						    </body>
						    </html>
						""";
//            System.out.println(htmlWrapper);

            try {
                Path filePath = Paths.get("src/main/resources/tkb.html");
                Files.createDirectories(filePath.getParent());
                Files.writeString(filePath, htmlWrapper);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("Không có lịch học!");
        }
    }

    public void readStartDate() {
        if (page == null || selectedHocKy == -1) {
            System.out.println("Bạn cần đăng nhập và chọn học kỳ trước!");
            return;
        }

        // 1. Click vào tab thời khóa biểu 1 tuần
        String tkb = "#WEB_TKB_1TUAN";
        page.waitForSelector(tkb);
        page.click(tkb);
        page.waitForTimeout(2000);

        // 2. Click combobox học kỳ trong tab này (vị trí index 0)
        Locator comboBoxes = page.locator("div[role='combobox']");
        Locator hocKyComboBox = comboBoxes.nth(0);
        hocKyComboBox.waitFor();
        hocKyComboBox.click();
        page.waitForTimeout(1000);

        // 3. Chọn lại học kỳ đã chọn trước đó
        String hocKyText = mapHocKy.get(selectedHocKy);
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(hocKyText)).click();
        page.waitForTimeout(2000); // đợi dữ liệu cập nhật

        Locator weekDropdown = page.locator(
                "#fullScreen > div.card-body.p-0 > div.row.text-nowrap.px-1.pb-1 > div.d-inline-block.col-lg-7.col-md-12.col-sm-12.mb-1 > ng-select > div > div > div.ng-input"
        );
        weekDropdown.click();

        page.waitForSelector(".ng-dropdown-panel-items.scroll-host");

        page.evaluate("() => document.querySelector('.ng-dropdown-panel-items.scroll-host')?.scrollTo(0, 0)");

        page.waitForTimeout(1000);

        Locator firstOption = page.locator(
                "//div[@class='ng-dropdown-panel-items scroll-host']//div[contains(@class, 'ng-option')][1]"
        );

        firstOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        String weekText = firstOption.textContent();

        if (weekText == null || !weekText.contains("từ ngày")) {
            System.out.println("Không tìm thấy thông tin tuần hợp lệ.");
            return;
        }

        // 6. Trích xuất ngày bắt đầu
        Matcher matcher = Pattern.compile("từ ngày (\\d{2}/\\d{2}/\\d{4})").matcher(weekText);
        if (matcher.find()) {
            String startDate = matcher.group(1);
            System.out.println("📅 Ngày bắt đầu học kỳ là: " + startDate);

            //thêm ngày bắt đầu vào để đọc lịch học
            String[] parts = startDate.split("/");

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            String txt = year + "\n" + month + "\n" + day;
            try {
                Path filePath = Paths.get("src/main/resources/start_date.txt");
                Files.createDirectories(filePath.getParent());
                Files.writeString(filePath, txt);
                System.out.println("Đã ghi vào tệp");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Không trích xuất được ngày bắt đầu từ chuỗi tuần.");
        }
    }

    public void runApp() {
        try {
            logIn();

            readScheduleData();

            readStartDate();
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            if (page != null) page.close();
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        }

    }

}
