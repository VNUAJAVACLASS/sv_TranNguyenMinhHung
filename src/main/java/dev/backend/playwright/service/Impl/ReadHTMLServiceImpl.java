package dev.backend.playwright.service.Impl;

import dev.backend.playwright.entities.LichHoc;
import dev.backend.playwright.entities.Thu;
import dev.backend.playwright.entities.TietHoc;
import dev.backend.playwright.entities.Tuan;
import dev.backend.playwright.service.ReadHTMLService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ReadHTMLServiceImpl implements ReadHTMLService {
    private Map<Integer, Tuan> dsTuan = new HashMap<>();
    private final LocalDate ngayBatDauHK2 = LocalDate.of(2025, 1, 13);
    private String maMHTG, tenMHTG; // Biến trung gian lưu thông tin môn học khi thiếu cột

    public ReadHTMLServiceImpl() {}

    public void docFileHTML(String path) throws IOException {
        File file = new File(path);
        Document doc = org.jsoup.Jsoup.parse(file, "UTF-8");

        Element table = doc.select("table").first();
        //lấy từng dòng
        Elements rows = table.select("tr");

        for (Element row : rows) {
            Elements cols = row.select("td");

            if (cols.size() < 7) continue;

            String maMH, tenMH;

            //Nếu dòng thiếu thông tin mã, tên môn thì lưu vào biến tg
            if (cols.size() < 11) {
                maMH = this.maMHTG;
                tenMH = this.tenMHTG;
            } else {
                maMH = cols.get(0).text();
                tenMH = cols.get(1).text();
                this.maMHTG = maMH;
                this.tenMHTG = tenMH;
            }

            //lấy thông tin tương ứng
            String thuStr = cols.get(cols.size() - 10).text();
            String tiet = cols.get(cols.size() - 9).text();
            int soTiet = Integer.parseInt(cols.get(cols.size() - 8).text());
            String phong = cols.get(cols.size() - 7).text();
            String tuanStr = cols.get(cols.size() - 5).text();


            int soThu = thuStr.equalsIgnoreCase("CN") ? 8 : Integer.parseInt(thuStr);

            TietHoc tietBatDau = TietHoc.fromTiet(tiet);

            LichHoc lich = new LichHoc(maMH, tenMH, null, tietBatDau, soTiet, phong);

            xuLyLichHoc(tuanStr, soThu, lich);
        }
    }

    public void docFileHTMLGV(String path) throws IOException {
        File file = new File(path);
        Document doc = org.jsoup.Jsoup.parse(file, "UTF-8");

        Element table = doc.select("table").first();
        Elements rows = table.select("tr");

        //lấy từng dòng
        for (Element row : rows) {
            Elements cols = row.select("td");

            if (cols.size() < 7) continue;

            String maMH, tenMH;

            //Nếu dòng thiếu thông tin mã, tên môn thì lưu vào biết tg
            if (cols.size() < 11) {
                maMH = this.maMHTG;
                tenMH = this.tenMHTG;
            } else {
                maMH = cols.get(0).text();
                tenMH = cols.get(1).text();
                this.maMHTG = maMH;
                this.tenMHTG = tenMH;
            }

            //lấy thông tin tương ứng
            String thuStr = cols.get(cols.size() - 12).text();
            String tiet = cols.get(cols.size() - 11).text();
            int soTiet = Integer.parseInt(cols.get(cols.size() - 10).text());
            String phong = cols.get(cols.size() - 9).text();
            String tuanStr = cols.get(cols.size() - 6).text();

            if(thuStr == "") continue;
            int soThu = thuStr.equalsIgnoreCase("CN") ? 8 : Integer.parseInt(thuStr);

            TietHoc tietBatDau = TietHoc.fromTiet(tiet);

            LichHoc lich = new LichHoc(maMH, tenMH, null, tietBatDau, soTiet, phong);

            xuLyLichHoc(tuanStr, soThu, lich);
        }
    }

    public void xuLyLichHoc(String tuanStr, int soThu, LichHoc lichHoc) {
        for (int i = 0; i < tuanStr.length(); i++) {
            char c = tuanStr.charAt(i);
            if (c == '-') continue;

            int soTuan = i + 1;

            // Kiểm tra xem tuần đã có trong HashMap chưa
            Tuan tuanHienTai = dsTuan.get(soTuan);

            // Nếu tuần chưa tồn tại, tạo mới và thêm vào HashMap
            if (tuanHienTai == null) {
                tuanHienTai = new Tuan(soTuan);
                dsTuan.put(soTuan, tuanHienTai);
            }

            // Tìm hoặc tạo đối tượng Thu trong tuần hiện tại
            Thu thuHienTai = null;
            for (Thu thu : tuanHienTai.getDsThu()) {
                if (thu.getThu() == soThu) {
                    thuHienTai = thu;
                    break;
                }
            }

            // Nếu thứ chưa có, tạo mới và thêm vào danh sách các ngày trong tuần
            if (thuHienTai == null) {
                thuHienTai = new Thu(soThu);
                tuanHienTai.themThu(thuHienTai);
            }

            // Thêm lịch học vào thứ hiện tại
            lichHoc.setThu(thuHienTai);
            thuHienTai.themLichHoc(lichHoc);
        }
    }

    public void hienThiLicHocCaTuan(int soTuan) {
        if (soTuan < 1 || soTuan > 22) {
            System.out.println("Chỉ số tuần không hợp lệ. Vui lòng nhập từ 1 đến 22.");
            return;
        }

        Tuan tuan = dsTuan.get(soTuan);

        if (tuan == null) { // Kiểm tra xem tuần có tồn tại trong HashMap không
            System.out.println("Không có dữ liệu thời khóa biểu cho tuần " + soTuan);
            return;
        }

        System.out.println("Thời khóa biểu tuần " + soTuan + ":");

        List<Thu> dsThu = new ArrayList<>(tuan.getDsThu());
        dsThu.sort(Comparator.comparingInt(Thu::getThu));

        for (Thu thu : dsThu) {
            System.out.println("  Thứ " + (thu.getThu() == 8 ? "CN" : thu.getThu()) + ":");

            List<LichHoc> dsLich = new ArrayList<>(thu.getDsLichHoc());

            for (LichHoc lich : dsLich) {
                int tietBD = Integer.parseInt(lich.getTietBatDau().getTiet());
                int tietKT = tietBD + lich.getSoTiet() - 1;
                System.out.println("    - " + lich.getTenMH() + " (" + lich.getMaMH() +
                        "), Tiết " + tietBD + " -> " + tietKT + ", Phòng: " + lich.getPhong());
            }
        }
    }

    public void hienThiLichHocTheoTuanVaThu(int soTuan, int soThu) {
        if (soTuan < 1 || soTuan > 22) {
            System.out.println("Tuần không hợp lệ. Vui lòng nhập từ 1 đến 22.");
            return;
        }

        if (soThu < 2 || soThu > 8) {
            System.out.println("Thứ không hợp lệ. Vui lòng nhập từ 2 đến 7 (hoặc 8 cho CN).");
            return;
        }

        Tuan tuan = dsTuan.get(soTuan);

        if (tuan == null) { // Kiểm tra xem tuần có tồn tại không
            System.out.println("Không có dữ liệu cho tuần " + soTuan);
            return;
        }

        // Tìm thứ trong tuần
        Optional<Thu> optionalThu = tuan.getDsThu().stream()
                .filter(thu -> thu.getThu() == soThu)
                .findFirst();

        if (optionalThu.isEmpty()) {
            System.out.println("Không có lịch học cho tuần " + soTuan + ", thứ " + (soThu == 8 ? "CN" : soThu));
            return;
        }

        Thu thu = optionalThu.get();
        List<LichHoc> dsLich = new ArrayList<>(thu.getDsLichHoc());

        System.out.println("Thời khóa biểu tuần " + soTuan + ", thứ " + (soThu == 8 ? "CN" : soThu) + ":");

        for (LichHoc lich : dsLich) {
            int tietBD = Integer.parseInt(lich.getTietBatDau().getTiet());
            int tietKT = tietBD + lich.getSoTiet() - 1;
            System.out.println("  - " + lich.getTenMH() + " (" + lich.getMaMH() + "), Tiết " +
                    tietBD + " -> " + tietKT + ", Phòng: " + lich.getPhong());
        }
    }

    public void hienThiLichHocHomNay() {
        LocalDate homNay = LocalDate.now();

        long soNgay = ChronoUnit.DAYS.between(ngayBatDauHK2, homNay);

        if (soNgay < 0) {
            System.out.println("Kỳ học này chưa bắt đầu");
            return;
        }

        int soTuanHienTai = (int) (soNgay / 7) + 1;
        int soThuTrongTuan = homNay.getDayOfWeek().getValue();
        int soThu = (soThuTrongTuan == 7) ? 8 : soThuTrongTuan + 1;

        System.out.println("Hôm nay là: " + homNay + " (Tuần " + soTuanHienTai + ", Thứ " + (soThu == 8 ? "CN" : soThu) + ")");

        // Sử dụng get() để truy cập trực tiếp tuần hiện tại
        Tuan tuanHienTai = dsTuan.get(soTuanHienTai);

        if (tuanHienTai == null) {
            System.out.println("Không có dữ liệu thời khóa biểu cho tuần " + soTuanHienTai);
            return;
        }

        // Tìm thứ trong tuần hiện tại
        Optional<Thu> optionalThu = tuanHienTai.getDsThu().stream()
                .filter(thu -> thu.getThu() == soThu)
                .findFirst();

        if (optionalThu.isEmpty()) {
            System.out.println("Không có lịch học vào hôm nay.");
            return;
        }

        Thu thuHomNay = optionalThu.get();
        List<LichHoc> dsLich = new ArrayList<>(thuHomNay.getDsLichHoc());

        for (LichHoc lich : dsLich) {
            int tietBatDau = Integer.parseInt(lich.getTietBatDau().getTiet());
            int tietKetThuc = tietBatDau + lich.getSoTiet() - 1;
            System.out.println(" - " + lich.getTenMH() + " (" + lich.getMaMH() +
                    "), Tiết " + tietBatDau + " -> " + tietKetThuc + ", Phòng: " + lich.getPhong());
        }
    }

    public void hienThiLichHocTheoNgay(String ngay) {
        if (ngay == null || ngay.isBlank()) {
            System.out.println("Ngày không hợp lệ hoặc bị bỏ trống.");
            return;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ngayNhap = LocalDate.parse(ngay, dtf);

        long soNgay = ChronoUnit.DAYS.between(ngayBatDauHK2,ngayNhap);

        if (soNgay < 0) {
            System.out.println("Kỳ học này chưa bắt đầu");
            return;
        }

        int soTuanHienTai = (int) (soNgay / 7) + 1;
        int soThuTrongTuan = ngayNhap.getDayOfWeek().getValue();
        int soThu = (soThuTrongTuan == 7) ? 8 : soThuTrongTuan + 1;

        System.out.println("Hôm nay là: " + ngayNhap + " (Tuần " + soTuanHienTai + ", Thứ " + (soThu == 8 ? "CN" : soThu) + ")");

        // Sử dụng get() để truy cập trực tiếp tuần hiện tại
        Tuan tuanHienTai = dsTuan.get(soTuanHienTai);

        if (tuanHienTai == null) {
            System.out.println("Không có dữ liệu thời khóa biểu cho tuần " + soTuanHienTai);
            return;
        }

        // Tìm thứ trong tuần hiện tại
        Optional<Thu> optionalThu = tuanHienTai.getDsThu().stream()
                .filter(thu -> thu.getThu() == soThu)
                .findFirst();

        if (optionalThu.isEmpty()) {
            System.out.println("Không có lịch học vào hôm nay.");
            return;
        }

        Thu thuHomNay = optionalThu.get();
        List<LichHoc> dsLich = new ArrayList<>(thuHomNay.getDsLichHoc());

        for (LichHoc lich : dsLich) {
            int tietBatDau = Integer.parseInt(lich.getTietBatDau().getTiet());
            int tietKetThuc = tietBatDau + lich.getSoTiet() - 1;
            System.out.println(" - " + lich.getTenMH() + " (" + lich.getMaMH() +
                    "), Tiết " + tietBatDau + " -> " + tietKetThuc + ", Phòng: " + lich.getPhong());
        }
    }

}
