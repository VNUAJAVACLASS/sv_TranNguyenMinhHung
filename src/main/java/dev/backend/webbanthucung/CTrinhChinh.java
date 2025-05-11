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

            // Duyệt ngược về để tách chuỗi
            for (int i = cols.size() - 1; i > 0; i--) {
                String maMH = cols.get(0).text();
                String tenMH = cols.get(1).text();

                String tuanStr = cols.get(cols.size() - 5).text();
                String thuStr = cols.get(cols.size() - 10).text();

                String tiet = cols.get(cols.size() - 9).text();
                int soTiet =  Integer.parseInt(cols.get(cols.size() - 8).text());
                String phong =  cols.get(cols.size() - 7).text();

                TietHoc tietBatDau = TietHoc.fromTiet(tiet);


                int soThu;
                if (thuStr.equalsIgnoreCase("CN")) {
                    soThu = 8;
                }else{
                    soThu = Integer.parseInt(thuStr);
                }
                Thu thu = new Thu(soThu);

                LichHoc lich = new LichHoc(maMH, tenMH, thu, tietBatDau, soTiet, phong);

                xuLyLichHoc(tuanStr, thuStr, lich);

            }
        }
    }

    public void xuLyTuan(String tuanStr, String thuStr) {
        int soThu;
        if (thuStr.equalsIgnoreCase("CN")) {
            soThu = 8;
        } else {
            soThu = Integer.parseInt(thuStr);
        }
        Thu thu = new Thu(soThu);

        for (int i = 0; i < tuanStr.length(); i++) {
            char c = tuanStr.charAt(i);
            int soTuan = i + 1;

            if (c != '-') {
                // Tạo đối tượng Tuan cho tuần hiện tại
                Tuan tuan = new Tuan(soTuan);

                // Tìm tuần đã có trong dsTuan
                if (!dsTuan.contains(tuan)) {
                    // Nếu tuần chưa có trong danh sách, thêm tuần mới và thêm Thu
                    tuan.themThu(thu);
                    themTuan(tuan);
                } else {
                    for(Tuan t:dsTuan){
                        if(t.equals(tuan)){
                            //Kiểm tra thứ chưa có trong tuần
                            if (!t.getDsThu().contains(thu)) {
                                t.themThu(thu);
                            }
                        }
                    }
                }
            }
        }
    }

    public void xuLyLichHoc(String tuanStr, String thuStr, LichHoc lichHoc) {
        int soThu;
        if (thuStr.equalsIgnoreCase("CN")) {
            soThu = 8;
        }else{
            soThu = Integer.parseInt(thuStr);
        }

        Thu thu = new Thu(soThu);
        thu.themLichHoc(lichHoc);

        for(int i = 0; i < tuanStr.length(); i++) {
            char c = tuanStr.charAt(i);
            int soTuan = i + 1;

            if(c != '-'){
                Tuan tuan = new Tuan(soTuan);

                boolean daCoTuan = false;
                for(Tuan t:dsTuan){
                    if(t.equals(tuan)){
                        daCoTuan = true;

                        boolean daCoThu = false;
                        for(Thu thuCu: t.getDsThu()){
                            if(thuCu.equals(thu)){
                                thu.themLichHoc(lichHoc);
                                daCoThu = true;
                                break;
                            }
                        }

                        if(!daCoThu){
                            t.themThu(thu);
                        }
                        break;
                    }
                }

                if(!daCoTuan){
                    tuan.themThu(thu);
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

        // Sắp xếp theo số tuần tăng dần
        List<Tuan> danhSachTuan = new ArrayList<>(dsTuan);
        danhSachTuan.sort(Comparator.comparingInt(Tuan::getSoTuan));

        for (Tuan tuan : danhSachTuan) {
            System.out.println("Tuần " + tuan.getSoTuan() + ":");

            List<Thu> dsThu = tuan.getDsThu();
            dsThu.sort(Comparator.comparingInt(Thu::getThu)); // Sắp xếp thứ tăng dần

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
//        chinh.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/tkb_TranNguyenMinhHung.html");
        chinh.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/tkb_mhung.html");


        chinh.inTuan();
    }
}
