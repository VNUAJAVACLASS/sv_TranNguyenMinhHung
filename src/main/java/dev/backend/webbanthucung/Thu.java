package dev.backend.webbanthucung;

import java.util.ArrayList;
import java.util.List;

public class Thu {
    private int thu;
    private List<LichHoc> dsLichHoc = new ArrayList<>();

    public Thu(int thu) {
        this.thu = thu;
    }

    public int getThu() {
        return thu;
    }

    public List<LichHoc> getDsLichHoc() {
        return dsLichHoc;
    }

    public void themLichHoc(LichHoc lich) {
        dsLichHoc.add(lich);
    }
}
