package dev.backend.webbanthucung;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
                String tuan = cols.get(cols.size() - 5).text();
                xuLyTuan(tuan);
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


    public void inTuan() {
        if (dsTuan.isEmpty()) {
            System.out.println("Chua co tuan nao duoc them!");
            return;
        }

        for(Tuan tuan : dsTuan) {
            System.out.print(tuan.getSoTuan() + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        CTrinhChinh chinh = new CTrinhChinh();
        chinh.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/tkb_TranNguyenMinhHung.html");

        chinh.inTuan();
    }
}
