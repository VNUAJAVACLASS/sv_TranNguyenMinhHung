package dev.backend.webbanthucung;

import java.util.ArrayList;
import java.util.List;

public class Tuan {
    private int soTuan;
    private List<Thu> dsThu = new ArrayList<>();

    public Tuan(int soTuan) {
        this.soTuan = soTuan;
    }

    public void themThu(Thu thu) {dsThu.add(thu);}

    public int getSoTuan() {
        return soTuan;
    }

    public List<Thu> getDsThu() {
        return dsThu;
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
