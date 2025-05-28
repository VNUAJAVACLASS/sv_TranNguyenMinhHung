package dev.backend.playwright.service;

import dev.backend.playwright.entities.LichHoc;

import java.io.IOException;

public interface ReadHTMLService {
    void docFileHTML(String path);
    void docFileHTMLGV(String path);
    void hienThiLicHocCaTuan(int soTuan);
    void hienThiLichHocTheoTuanVaThu(int soTuan, int soThu);
    void hienThiLichHocHomNay();
    void hienThiLichHocTheoNgay(String ngay);
}
