package net.tdiant.tinyjvm.runtime;

public class LocalVariableTable {

    private final Slot[] slots;

    public LocalVariableTable(int size) {
        slots = new Slot[size * 2+5];
    }

    public Slot get(int idx) {
        return slots[idx];
    }

    public void set(int idx, Slot s) {
        slots[idx] = s;
        if (s.getLow() != null)
            slots[idx + 1] = s;
    }

}
