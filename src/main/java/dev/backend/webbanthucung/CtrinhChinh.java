package dev.backend.webbanthucung;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class CtrinhChinh {
    private HashMap<Integer, Tuan> dsTuan = new HashMap<>();
    private LocalDate ngayBatDauHK2 = LocalDate.of(2025, 1, 13);

    public void docFileHTML(String fileName) throws IOException {
        File file = new File(fileName);
        Document doc = Jsoup.parse(file, "UTF-8");

        Element table = doc.getElementsByTag("table").first();

        Elements rows = table.select("tr");

        if (!rows.isEmpty()) {

            Element firstRow = rows.get(3);  // Dòng đầu tiên
            Elements cols = firstRow.select("td"); // Lấy các cột (ô) trong dòng

            // In ra từng ô
            for (Element cell : cols) {
                System.out.print(cell.text() + " "); // In ra nội dung văn bản của ô
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws IOException {
        CtrinhChinh chinh = new CtrinhChinh();
        chinh.docFileHTML("D:/BTL_XayDung&PTPhanMem/Challenge/src/main/java/dev/backend/webbanthucung/tkb_TranNguyenMinhHung.html");
    }
}