package net.tdiant.tinyjvm.runtime;

public class Field extends BaseNametag {

    private Slot val;

    public Field(int accessFlags, String name, String descriptor) {
        super(accessFlags, name, descriptor);
    }

    public void set(Frame frame) {
    }

    public Slot getVal() {
        return val;
    }

    public void setVal(Slot val) {
        this.val = val;
    }

    public void get(Frame frame) {
    }

    public void set(Slot s) {
    }
}
