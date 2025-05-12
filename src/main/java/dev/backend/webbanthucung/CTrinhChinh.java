package dev.backend.webbanthucung;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CTrinhChinh {
    private Set<Tuan> dsTuan = new HashSet<>();
    private final LocalDate ngayBatDauHK2 = LocalDate.of(2025, 1, 13);
    private String maMHTG, tenMHTG; // Biến trung gian lưu thông tin môn học khi thiếu cột

    public void themTuan(Tuan tuan) {
        dsTuan.add(tuan);
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

            //Kiem tra xem tuan da co trong danh sach hay chua
            for (Tuan t : dsTuan) {
                if (t.equals(tuan)) {
                    daCoTuan = true;
                    boolean daCoThu = false;

                    //Kiem tra xem thu da co trong tuan hay chua
                    for (Thu thu : t.getDsThu()) {
                        if (thu.getThu() == soThu) {
                            thu.themLichHoc(lichHoc);
                            daCoThu = true;
                            break;
                        }
                    }

                    //Neu thu chua co, tao moi va them lich hoc
                    if (!daCoThu) {
                        Thu thuMoi = new Thu(soThu);
                        thuMoi.themLichHoc(lichHoc);
                        t.themThu(thuMoi);
                    }
                    break;
                }

                //Neu chua co tuan, tao moi tuan va them lich hoc
                if (!daCoTuan) {
                    Thu thuMoi = new Thu(soThu);
                    thuMoi.themLichHoc(lichHoc);
                    tuan.themThu(thuMoi);
                    themTuan(tuan);
                }
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

    public void hienThiLichHocHomNay() {
        LocalDate homNay = LocalDate.now();

        long soNgay = ChronoUnit.DAYS.between(ngayBatDauHK2, homNay);

        if (soNgay < 0) {
            System.out.println("Kỳ học này chưa bắt đầu");
        }

        int soTuanHienTai = (int) (soNgay / 7) + 1;
        int soThuTrongTuan = homNay.getDayOfWeek().getValue();
        int soThu = (soThuTrongTuan == 7) ? 8 : soThuTrongTuan + 1;

        System.out.println("Hôm nay là: " + homNay + " (Tuần " + soTuanHienTai + ", Thứ " + (soThu == 8 ? "CN" : soThu) + ")");

        //tìm tuần hiện tại
        for (Tuan tuan : dsTuan) {
            if (tuan.getSoTuan() == soTuanHienTai) {
                for (Thu thu : tuan.getDsThu()) {
                    if (thu.getThu() == soThu) {
                        List<LichHoc> dsLich = thu.getDsLichHoc();
                        dsLich.sort(Comparator.comparing(l -> l.getTietBatDau().getTiet()));

                        for (LichHoc lich : dsLich) {
                            int tietBatDau = Integer.parseInt(lich.getTietBatDau().getTiet());
                            int tietKetThuc = tietBatDau + lich.getSoTiet() - 1;
                            System.out.println(" - " + lich.getTenMH() + " (" + lich.getMaMH() +
                                    "), Tiết " + tietBatDau + " -> " + tietKetThuc + ", Phòng: " + lich.getPhong());
                        }
                        return;
                    }
                }
            }
        }
    }

    public void hienThiLicHocCaTuan(int soTuan) {
        if (soTuan < 1 || soTuan > 22) {
            System.out.println("Chỉ số tuần không hợp lệ. Vui lòng nhập từ 1 đến 22.");
            return;
        }

        Optional<Tuan> optionalTuan = dsTuan.stream()
                .filter(t -> t.getSoTuan() == soTuan)
                .findFirst();

        if (optionalTuan.isEmpty()) {
            System.out.println("Không có dữ liệu thời khóa biểu cho tuần " + soTuan);
            return;
        }

        Tuan tuan = optionalTuan.get();
        System.out.println("Thời khóa biểu tuần " + soTuan + ":");

        List<Thu> dsThu = new ArrayList<>(tuan.getDsThu());
        dsThu.sort(Comparator.comparingInt(Thu::getThu));

        for (Thu thu : dsThu) {
            System.out.println("  Thứ " + (thu.getThu() == 8 ? "CN" : thu.getThu()) + ":");

            List<LichHoc> dsLich = new ArrayList<>(thu.getDsLichHoc());
            dsLich.sort(Comparator.comparing(l -> l.getTietBatDau().getTiet()));

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

        Optional<Tuan> optionalTuan = dsTuan.stream()
                .filter(t -> t.getSoTuan() == soTuan)
                .findFirst();

        if (optionalTuan.isEmpty()) {
            System.out.println("Không có dữ liệu cho tuần " + soTuan);
            return;
        }

        Tuan tuan = optionalTuan.get();
        Optional<Thu> optionalThu = tuan.getDsThu().stream()
                .filter(thu -> thu.getThu() == soThu)
                .findFirst();

        if (optionalThu.isEmpty()) {
            System.out.println("Không có lịch học cho tuần " + soTuan + ", thứ " + (soThu == 8 ? "CN" : soThu));
            return;
        }

        Thu thu = optionalThu.get();
        List<LichHoc> dsLich = new ArrayList<>(thu.getDsLichHoc());
        dsLich.sort(Comparator.comparing(l -> l.getTietBatDau().getTiet()));

        System.out.println("Thời khóa biểu tuần " + soTuan + ", thứ " + (soThu == 8 ? "CN" : soThu) + ":");

        for (LichHoc lich : dsLich) {
            int tietBD = Integer.parseInt(lich.getTietBatDau().getTiet());
            int tietKT = tietBD + lich.getSoTiet() - 1;
            System.out.println("  - " + lich.getTenMH() + " (" + lich.getMaMH() + "), Tiết " +
                    tietBD + " -> " + tietKT + ", Phòng: " + lich.getPhong());
        }
    }

    public Date stringToDate(String ngaySinhStr) {
        Date stringToDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            stringToDate = sdf.parse(ngaySinhStr);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Lỗi ngày nhập!");
        }

        return stringToDate;
    }

    public static void main(String[] args) throws IOException {
        CTrinhChinh chinh = new CTrinhChinh();
        chinh.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/tkb_TranNguyenMinhHung.html");

//        chinh.inTuan();
        Scanner sc = new Scanner(System.in);

        System.out.println("\n1. Lịch học hôm nay:");
        chinh.hienThiLichHocHomNay();

        System.out.println("\n===================================================");

        System.out.println("\n2. Xem thời khóa biểu cả tuần:");
        System.out.print("Nhập số tuần cần xem (1-22): ");
        int soTuan = sc.nextInt();
        chinh.hienThiLicHocCaTuan(soTuan);

        System.out.println("\n===================================================");

        System.out.println("\n3. Xem thời khóa biểu theo tuần, thứ:");
        System.out.print("Nhập tuần: ");
        int soTuan1 = sc.nextInt();
        System.out.print("Nhập thứ: ");
        int soThu = sc.nextInt();
        chinh.hienThiLichHocTheoTuanVaThu(soTuan1, soThu);

        System.out.println("\n===================================================");
        System.out.println("\n4. Xem thời khóa biểu theo ngày:");
        System.out.print("Nhập ngày: ");
    }
}
