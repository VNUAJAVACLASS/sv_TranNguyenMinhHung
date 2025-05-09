package dev.backend.webbanthucung;

public class LichHoc {
    private String maMH;
    private String tenMH;
    private Thu thu;
    private TietHoc tietBatDau;
    private String phong;

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

    public Thu getThu() {
        return thu;
    }

    public void setThu(Thu thu) {
        this.thu = thu;
    }

    public TietHoc getTietBatDau() {
        return tietBatDau;
    }

    public void setTietBatDau(TietHoc tietBatDau) {
        this.tietBatDau = tietBatDau;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }
}
