package net.tdiant.tinyjvm.runtime;

public class Slot {

    public boolean tag = false; //false:num ; true: instance
    public int num;
    public Instance ref;

    public Slot(int num) {
        this.num = num;
        this.ref = null;
        this.tag = false;
    }

    public Slot(Instance ref) {
        this.num = 0;
        this.ref = ref;
        this.tag = true;
    }

    @Override
    public String toString() {
        return "Slot{" + "num=" + num +
                ", ref=" + ref +
                ", tag=" + tag +
                '}';
    }

    public Instance getRef() {
        return ref;
    }

    public int getNum() {
        return num;
    }
}
