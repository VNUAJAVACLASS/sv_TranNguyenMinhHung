package dev.backend.webbanthucung;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

class CtrinhChinh {
    private Map<Integer, Tuan> dsTuan;
    private static LocalDate ngayBatDauHK = LocalDate.of(2025, 1, 13);
    private static LocalDate ngayKetThucHK = LocalDate.of(2025, 6, 29);
    private List<Tuan> qlTuanHoc;
    private Set<Integer> dsTuanDaThem;

    public CtrinhChinh() {
        this.dsTuan = new HashMap<>();
        this.qlTuanHoc = new ArrayList<>();
        this.dsTuanDaThem = new HashSet<>();
    }

    public void themTuanHoc(int tuan) {
        // Kiểm tra xem tuần đã có trong danh sách chưa
        if (!dsTuanDaThem.contains(tuan) && tuan <= tinhTuanHoc()) {
            qlTuanHoc.add(new Tuan(tuan));
            dsTuanDaThem.add(tuan);
            System.out.println("Đã thêm tuần: " + tuan);
        }
    }

    public void docFileHTML(String path) throws IOException {
        File file = new File(path);
        Document doc = Jsoup.parse(file, "UTF-8");

        Element table = doc.select("table").first();
        Elements rows = table.select("tr");

        for (Element row : rows) {
            Elements cols = row.select("td");

            // Duyệt ngược về để tách chuỗi
            for (int i = cols.size() - 1; i > 0; i--) {
                String tuan = cols.get(cols.size() - 5).text();
                xuLyTuan(tuan);
            }
        }
    }

    private int tinhTuanHoc() {
        long ngayOGiua = ChronoUnit.DAYS.between(ngayBatDauHK, ngayKetThucHK);
        return (int) (ngayOGiua / 7) + 1;
    }

    public void xuLyTuan(String str) {
        int dem = 1;

        for (char c : str.toCharArray()) {
            int soTuan;
            if (c == '0') {
                soTuan = 10 + dem;
            } else {
                soTuan = dem++;
            }
            // Thêm tuần vào danh sách nếu chưa có
            themTuanHoc(soTuan);
        }
    }

    public static void main(String[] args) throws IOException {
        CtrinhChinh chinh = new CtrinhChinh();
        chinh.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/tkb_TranNguyenMinhHung.html");

        System.out.println("Tổng số tuần học trong học kỳ: " + chinh.tinhTuanHoc());
    }
}
