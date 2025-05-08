package dev.backend.webbanthucung;

import java.util.ArrayList;
import java.util.List;

class Thu {
    private int thu;
    private List<LichHoc> dsLichHoc;

    public Thu(int thu) {
        this.thu = thu;
        this.dsLichHoc = new ArrayList<>();
    }

    public void themLichHoc(LichHoc lichHoc) {
        dsLichHoc.add(lichHoc);
    }

    public List<LichHoc> getDsLichHoc() {
        return dsLichHoc;
    }

    public int getThu() {
        return thu;
    }
}