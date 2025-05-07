package dev.backend.webbanthucung;

public enum TietHoc {
    TIET1("7:00 - 7:50"),
    TIET2("7:55 - 8:45"),
    TIET3("8:50 - 9:40"),
    TIET4("9:55 - 10:45"),
    TIET5("10:50 - 11:40"),
    TIET6("12:45 - 13:35"),
    TIET7("13:40 - 14:30"),
    TIET8("14:35 - 15:25"),
    TIET9("15:40 - 16:30"),
    TIET10("16:35 - 17:25"),
    TIET11("18:00 - 18:50"),
    TIET12("18:55 - 19:45"),
    TIET13("19:50 - 20:40");

    private String thoiGian;

    TietHoc(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getThoiGian() {
        return thoiGian;
    }
}