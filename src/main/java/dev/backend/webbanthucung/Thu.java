package dev.backend.webbanthucung;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thu)) return false;
        Thu that = (Thu) o;
        return thu == that.thu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(thu);
    }
}
