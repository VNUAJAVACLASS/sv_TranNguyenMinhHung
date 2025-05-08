package dev.backend.webbanthucung;

import java.util.List;

class LichHoc {
    private String maMH;
    private String tenMH;
    private String nhomTo;
    private int soTinChi;
    private String lop;
    private int thu;
    private int tietBatDau;
    private int soTiet;
    private String phong;
    private String giangVien;
    private String tuanHoc;

    public LichHoc(String maMH, String tenMH, String nhomTo, int soTinChi, String lop,
                   int thu, int tietBatDau, int soTiet, String phong, String giangVien, String tuanHoc) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.nhomTo = nhomTo;
        this.soTinChi = soTinChi;
        this.lop = lop;
        this.thu = thu;
        this.tietBatDau = tietBatDau;
        this.soTiet = soTiet;
        this.phong = phong;
        this.giangVien = giangVien;
        this.tuanHoc = tuanHoc;
    }

    // Getter methods
    public String getMaMH() { return maMH; }
    public String getTenMH() { return tenMH; }
    public String getNhomTo() { return nhomTo; }
    public int getSoTinChi() { return soTinChi; }
    public String getLop() { return lop; }
    public int getThu() { return thu; }
    public int getTietBatDau() { return tietBatDau; }
    public int getSoTiet() { return soTiet; }
    public String getPhong() { return phong; }
    public String getGiangVien() { return giangVien; }
    public String getTuanHoc() { return tuanHoc; }

    @Override
    public String toString() {
        return String.format("%s - %s\nPhòng: %s, Tiết %d-%d (%s)\nGV: %s | Nhóm: %s | Lớp: %s",
                maMH, tenMH, phong, tietBatDau, tietBatDau + soTiet - 1,
                TietHoc.getThoiGian(String.valueOf(tietBatDau)), giangVien, nhomTo, lop);
    }
}