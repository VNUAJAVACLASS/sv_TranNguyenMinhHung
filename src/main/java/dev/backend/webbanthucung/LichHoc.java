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
    private List<Integer> tuanHoc;

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getNhomTo() {
        return nhomTo;
    }

    public void setNhomTo(String nhomTo) {
        this.nhomTo = nhomTo;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public int getTietBatDau() {
        return tietBatDau;
    }

    public void setTietBatDau(int tietBatDau) {
        this.tietBatDau = tietBatDau;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
    }

    public List<Integer> getTuanHoc() {
        return tuanHoc;
    }

    public void setTuanHoc(List<Integer> tuanHoc) {
        this.tuanHoc = tuanHoc;
    }

    @Override
    public String toString() {
        return "LichHoc{" +
                "maMH='" + maMH + '\'' +
                ", tenMH='" + tenMH + '\'' +
                ", nhomTo='" + nhomTo + '\'' +
                ", soTinChi=" + soTinChi +
                ", lop='" + lop + '\'' +
                ", thu=" + thu +
                ", tietBatDau=" + tietBatDau +
                ", soTiet=" + soTiet +
                ", phong='" + phong + '\'' +
                ", giangVien='" + giangVien + '\'' +
                ", tuanHoc=" + tuanHoc +
                '}';
    }
}