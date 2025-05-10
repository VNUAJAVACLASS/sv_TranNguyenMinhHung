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
                String tuanStr = cols.get(cols.size() - 5).text();
                String thuStr = cols.get(cols.size() - 10).text();

                xuLyTuan(tuanStr, thuStr);
            }
        }
    }

    public void xuLyTuan(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int soTuan = i + 1;

            if (c != '-') {
                Tuan tuan = new Tuan(soTuan);
                if (!dsTuan.contains(tuan)) {
                    themTuan(tuan);
                } else {
//                    System.out.println("Tuần " + soTuan + " đã tồn tại, bỏ qua.");
                }
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
                            if (!t.getDsThu().contains(thu)) {
                                t.themThu(thu);
                            }
//                            break;
                        }
                    }
                }
            }
        }
    }




    public void inTuan() {
        if (dsTuan.isEmpty()) {
            System.out.println("Chua co tuan nao duoc them!");
            return;
        }

        for(Tuan tuan : dsTuan) {
            System.out.print(tuan.getSoTuan() + " ");
        }
    }

    public void inTuan1() {
        if (dsTuan.isEmpty()) {
            System.out.println("Chưa có tuần nào được thêm!");
            return;
        }

        for (Tuan tuan : dsTuan) {
            System.out.print("Tuần " + tuan.getSoTuan() + ": ");

            List<Thu> dsThu = tuan.getDsThu();
            List<Integer> thuDaThem = new ArrayList<>();

            // Chỉ thêm thứ vào danh sách thuDaThem mà không cần kiểm tra LichHoc
            for (Thu thu : dsThu) {
                thuDaThem.add(thu.getThu());  // Thêm thứ vào danh sách thuDaThem
            }

            if (thuDaThem.isEmpty()) {
                System.out.println("Chưa có thứ nào được thêm.");
            } else {
                System.out.println("Thứ " + thuDaThem);
            }
        }
    }



    public static void main(String[] args) throws IOException {
        CTrinhChinh chinh = new CTrinhChinh();
//        chinh.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/tkb_TranNguyenMinhHung.html");
        chinh.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/tkb_mhung.html");

        chinh.inTuan1();
    }
}
