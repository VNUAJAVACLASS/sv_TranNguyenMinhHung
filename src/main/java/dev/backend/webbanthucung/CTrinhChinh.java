package dev.backend.webbanthucung;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CTrinhChinh {
    private Set<Tuan> dsTuan = new HashSet<>();
    private final LocalDate ngayBatDauHK2 = LocalDate.of(2025, 1, 13);
    private String maMHTG, tenMHTG; // Biến trung gian lưu thông tin môn học khi thiếu cột

    public void themTuan(Tuan tuan) {
        dsTuan.add(tuan);
    }

    public Set<Tuan> getDsTuan() {
        return dsTuan;
    }

    public void docFileHTML(String path) throws IOException {
        File file = new File(path);
        Document doc = Jsoup.parse(file, "UTF-8");

        Element table = doc.select("table").first();
        Elements rows = table.select("tr");

        for (Element row : rows) {
            Elements cols = row.select("td");
            if (cols.size() < 7) continue; // Bỏ qua dòng thiếu nhiều dữ liệu

            String maMH = cols.get(0).text();
            String tenMH = cols.get(1).text();

            String thuStr = cols.get(cols.size() - 10).text();
            String tiet = cols.get(cols.size() - 9).text();
            int soTiet = Integer.parseInt(cols.get(cols.size() - 8).text());
            String phong = cols.get(cols.size() - 7).text();
            String tuanStr = cols.get(cols.size() - 5).text();

            int soThu = thuStr.equalsIgnoreCase("CN") ? 8 : Integer.parseInt(thuStr);
            Thu thu = new Thu(soThu);
            TietHoc tietBatDau = TietHoc.fromTiet(tiet);

            LichHoc lich;

            if (cols.size() < 11) {
                // Dòng bị thiếu mã MH hoặc tên MH: dùng dữ liệu lưu tạm
                lich = new LichHoc(maMHTG, tenMHTG, thu, tietBatDau, soTiet, phong);
            } else {
                // Dòng đầy đủ: lưu lại thông tin mới
                this.maMHTG = maMH;
                this.tenMHTG = tenMH;
                lich = new LichHoc(maMH, tenMH, thu, tietBatDau, soTiet, phong);
            }

            xuLyLichHoc(tuanStr, soThu, lich);
        }
    }

    public void xuLyLichHoc(String tuanStr, int soThu, LichHoc lichHoc) {
        for (int i = 0; i < tuanStr.length(); i++) {
            char c = tuanStr.charAt(i);
            if (c == '-') continue;

            int soTuan = i + 1;
            Tuan tuan = new Tuan(soTuan);
            boolean daCoTuan = false;

            for (Tuan t : dsTuan) {
                if (t.equals(tuan)) {
                    daCoTuan = true;
                    boolean daCoThu = false;

                    for (Thu thu : t.getDsThu()) {
                        if (thu.getThu() == soThu) {
                            thu.themLichHoc(lichHoc);
                            daCoThu = true;
                            break;
                        }
                    }

                    if (!daCoThu) {
                        Thu thuMoi = new Thu(soThu);
                        thuMoi.themLichHoc(lichHoc);
                        t.themThu(thuMoi);
                    }

                    break;
                }
            }

            if (!daCoTuan) {
                Thu thuMoi = new Thu(soThu);
                thuMoi.themLichHoc(lichHoc);
                tuan.themThu(thuMoi);
                themTuan(tuan);
            }
        }
    }

    public void inTuan() {
        if (dsTuan.isEmpty()) {
            System.out.println("Chưa có tuần nào được thêm!");
            return;
        }

        List<Tuan> danhSachTuan = new ArrayList<>(dsTuan);
        danhSachTuan.sort(Comparator.comparingInt(Tuan::getSoTuan));

        for (Tuan tuan : danhSachTuan) {
            System.out.println("Tuần " + tuan.getSoTuan() + ":");

            List<Thu> dsThu = tuan.getDsThu();
            dsThu.sort(Comparator.comparingInt(Thu::getThu));

            for (Thu thu : dsThu) {
                System.out.println("  Thứ " + (thu.getThu() == 8 ? "CN" : thu.getThu()) + ":");

                List<LichHoc> dsLich = thu.getDsLichHoc();
                dsLich.sort(Comparator.comparing(l -> l.getTietBatDau().getTiet()));

                for (LichHoc lich : dsLich) {
                    int tietBatDau = Integer.parseInt(lich.getTietBatDau().getTiet());
                    int tietKetThuc = tietBatDau + lich.getSoTiet() - 1;

                    System.out.println("    - " + lich.getTenMH() +
                            " (" + lich.getMaMH() + "), Tiết " + tietBatDau +
                            " -> " + tietKetThuc +
                            ", Phòng: " + lich.getPhong());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CTrinhChinh chinh = new CTrinhChinh();
        chinh.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/tkb_mhung.html");
        chinh.inTuan();
    }
}
