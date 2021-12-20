package net.tdiant.tinyjvm.runtime;

public class SlotUnit {

    private Object obj;
    private boolean tag = false; //false: int; true: instance

    public SlotUnit(int idx) {
        tag = false;
        obj = idx;
    }

    public SlotUnit(Instance inst) {
        tag = true;
        obj = inst;
    }

    public int getNum() {
        if (tag) return 0;
        return (Integer) obj;
    }

    public Instance getInstance() {
        if (!tag) return null;
        return (Instance) obj;
    }
}

