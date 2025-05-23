package dev.backend.playwright.service;

import dev.backend.playwright.entities.LichHoc;

import java.io.IOException;

public interface ReadHTMLService {
    void docFileHTML(String path) throws IOException;
    void docFileHTMLGV(String path) throws IOException;
    void xuLyLichHoc(String tuanStr, int soThu, LichHoc lichHoc);
    void hienThiLicHocCaTuan(int soTuan);
    void hienThiLichHocTheoTuanVaThu(int soTuan, int soThu);
    void hienThiLichHocHomNay();
    void hienThiLichHocTheoNgay(String ngay);
}
