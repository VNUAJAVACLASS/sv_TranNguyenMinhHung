package dev.backend.webbanthucung;

public class LichHoc {
    private String maMH;
    private String tenMH;
    private Thu thu;
    private TietHoc tietBatDau;
    private TietHoc soTiet;
    private String phong;

    public LichHoc() {}

    public LichHoc(String maMH, String tenMH, Thu thu, TietHoc tietBatDau, TietHoc soTiet, String phong) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.thu = thu;
        this.tietBatDau = tietBatDau;
        this.soTiet = soTiet;
        this.phong = phong;
    }

    @Override
    public String toString() {
        return "Thứ: "+ thu.getThu() + "Mã môn học: " + maMH + "Tên môn học: " + tenMH +
                "Tiết bắt đầu: " + tietBatDau + "Số tiết: " + soTiet + "Phòng: " + phong + "\n";
    }

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

    public TietHoc getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(TietHoc soTiet) {
        this.soTiet = soTiet;
    }
}
