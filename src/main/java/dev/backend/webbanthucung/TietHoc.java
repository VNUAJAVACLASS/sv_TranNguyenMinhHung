package dev.backend.webbanthucung;

public enum TietHoc {
    TIET_1("1","7:00 - 7:50"),
    TIET_2("2","7:55 - 8:45"),
    TIET_3("3","8:50 - 9:40"),
    TIET_4("4","9:55 - 10:45"),
    TIET_5("5","10:50 - 11:40"),
    TIET_6("6","12:45 - 13:35"),
    TIET_7("7","13:40 - 14:30"),
    TIET_8("8","14:35 - 15:25"),
    TIET_9("9","15:40 - 16:30"),
    TIET_10("10","16:35 - 17:25"),
    TIET_11("11","18:00 - 18:50"),
    TIET_12("12","18:55 - 19:45"),
    TIET_13("13","19:50 - 20:40");

    private final String soTiet;
    private final String thoiGian;

    TietHoc(String soTiet, String thoiGian) {
        this.soTiet = soTiet;
        this.thoiGian = thoiGian;
    }

    public static String getThoiGian(String soTiet) {
        for (TietHoc tiet : values()) {
            if (tiet.soTiet.equals(soTiet)) {
                return tiet.thoiGian;
            }
        }
        return "";
    }
}