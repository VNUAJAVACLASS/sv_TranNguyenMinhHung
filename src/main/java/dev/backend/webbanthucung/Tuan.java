package dev.backend.webbanthucung;

import java.util.HashMap;

class Tuan {
    private HashMap<Integer, Thu> dsThu = new HashMap<>(); // Key: số thứ, Value: đối tượng Thu

    public HashMap<Integer, Thu> getDsThu() {
        return dsThu;
    }

    public void setDsThu(HashMap<Integer, Thu> dsThu) {
        this.dsThu = dsThu;
    }
}