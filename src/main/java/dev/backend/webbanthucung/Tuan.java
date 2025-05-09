package dev.backend.webbanthucung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tuan {
    private int soTuan;
    private List<Thu> dsThu = new ArrayList<>();

    public Tuan(int soTuan) {
        this.soTuan = soTuan;
        for (int i = 2; i < 9; i++) {
            dsThu.add(new Thu(i));
        }
    }

    public int getSoTuan() {
        return soTuan;
    }

    public List<Thu> getDsThu() {
        return dsThu;
    }

    public Thu getThu(int thu) {
        return dsThu.get(thu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuan)) return false;
        Tuan tuan = (Tuan) o;
        return soTuan == tuan.soTuan;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(soTuan);
    }
}
