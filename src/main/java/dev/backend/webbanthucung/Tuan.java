package dev.backend.webbanthucung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Tuan {
    private int soTuan;
    private Map<Integer, Thu> dsThu;

    public Tuan(int soTuan) {
        this.soTuan = soTuan;
        this.dsThu = new HashMap<>();
        // Khởi tạo các thứ từ 2 đến 8
        for (int i = 2; i <= 8; i++) {
            dsThu.put(i, new Thu(i));
        }
    }

    public void themLichHoc(LichHoc lichHoc) {
        int thu = lichHoc.getThu();
        dsThu.get(thu).themLichHoc(lichHoc);
    }

    public List<LichHoc> getLichHocTheoThu(int thu) {
        return dsThu.get(thu).getDsLichHoc();
    }

    public int getSoTuan() {
        return soTuan;
    }
}